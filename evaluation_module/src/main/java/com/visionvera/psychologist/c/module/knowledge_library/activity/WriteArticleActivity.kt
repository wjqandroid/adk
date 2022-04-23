package com.visionvera.psychologist.c.module.knowledge_library.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.reflect.TypeToken
import com.gyf.immersionbar.ImmersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.lei.lib.java.rxcache.RxCache
import com.lei.lib.java.rxcache.entity.CacheResponse
import com.lei.lib.java.rxcache.util.RxUtil
import com.lxj.xpopup.XPopup
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.bean.ImageItem
import com.lzy.imagepicker.ui.ImageGridActivity
import com.orhanobut.logger.Logger
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tencent.cos.xml.exception.CosXmlClientException
import com.tencent.cos.xml.exception.CosXmlServiceException
import com.tencent.cos.xml.listener.CosXmlResultListener
import com.tencent.cos.xml.model.CosXmlRequest
import com.tencent.cos.xml.model.CosXmlResult
import com.visionvera.library.base.BaseMVPActivity
import com.visionvera.library.base.Constant
import com.visionvera.library.base.bean.BaseResponseBean3
import com.visionvera.library.base.mvp.view.IBaseView
import com.visionvera.psychologist.c.R
import com.visionvera.psychologist.c.databinding.ActivityWriteArticalBinding
import com.visionvera.psychologist.c.module.knowledge_library.bean.AddArticleRequestBean
import com.visionvera.psychologist.c.module.knowledge_library.bean.DraftBean
import com.visionvera.psychologist.c.module.knowledge_library.contract.IContract
import com.visionvera.psychologist.c.module.knowledge_library.event.PublishArticleEvent
import com.visionvera.psychologist.c.module.knowledge_library.event.SaveDraftEvent
import com.visionvera.psychologist.c.module.knowledge_library.pop.CommonCenterTipsPopup
import com.visionvera.psychologist.c.module.knowledge_library.presenter.WriteArticleActivityPresenter
import com.visionvera.psychologist.c.module.knowledge_library.richeditor.CommonPopupWindow
import com.visionvera.psychologist.c.module.knowledge_library.richeditor.KeyBoardUtils
import com.visionvera.psychologist.c.module.knowledge_library.richeditor.RichEditor
import com.visionvera.psychologist.c.module.knowledge_library.richeditor.RichUtils
import com.visionvera.psychologist.c.module.usercenter.bean.FeedBackImgBean
import com.visionvera.psychologist.c.utils.cos.TencentCosManager
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import io.reactivex.functions.Consumer
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author: 刘传政
 * @date: 2020/6/29 14:19
 * QQ:1052374416
 * 作用:写文章
 * 注意事项:
 */
class WriteArticleActivity : BaseMVPActivity<IContract.WriteArticleActivity.View, WriteArticleActivityPresenter>() {
    private var currentUrl = ""

    //编辑图片的pop
    private var popupWindow: CommonPopupWindow? = null
    lateinit var rxPermissions: RxPermissions

    //默认创建一个，保证取值不null。所以里边的默认值很重要
    var intentBean = IntentBean()
    lateinit var viewBinding: ActivityWriteArticalBinding
    private val selectImages = java.util.ArrayList<ImageItem?>()
    var picPathList = java.util.ArrayList<FeedBackImgBean>()

    companion object {
        fun startActivity(context: Context, intentBean: IntentBean) {
            val intent = Intent(context, WriteArticleActivity::class.java)
            intent.putExtra(Constant.IntentKey.IntentBean, intentBean)
            context.startActivity(intent)
        }

        data class IntentBean(/*文章id*/var id: Int = -1,/*文章标题*/var title: String = "",/*进入此页面的作用 0写文章 1写回答 2编辑文章*/var comeType: Int = 0,/*草稿id*/var draftId: Int = -1,/*草稿内容*/var content: String = "") : Serializable {
        }
    }


