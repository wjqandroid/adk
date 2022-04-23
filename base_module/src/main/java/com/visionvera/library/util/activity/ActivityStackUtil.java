package com.visionvera.library.util.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

import java.util.Stack;

/**
 * activity 堆栈工具
 */

public class ActivityStackUtil {

    /**
     * 存储ActivityStack
     */
    private static Stack<Activity> activityStack = new Stack<>();

    /**
     * 单例模式
     */
    private static ActivityStackUtil instance;


    /**
     * 单列堆栈集合对象
     *
     * @return AppDavikActivityMgr 单利堆栈集合对象
     */
    public static ActivityStackUtil getScreenManager() {
        if (instance == null) {
            synchronized (ActivityStackUtil.class) {
                if (instance == null) {
                    instance = new ActivityStackUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 堆栈中销毁并移除
     *
     * @param activity 指定Act对象
     */
    public void removeActivity(Activity activity) {
        if (null != activity) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }


    /**
     * 栈中销毁并移除所有Act对象
     */
    public void removeAllActivity() {
        if (null != activityStack && activityStack.size() > 0) {
            //创建临时集合对象
            Stack<Activity> activityTemp = new Stack<Activity>();
            for (Activity activity : activityStack) {
                if (null != activity) {
                    //添加到临时集合中
                    activityTemp.add(activity);
                    //结束Activity
                    activity.finish();
                }
            }
            activityStack.removeAll(activityTemp);
        }
        System.gc();
        System.exit(0);
    }


    /**
     * 获取当前Act对象
     *
     * @return Activity 当前act
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }


    /**
     * 获得当前Act的类名
     *
     * @return String
     */
    public String getCurrentActivityName() {
        String actSimpleName = "";
        if (!activityStack.empty()) {
            actSimpleName = activityStack.lastElement().getClass().getSimpleName();
        }
        return actSimpleName;
    }


    /**
     * 将Act纳入推栈集合中
     *
     * @param activity Act对象
     */
    public void addActivity(Activity activity) {
        if (null == activityStack) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
        Logger.i("activity启动:" + activity);
    }


    /**
     * 退出栈中所有Activity
     *
     * @param cls
     * @return void
     */
    public void exitApp(Class<?> cls) {
        while (true) {
            Activity activity = currentActivity();
            if (null == activity) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            removeActivity(activity);
        }
        System.gc();
        System.exit(0);
    }

    /**
     * 重新打开，并且移除所有原来此activity之上的activity
     */
    public void createAndClearTopOfMy(Context context, Class<?> cls) {
        if (null == activityStack) {
            activityStack = new Stack<>();
        }
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            if (activityStack.get(i).getClass().equals(cls)) {
                for (int i1 = activityStack.size() - 1; i1 >= i; i1--) {
                    removeActivity(activityStack.get(i1));
                }
                break;
            }

        }
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 移除所有原来此activity之上的activity
     */
    public void clearTopOfMy(Context context, Class<?> cls) {
        if (null == activityStack) {
            activityStack = new Stack<>();
        }
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            if (activityStack.get(i).getClass().equals(cls)) {
                for (int i1 = activityStack.size() - 1; i1 > i; i1--) {
                    removeActivity(activityStack.get(i1));
                }
                break;
            }

        }
    }

    /**
     *移除所有原来此activity之上的activity,包括cls的activity
     */
    public void  clearTopOfMyIncludeMy(Context context, Class<?> cls){
        if (null == activityStack) {
            activityStack = new Stack<>();
        }
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            if (activityStack.get(i).getClass().equals(cls)) {
                for (int i1 = activityStack.size() - 1; i1 >= i; i1--) {
                    removeActivity(activityStack.get(i1));
                }
                break;
            }

        }
    }


}
