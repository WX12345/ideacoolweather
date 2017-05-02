package greendao.util;

import android.content.Context;

import greendao.manager.CityManager;
import greendao.manager.CountryManager;
import greendao.manager.DBManager;
import greendao.manager.ProvinceManager;

/**
 * Created by idea on 3/14 0014.
 */

public class DaoUtils {
    private  static ProvinceManager mProvinceManager;
    private  static CityManager mCityManager;
    private  static CountryManager mCountryManager;
    public  static Context context;

    public static void init(Context context){
        DaoUtils.context = context.getApplicationContext();
    }
    /**
     * 单列模式获取ProvinceManager对象
     * @return
     */
    public static ProvinceManager getProvinceManager(){
        if (mProvinceManager == null) {
            mProvinceManager = new ProvinceManager(context);
        }
        return mProvinceManager;
    }
    /**
     * 单列模式获取CityManager对象
     * @return
     */
    public static CityManager getCityManager(){
        if (mCityManager == null) {
            mCityManager = new CityManager(context);
        }
        return mCityManager;
    }
    /**
     * 单列模式获取CityManager对象
     * @return
     */
    public static CountryManager getCountryManager(){
        if (mCountryManager == null) {
            mCountryManager = new CountryManager(context);
        }
        return mCountryManager;
    }
}
