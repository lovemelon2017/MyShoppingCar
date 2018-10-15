package xiaofu.com.buycar_han;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;

public class MyApplication extends Application{
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static MyApplication instances;
    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();

    }
    public static MyApplication getInstances(){
        return instances;
    }

    /**
     * 设置greenDAO
     */
    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "car-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

}
