package greendao;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import greendao.manager.DBManager;

/**
 * Created by idea on 3/14 0014.
 */

public class BaseDao<T> {

    public static final String TAG = BaseDao.class.getSimpleName();
    public static final boolean DUBUG = true;
    public DBManager manager;
    public DaoSession daoSession;

    public BaseDao(Context context) {
        manager = DBManager.getInstance(context);
        daoSession = manager.getDaoSession();
        manager.setDebug(DUBUG);
    }

    /**************************数据库插入操作***********************/
    /**
     * 插入单个对象
     * @param object
     * @return
     */
    public boolean insertObject(T object){
        boolean flag = false;
        try {
            flag = manager.getDaoSession().insert(object) != -1 ? true:false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return flag;
    }

    /**
     * 查询所有对象
     * @param object
     * @return
     */
    public List<T> queryAll(Class object){
        List<T> objects = null;
        try {
            objects = (List<T>) daoSession.getDao(object).loadAll();
        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        return objects;
    }

    /**
     * 删除某个数据库表
     * @param clss
     * @return
     */
    public boolean deleteAll(Class clss){
        boolean flag = false;
        try {
            manager.getDaoSession().deleteAll(clss);
            flag = true;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            flag = false;
        }
        return flag;
    }


    /**
     * 删除某个对象
     * @param object
     * @return
     */
    public void deleteObject(T object){
        try {
            daoSession.delete(object);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 查询某条件下的对象
     * @param object
     * @return
     */
    public List<T> queryObject(Class object ,String where,String...params){
        Object obj = null;
        List<T> objects = null;
        try {
            obj = daoSession.getDao(object);
            if (null == obj){
                return null;
            }
           objects = daoSession.getDao(object).queryRaw(where,params);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return objects;
    }

    /***************************关闭数据库*************************/
    /**
     * 关闭数据库一般在Odestory中使用
     */
    public void CloseDataBase(){
        manager.closeDataBase();
    }


}
