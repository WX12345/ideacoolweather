package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ideacoolweather.android.model.City;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CITY".
*/
public class CityDao extends AbstractDao<City, Long> {

    public static final String TABLENAME = "CITY";

    /**
     * Properties of entity City.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property CityId = new Property(0, Long.class, "cityId", true, "_id");
        public final static Property ProvinceId = new Property(1, int.class, "provinceId", false, "PROVINCE_ID");
        public final static Property CityName = new Property(2, String.class, "cityName", false, "CITY_NAME");
        public final static Property CityCode = new Property(3, int.class, "cityCode", false, "CITY_CODE");
    };


    public CityDao(DaoConfig config) {
        super(config);
    }
    
    public CityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: cityId
                "\"PROVINCE_ID\" INTEGER NOT NULL ," + // 1: provinceId
                "\"CITY_NAME\" TEXT," + // 2: cityName
                "\"CITY_CODE\" INTEGER NOT NULL );"); // 3: cityCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, City entity) {
        stmt.clearBindings();
 
        Long cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindLong(1, cityId);
        }
        stmt.bindLong(2, entity.getProvinceId());
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(3, cityName);
        }
        stmt.bindLong(4, entity.getCityCode());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, City entity) {
        stmt.clearBindings();
 
        Long cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindLong(1, cityId);
        }
        stmt.bindLong(2, entity.getProvinceId());
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(3, cityName);
        }
        stmt.bindLong(4, entity.getCityCode());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public City readEntity(Cursor cursor, int offset) {
        City entity = new City( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // cityId
            cursor.getInt(offset + 1), // provinceId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cityName
            cursor.getInt(offset + 3) // cityCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, City entity, int offset) {
        entity.setCityId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProvinceId(cursor.getInt(offset + 1));
        entity.setCityName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCityCode(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(City entity, long rowId) {
        entity.setCityId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(City entity) {
        if(entity != null) {
            return entity.getCityId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
