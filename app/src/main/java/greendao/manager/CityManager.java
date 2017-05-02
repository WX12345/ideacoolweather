package greendao.manager;

import android.content.Context;
import android.util.Log;

import com.ideacoolweather.android.model.City;
import com.ideacoolweather.android.model.Province;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import greendao.BaseDao;

/**
 * Created by idea on 3/14 0014.
 */

public class CityManager extends BaseDao<City> {

    public CityManager(Context context) {
        super(context);
    }

    @Override
    public boolean insertObject(City object) {
        return super.insertObject(object);
    }

    @Override
    public List<City> queryObject(Class object, String where, String... params) {
        return super.queryObject(object, where, params);
    }

    @Override
    public List<City> queryAll(Class object) {
        return super.queryAll(object);
    }
}
