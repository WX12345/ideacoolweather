package greendao.manager;

import android.content.Context;

import com.ideacoolweather.android.model.City;
import com.ideacoolweather.android.model.County;

import java.util.List;

import greendao.BaseDao;

/**
 * Created by idea on 3/14 0014.
 */

public class CountryManager extends BaseDao<County> {

    public CountryManager(Context context) {
        super(context);
    }

    @Override
    public boolean insertObject(County object) {
        return super.insertObject(object);
    }

    @Override
    public List<County> queryObject(Class object, String where, String... params) {
        return super.queryObject(object, where, params);
    }

    @Override
    public List<County> queryAll(Class object) {
        return super.queryAll(object);
    }
}
