package greendao.manager;

import android.content.Context;

import com.ideacoolweather.android.model.Province;

import java.util.List;

import greendao.BaseDao;

/**
 * Created by idea on 3/14 0014.
 */

public class ProvinceManager extends BaseDao<Province> {

    public ProvinceManager(Context context) {
        super(context);
    }

    /**
     * 插入对象
     *
     * @param
     * @return
     */
//    public void insertProvince(Province province){
//        daoSession.getProvinceDao().insert(province);
//    }
    @Override
    public boolean insertObject(Province object) {
        return super.insertObject(object);
    }

    /**
     * 查询所有的省的信息
     *
     * @return
     */
    @Override
    public List<Province> queryAll(Class object) {
        return super.queryAll(object);
    }

//    public List<Province> queryProvince(){
//       return daoSession.getProvinceDao().loadAll();
//    }

    /**
     * 删除所有的省的信息
     * @return
     */
    @Override
    public boolean deleteAll(Class clss) {
        return super.deleteAll(clss);
    }

    @Override
    public void deleteObject(Province object) {
        super.deleteObject(object);
    }
}