    /**
     * 创建view，不要用实现的方式，要new一个，方便直接查看
     * 创建presenter
     * 时机是展示ui之前。因为这里只涉及到创建一些mvp类。不会影响功能。
     */
    override fun initMVP() {
        mView = object : IContract.WriteArticleActivity.View {
            override fun onAddArticle(responseBean: BaseResponseBean3<*>?, resultType: IBaseView.ResultType, errorMsg: String?) {
                hideDialog()

                when (resultType) {
                    IBaseView.ResultType.NET_ERROR -> {
                        //网络异常等
                        ToastUtils.showLong(getString(R.string.base_module_net_error))
                    }
                    IBaseView.ResultType.SERVER_ERROR -> {
                        ToastUtils.showShort(errorMsg)
                    }
                    IBaseView.ResultType.SERVER_NORMAL_DATAYES, IBaseView.ResultType.SERVER_NORMAL_DATANO -> {
                        ToastUtils.showShort("发布成功")
                        //发送状态更新事件总线.因为有的界面需要刷新列表
                        val busEvent = PublishArticleEvent()
                        LiveEventBus
                                .get(PublishArticleEvent::class.java)
                                .post(busEvent)


                        val cacheKey = Constant.Cache.draftsKey
                        //获取缓存
                        val type = object : TypeToken<java.util.ArrayList<DraftBean>>() {}.type
                        RxCache.getInstance()
                                .get<java.util.ArrayList<DraftBean>>(cacheKey, false, type) //key:缓存的key update:表示从缓存获取数据强行返回NULL
                                .compose(RxUtil.io_main())
                                .subscribe(Consumer<CacheResponse<java.util.ArrayList<DraftBean>>> { listCacheResponse ->
                                    listCacheResponse.data?.let {
                                        //非空怎么撸
                                        Logger.i("获取到了缓存  ${cacheKey}:" + listCacheResponse.data.toString())
                                        for (index in it.size - 1 downTo 0) {
                                            if (it.get(index).id == intentBean.draftId) {
                                                it.removeAt(index)
                                                finalSaveSoft(cacheKey, it)
                                                break
                                            }
                                        }

                                    } ?: let {
                                        //为空又怎么撸
                                        Logger.i("没获取到缓存:" + cacheKey)
                                    }

                                }, Consumer { throwable ->
                                    throwable.printStackTrace()
                                    Logger.i("没获取到缓存 ${cacheKey}:$throwable")
                                })

                        finish()
                    }
                }
            }


            override fun onComplete() {}
        }
        mPresenter = WriteArticleActivityPresenter(activity, mView)
    }

    private fun finalSaveSoft(cacheKey: String, list: java.util.ArrayList<DraftBean>) {
        RxCache.getInstance()
                .put(cacheKey, list, -1) //key:缓存的key data:具体的数据 time:缓存的有效时间
                .compose(RxUtil.io_main()) //线程调度
                .subscribe({ aBoolean ->
                    if (aBoolean) {
                        Logger.i("缓存成功  ${cacheKey}" + list)
                        ToastUtils.showShort("保存草稿成功")
                        //发送状态更新事件总线.因为有的界面需要刷新列表
                        val busEvent = SaveDraftEvent()
                        LiveEventBus
                                .get(SaveDraftEvent::class.java)
                                .post(busEvent)
                        finish()

                    }
                    hideDialog()
                }) { throwable ->
                    throwable.printStackTrace()
                    hideDialog()
                    ToastUtils.showShort("保存草稿失败")
                }
    }

    private fun checkPicAndAddArticle() {
        if (checkIfUpAllPic()) {
            //如果图片都传完了
            picPathList?.let {
                var newHtml: String = viewBinding.richEditor.getHtml()
                for (i in picPathList.indices) {

                    picPathList.get(i).setUrl(picPathList.get(i).url)
                    //将本地图片地址替换为网络地址
                    newHtml = newHtml.replace(picPathList.get(i).picPath, picPathList.get(i).url)

                }
                if (picPathList.size > 0) {
                    viewBinding.richEditor.html = newHtml
                }

            }

            var title = viewBinding.editName.text.toString().trim()
            var summary = getSummary()
            var picUrls = ""
            if (picPathList.size > 0) {
                picUrls = picPathList.get(0).getUrl() + ""
            }
            var htmlHead = "<html>\n" +
                    "<head>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "<meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\n" +
                    "<title></title>\n" +
                    "<meta name=\"Generator\" content=\"Cocoa HTML Writer\">\n" +
                    "<style type=\"text/css\">\n" +
                    "p.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 16.0px '.AppleSimplifiedChineseFont'}\n" +
                    "span.s1 {font-family: '.PingFangSC-Regular'; font-weight: normal; font-style: normal; font-size: 16.00px; letter-spacing: 1.2px}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>"
            var htmlFoot = "</body>\n" +
                    "</html>"
            var content = htmlHead + viewBinding.richEditor.html + htmlFoot
            showLoadingDialog()
            net_addArticle(title, summary, picUrls, content)
        } else {
            uploadAllPicOneByOne()
        }
    }

