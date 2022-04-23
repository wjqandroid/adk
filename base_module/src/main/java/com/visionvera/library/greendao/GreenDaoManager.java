package com.visionvera.library.greendao;

import android.content.Context;

import com.visionvera.library.base.Constant;
import com.visionvera.library.greendao.gen.DaoMaster;
import com.visionvera.library.greendao.gen.DaoSession;
import com.visionvera.library.greendao.gen.SearchHistoryDBBeanDao;
import com.visionvera.library.greendao.testbean.Student;

import java.util.List;


/**
 * author lilongfeng
 * date 2019/6/19
 */

public class GreenDaoManager {

    private volatile static GreenDaoManager instance;//多线程访问
    private static Context mContext;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;


    /**
     * 初始化Context对象
     *
     * @param context
     */
    public static void init(Context context) {
        GreenDaoManager.mContext = context;
    }


    /**
     * 使用单例模式获得操作数据库的对象
     */
    public static GreenDaoManager getInstance() {
        if (instance == null) {
            synchronized (GreenDaoManager.class) {
                if (instance == null) {
                    instance = new GreenDaoManager();
                }
            }
        }
        return instance;
    }

    /**
     * 判断数据库是否存在，如果不存在则创建
     */
    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, Constant.GreenDao.DBName);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的增删查找
     */
    public DaoSession getDaoSession() {
        if (null == mDaoSession) {
            if (null == mDaoMaster) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 添加数据库数据
     *
     * @param student
     */
    public void insert(Student student) {
        if (mDaoSession != null) {
            mDaoSession.insertOrReplace(student);
        }
    }

    /**
     * 删除数据库数据
     */
    public void deleteAll() {
        if (mDaoSession != null) {
            mDaoSession.deleteAll(Student.class);
        }
    }

    /**
     * 查询数据库全部数据
     */
    public List<Student> queryAll() {
        List<Student> students = mDaoSession.loadAll(Student.class);
        return students;
    }

    /**
     * 查询数据库一条数据
     */
   /* public Student query(String id){
        List<Student> students=mDaoSession.queryRaw(Student.class,"where id = ?" ,id);

    }*/


    /**
     * 测评模块-搜索历史
     * 添加数据库数据
     *
     * @param bean
     */
    public void insert(SearchHistoryDBBean bean) {
        if (mDaoSession != null) {
            List<SearchHistoryDBBean> list = mDaoSession.getSearchHistoryDBBeanDao().queryBuilder()
                    .where(SearchHistoryDBBeanDao.Properties.DiscoverValue.eq(bean.getDiscoverValue()))
                    .list();
            if (list != null && list.size() > 0) {
                for (SearchHistoryDBBean searchHistoryDBBean : list) {
                    mDaoSession.delete(searchHistoryDBBean);
                }
            }
            mDaoSession.insertOrReplace(bean);
        }
    }

    /**
     * 测评模块-搜索历史
     * 删除
     */
    public void clearAllSearchHistory() {
        if (mDaoSession != null) {
            mDaoSession.deleteAll(SearchHistoryDBBean.class);
        }
    }

    /**
     * 测评模块-搜索历史
     * 查询10条
     */
    public List<SearchHistoryDBBean> query10SearchHistory() {
        List<SearchHistoryDBBean> list = mDaoSession.getSearchHistoryDBBeanDao().queryBuilder()
                .limit(10)
                .orderDesc(SearchHistoryDBBeanDao.Properties.Id)
                .list();
        return list;
    }
}
