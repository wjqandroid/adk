package com.visionvera.live.module.search.contract;import android.content.Context;import com.trello.rxlifecycle2.LifecycleProvider;import com.visionvera.live.base.bean.ResBean;import com.visionvera.live.base.mvp.IBaseModel;import com.visionvera.live.base.mvp.IBasePresenter;import com.visionvera.live.base.mvp.IBaseView;import com.visionvera.live.module.home.bean.CourseBean;import java.util.List;import io.reactivex.Observer;import okhttp3.RequestBody;/** * @Desc 搜索mvp接口约束类 * * @Author yemh * @Date 2019/4/15 17:30 */public interface SearchContract {        interface IModel extends IBaseModel {            void getSearchResult(RequestBody params, LifecycleProvider provider, Observer<ResBean<List<CourseBean>>> observer);        }        interface IView extends IBaseView {            void showSearchResult(ResBean<List<CourseBean>> bean);        }        interface IPresenter extends IBasePresenter {            void getSearchCourse(Context context, RequestBody params, LifecycleProvider provider);        }}