    override fun setContentView(): Boolean {
        viewBinding = ActivityWriteArticalBinding.inflate(layoutInflater)
        val view: View = viewBinding.getRoot()
        setContentView(view)
        return true
    }


    /**
     * 这里不过多区分initview与initdata等。因为他们的顺序不是固定的
     * 避免过度设计
     */
    override fun doYourself() {
        parseIntent()
        rxPermissions = RxPermissions(this@WriteArticleActivity)
        initView()
        initListener()
        initData()
    }

    private fun initView() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .fitsSystemWindows(true)
                //这里专门设置键盘模式,否则键盘弹出时,工具栏不跟随键盘
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .keyboardEnable(true)
                .init()

        if (intentBean.comeType == 0) {
            //写文章
        } else if (intentBean.comeType == 1) {
            //写回答
            if (!intentBean.title.isNullOrBlank()) {
                //标题
                viewBinding.editName.setText(intentBean.title)
                viewBinding.editName.isEnabled = false
            }
        } else if (intentBean.comeType == 2) {
            //编辑文章
            viewBinding.editName.setText(intentBean.title)
            viewBinding.richEditor.setHtml(intentBean.content)
        }
        initRichEditor()
        initPop()
    }


    /**
     * 初始化富文本编辑器
     */
    private fun initRichEditor() {
        //输入框显示字体的大小
        viewBinding.richEditor.setEditorFontSize(16)
        //输入框显示字体的颜色
        viewBinding.richEditor.setEditorFontColor(resources.getColor(R.color.common_text))
        //输入框背景设置
        viewBinding.richEditor.setEditorBackgroundColor(resources.getColor(R.color.white))
        //输入框文本padding
        viewBinding.richEditor.setPadding(0, 10, 0, 0)

        //输入提示文本
        viewBinding.richEditor.setPlaceholder("请输入文章内容！")

        //文本输入框监听事件
        viewBinding.richEditor.setOnTextChangeListener(object : RichEditor.OnTextChangeListener {
            override fun onTextChange(text: String) {
                Logger.i("富文本文字变动:" + text)
                var html2Text = RichUtils.Html2Text(text)

//                if (html2Text.length < 2000) {
//                    viewBinding.tvInputCount.setText("" + html2Text.length + "/2000")
//                    viewBinding.richEditor.setInputEnabled(true)
//                } else {
//                    viewBinding.tvInputCount.setText("2000/2000")
//                    viewBinding.richEditor.setInputEnabled(false)
//                }
            }
        })
        viewBinding.editName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {

            }
        })
        viewBinding.richEditor.setOnDecorationChangeListener(object : RichEditor.OnDecorationStateListener {
            override fun onStateChangeListener(text: String?, types: List<RichEditor.Type>) {
                val flagArr = ArrayList<String>()
                for (i in types.indices) {
                    flagArr.add(types[i].name)
                }
                Logger.i("flagArr:" + flagArr)
                if (flagArr.contains("BOLD")) {

                    viewBinding.buttonBold.setImageResource(R.drawable.article_bold);
                    viewBinding.buttonBold.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.base_module_theme)));
                } else {
                    viewBinding.buttonBold.setImageResource(R.drawable.article_bold);
                    viewBinding.buttonBold.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.bgtint)));
                }
                if (flagArr.contains("ITALIC")) {
                    viewBinding.buttonXieti.setImageResource(R.drawable.article_xieti);
                    viewBinding.buttonXieti.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.base_module_theme)));
                } else {
                    viewBinding.buttonXieti.setImageResource(R.drawable.article_xieti);
                    viewBinding.buttonXieti.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.bgtint)));
                }
                if (flagArr.contains("UNDERLINE")) {
                    viewBinding.buttonUnderline.setImageResource(R.drawable.article_underline);
                    viewBinding.buttonUnderline.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.base_module_theme)));
                } else {
                    viewBinding.buttonUnderline.setImageResource(R.drawable.article_underline);
                    viewBinding.buttonUnderline.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.bgtint)));
                }
                if (flagArr.contains("ORDEREDLIST")) {

                    viewBinding.buttonListUl.setImageResource(R.drawable.article_order_dian);
                    viewBinding.buttonListUl.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.bgtint)));
                    viewBinding.buttonListOl.setImageResource(R.drawable.article_order_number);
                    viewBinding.buttonListOl.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.base_module_theme)));
                } else {
                    viewBinding.buttonListOl.setImageResource(R.drawable.article_order_number);
                    viewBinding.buttonListOl.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.bgtint)));
                }
                if (flagArr.contains("UNORDEREDLIST")) {
                    viewBinding.buttonListOl.setImageResource(R.drawable.article_order_number)
                    val up = ContextCompat.getDrawable(baseContext, R.drawable.article_order_dian)
                    val drawableUp = DrawableCompat.wrap(up!!)
                    DrawableCompat.setTint(drawableUp, ContextCompat.getColor(baseContext, R.color.base_module_theme))
                    viewBinding.buttonListUl.setImageDrawable(drawableUp)

                    viewBinding.buttonListOl.setImageResource(R.drawable.article_order_number);
                    viewBinding.buttonListOl.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.bgtint)));
                    viewBinding.buttonListUl.setImageResource(R.drawable.article_order_dian);
                    viewBinding.buttonListUl.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.base_module_theme)));
                } else {
                    viewBinding.buttonListUl.setImageResource(R.drawable.article_order_dian);
                    viewBinding.buttonListUl.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(baseContext, R.color.bgtint)));
                }

            }
        })
        //查看照片
        viewBinding.richEditor.setImageClickListener(object : RichEditor.ImageClickListener {
            override fun onImageClick(imageUrl: String) {
                currentUrl = imageUrl
                popupWindow?.showBottom(viewBinding.llRoot, 0.5f)
            }
        })
    }

    private fun initPop() {
        val view: View = LayoutInflater.from(this@WriteArticleActivity).inflate(R.layout.newapp_pop_picture, null)
        view.findViewById<View>(R.id.linear_cancle).setOnClickListener { v: View? -> popupWindow!!.dismiss() }
        view.findViewById<View>(R.id.linear_editor).setOnClickListener { v: View? ->
            //编辑图片
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(Consumer { aBoolean: Boolean ->
                if (aBoolean) {
                    if (ActivityCompat.checkSelfPermission(this@WriteArticleActivity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this@WriteArticleActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        val intent = Intent(this@WriteArticleActivity, UCropActivity::class.java)
                        intent.putExtra("filePath", currentUrl)
                        val destDir = filesDir.absolutePath.toString()
                        val fileName = "SampleCropImage" + System.currentTimeMillis() + ".jpg"
                        intent.putExtra("outPath", destDir + fileName)
                        startActivityForResult(intent, 11)
                        popupWindow!!.dismiss()
                    }
                } else {
                    Toast.makeText(this@WriteArticleActivity, "相册需要此权限", Toast.LENGTH_SHORT).show()
                }
            })
        }
        view.findViewById<View>(R.id.linear_delete_pic).setOnClickListener { v: View? ->
            //删除图片
            val removeUrl = "<img src=\"$currentUrl\" alt=\"dachshund\" width=\"100%\"><br>"
            val newUrl: String = viewBinding.richEditor.getHtml().replace(removeUrl, "")
            currentUrl = ""
            viewBinding.richEditor.setHtml(newUrl)
            if (RichUtils.isEmpty(viewBinding.richEditor.getHtml())) {
                viewBinding.richEditor.setHtml("")
            }
            popupWindow!!.dismiss()
        }
        popupWindow = CommonPopupWindow.Builder(this@WriteArticleActivity)
                .setView(view)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true) //在外不可用手指取消
                .setAnimationStyle(R.style.pop_animation) //设置popWindow的出场动画
                .create()
        popupWindow?.setOnDismissListener(PopupWindow.OnDismissListener { viewBinding.richEditor.setInputEnabled(true) })
    }

    private fun initData() {
    }


    private fun initListener() {
        viewBinding.tvCancel.setOnClickListener {
            if (viewBinding.editName.text.isNullOrBlank() && viewBinding.richEditor.html.isNullOrBlank()) {
                finish()
            } else {
                showTipsPop()
            }
        }
        viewBinding.llPublish.setOnClickListener {
            //发布
            Logger.i("html:" + viewBinding.richEditor.getHtml())
            if (viewBinding.editName.text.isNullOrBlank()) {
                ToastUtils.showShort("请输入标题")
                return@setOnClickListener
            }
            if (viewBinding.richEditor.html.isNullOrBlank()) {
                ToastUtils.showShort("请输入文章内容")
                return@setOnClickListener
            }

            val arrayList = RichUtils.returnImageUrlsFromHtml(viewBinding.richEditor.getHtml())
            picPathList.clear()
            arrayList.forEachIndexed { index, s ->
                var imageBean = FeedBackImgBean(FeedBackImgBean.IMG, s)
                if (s.startsWith("http") || s.startsWith("https")) {
                    imageBean.url = s
                }
                picPathList.add(imageBean)
            }
            checkPicAndAddArticle()

        }
        viewBinding.buttonImage.setOnClickListener {
            //选图片
            if (!TextUtils.isEmpty(viewBinding.richEditor.getHtml())) {
                val arrayList = RichUtils.returnImageUrlsFromHtml(viewBinding.richEditor.getHtml())
                if (arrayList != null && arrayList.size >= 9) {
                    Toast.makeText(this@WriteArticleActivity, "最多添加9张照片~", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe { aBoolean: Boolean ->
                if (aBoolean) {
                    if (ActivityCompat.checkSelfPermission(this@WriteArticleActivity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(this@WriteArticleActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        selectImage(104, selectImages)
                        KeyBoardUtils.closeKeybord(viewBinding.editName, this@WriteArticleActivity)
                    }
                } else {
                    Toast.makeText(this@WriteArticleActivity, "相册需要此权限~", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewBinding.buttonBold.setOnClickListener {
            //加粗
            againEdit()
            viewBinding.richEditor.setBold()
        }
        viewBinding.buttonXieti.setOnClickListener {
            //斜体
            againEdit()
            viewBinding.richEditor.setItalic()
        }
        viewBinding.buttonUnderline.setOnClickListener {
            //加下划线
            againEdit()
            viewBinding.richEditor.setUnderline()
        }
        viewBinding.buttonListUl.setOnClickListener {
            //加带点的序列号
            againEdit()
            viewBinding.richEditor.setBullets()
        }
        viewBinding.buttonListOl.setOnClickListener {
            //加带数字的序列号
            againEdit()
            viewBinding.richEditor.setNumbers()
        }
        viewBinding.buttonRichUndo.setOnClickListener {
            //撤销
            viewBinding.richEditor.undo()
        }
        viewBinding.buttonRichDo.setOnClickListener {
            //反撤销
            viewBinding.richEditor.redo()
        }


    }

    private fun showTipsPop() {
        XPopup.Builder(this)
                .moveUpToKeyboard(false)//如果不加这个，评论弹窗会移动到软键盘上面
                .asCustom(CommonCenterTipsPopup(this, "是否存为草稿？", "提示", "不保存", "保存", object : CommonCenterTipsPopup.ResultListener {
                    override fun onCkecked(action: String?) {
                        when (action) {
                            "cancel" -> {
                                //左
                                finish()
                            }
                            "confirm" -> {
                                //右
                                val softBean = DraftBean()
                                softBean.id = intentBean.draftId
                                softBean.title = viewBinding.editName.text.toString().trim()
                                softBean.content = viewBinding.richEditor.html
                                softBean.summary = getSummary()
                                softBean.time = System.currentTimeMillis()
                                saveSoft(softBean)

                            }

                        }
                    }

                    override fun onCreated() {

                    }

                }))
                .show()
    }

    /**
     * 存储草稿到本地
     */
    private fun saveSoft(draftBean: DraftBean) {
        showLoadingDialog("正在保存草稿", false)
        val cacheKey = Constant.Cache.draftsKey
        //获取缓存
        val type = object : TypeToken<java.util.ArrayList<DraftBean>>() {}.type
        RxCache.getInstance()
                .get<java.util.ArrayList<DraftBean>>(cacheKey, false, type) //key:缓存的key update:表示从缓存获取数据强行返回NULL
                .compose(RxUtil.io_main())
                .subscribe(Consumer<CacheResponse<java.util.ArrayList<DraftBean>>> { listCacheResponse ->
                    listCacheResponse.data?.let {
                        //非空怎么撸
                        Logger.i("获取到了缓存  ${cacheKey}:" + listCacheResponse.data.toString())
                        var isNewDraft = true
                        it.forEachIndexed { index, bean ->
                            if (bean.id == draftBean.id && draftBean.id != -1) {
                                //同一个草稿
                                bean.id = draftBean.id
                                bean.title = draftBean.title
                                bean.content = draftBean.content
                                bean.summary = draftBean.summary
                                bean.time = draftBean.time
                                isNewDraft = false
                                return@forEachIndexed
                            }
                        }
                        //时间降序排序
                        it.sortByDescending { softBean -> softBean.time }
                        if (isNewDraft) {
                            draftBean.id = it.get(0).id + 1
                            //新草稿
                            it.add(0, draftBean)
                        }
                        finalSaveSoft(cacheKey, it)
                    } ?: let {
                        //为空又怎么撸
                        Logger.i("没获取到缓存:" + cacheKey)
                        hideDialog()
                        var arrayList = java.util.ArrayList<DraftBean>()
                        arrayList.add(draftBean)
                        finalSaveSoft(cacheKey, arrayList)
                    }

                }, Consumer { throwable ->
                    throwable.printStackTrace()
                    Logger.i("没获取到缓存 ${cacheKey}:$throwable")
                    hideDialog()
                })
    }

    /**
     * 依次上传所有图片
     * 每次只传一张.因为很多方法的回调会相互影响.所以不并发上传
     */
    fun uploadAllPicOneByOne() {
        if (!checkIfUpAllPic()) {
            //如果有图片没上传
            for (i in picPathList.indices) {
                val imgBean: FeedBackImgBean = picPathList.get(i)
                if (imgBean.itemType == FeedBackImgBean.IMG) {
                    if (!TextUtils.isEmpty(imgBean.picPath)) {
                        if (TextUtils.isEmpty(imgBean.url) && imgBean.id == 0) {
                            //证明还没上传过服务器这张图片
                            //把图片先上传cos,再想服务器换取id,id赋值给list,最后将id提交给接口
                            uploadPicByCos(imgBean.picPath, i)
                            return
                        }
                    }
                }
            }
        }
    }

    /**
     * @param picPath
     * @param position
     */
    private fun uploadPicByCos(picPath: String, position: Int) {
        showLoadingDialog("上传中...", false)
        var houzhui = "png"
        val strArr = picPath.split("\\.").toTypedArray()
        if (strArr.size > 0) {
            houzhui = strArr[strArr.size - 1]
        }
        val cosPath = System.currentTimeMillis().toString() + "." + houzhui
        TencentCosManager.getInstance(this).upload(cosPath, picPath, { complete, target -> }, object : CosXmlResultListener {
            override fun onSuccess(request: CosXmlRequest, result: CosXmlResult) {
                activity.runOnUiThread { // 告知服务器


                    //刷新图片列表
                    picPathList[position].url = result.accessUrl
                    checkPicAndAddArticle()
                }

            }

            override fun onFail(request: CosXmlRequest, exception: CosXmlClientException, serviceException: CosXmlServiceException) {
                activity.runOnUiThread {
                    hideDialog()
                    ToastUtils.showShort("上传失败")
                }
            }
        }) {
            activity.runOnUiThread {
                hideDialog()
                ToastUtils.showShort("上传失败")
            }
        }
    }

    /**
     * 检查是否所有的已选图片都上传到服务器拿到了图片id
     */
    private fun checkIfUpAllPic(): Boolean {
        var all = true
        for (i in picPathList.indices) {
            val imgBean: FeedBackImgBean = picPathList.get(i)
            if (imgBean.itemType == FeedBackImgBean.IMG) {
                if (!TextUtils.isEmpty(imgBean.picPath)) {
                    if (TextUtils.isEmpty(imgBean.url) && imgBean.id == 0) {
                        all = false
                    }
                }
            }
        }
        return all
    }


    private fun getSummary(): String {
        var summary = ""
        var html2Text = RichUtils.Html2Text(viewBinding.richEditor.html + "")
        if (html2Text.length >= 50) {
            summary = html2Text.subSequence(0, 50).toString()
        } else {
            summary = html2Text
        }
        return summary
    }

    private fun net_addArticle(title: String, summary: String, coverImageUrls: String, content: String) {
        var requestBean = AddArticleRequestBean()
        requestBean.title = title.trim()
        requestBean.summary = summary.trim()
        requestBean.coverImageUrls = coverImageUrls
        requestBean.content = content
        if (intentBean.comeType == 0) {
            //写文章
        } else if (intentBean.comeType == 1) {
            //写回答
            requestBean.parentId = intentBean.id
        } else if (intentBean.comeType == 2) {
            //编辑文章

        }
        mPresenter.addArticle(requestBean, this)
    }

    fun selectImage(requestCode: Int, imageItems: java.util.ArrayList<ImageItem?>?) {
        val imagePicker = ImagePicker.getInstance()
        imagePicker.isCrop = false
        imagePicker.isMultiMode = false
        imagePicker.isShowCamera = true
        val intent = Intent(this, ImageGridActivity::class.java)
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, imageItems)
        startActivityForResult(intent, requestCode)
    }

    private fun againEdit() {
        //如果第一次点击例如加粗，没有焦点时，获取焦点并弹出软键盘
        viewBinding.richEditor.focusEditor()
        KeyBoardUtils.openKeybord(viewBinding.editName, this@WriteArticleActivity)
    }

    private fun parseIntent() {
        //获取参数
        val bean = intent.getSerializableExtra(Constant.IntentKey.IntentBean) as IntentBean?
        if (bean != null) {
            intentBean = bean
        }
        Logger.i(intentBean.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //选照片的回调
            if (requestCode == 104) {
                selectImages.clear()
                val selects = data!!.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS) as java.util.ArrayList<ImageItem>
                selectImages.addAll(selects)
                againEdit()
//                selectImages[0]!!.path = "http://slyl-rmcp-cloud-1301295327.cos.ap-beijing.myqcloud.com/upload/images/MedicalCircle/1604544221341-6975-973.jpeg"
                viewBinding.richEditor.insertImage(selectImages[0]!!.path, "dachshund")
                KeyBoardUtils.openKeybord(viewBinding.editName, this@WriteArticleActivity)
                viewBinding.richEditor.postDelayed(Runnable {
                    if (viewBinding.richEditor != null) {
                        viewBinding.richEditor.scrollToBottom()
                    }
                }, 200)
            }
        } else if (resultCode == -1) {
            //编辑图片的回调
            if (requestCode == 11) {
                val outPath = data!!.getStringExtra(UCrop.EXTRA_OUTPUT_URI)
                if (!TextUtils.isEmpty(outPath)) {
                    val newHtml: String = viewBinding.richEditor.getHtml().replace(currentUrl, outPath)
                    viewBinding.richEditor.setHtml(newHtml)
                    currentUrl = ""
                }
            }
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

    override fun getLayoutId(): Int {
        return 0
    }

}