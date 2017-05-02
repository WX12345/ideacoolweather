package greendao.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ideacoolweather.android.model.Province;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import greendao.DaoMaster;
import greendao.DaoSession;
import greendao.ProvinceDao;

/**
 * Created by idea on 3/14 0014.
 */

public class DBManager{

    private final static String dbName = "idea_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper mHelper;
    private static  DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static SQLiteDatabase db;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 判断数据库是否存在，如果不存在则创建
     * @return
     */
    public DaoMaster getDaoMaster(){
        if (null == mDaoMaster){
            mHelper =  new DaoMaster.DevOpenHelper(context,dbName,null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }
    /**
     * 完成对数据库的增删查找
     * @return
     */
    public DaoSession getDaoSession(){
        if (null == mDaoSession){
            if (null == mDaoMaster){
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 设置debug模式开启或关闭，默认关闭
     * @param flag
     */
    public void setDebug(boolean flag){
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }
    /**
     * 关闭数据库
     */
    public void closeDataBase(){
        closeHelper();
        closeDaoSession();
    }

    public void closeDaoSession(){
        if (null != mDaoSession){
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public  void  closeHelper(){
        if (mHelper!=null){
            mHelper.close();
            mHelper = null;
        }
    }
}
