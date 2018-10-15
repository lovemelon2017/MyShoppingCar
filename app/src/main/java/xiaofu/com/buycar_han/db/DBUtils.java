package xiaofu.com.buycar_han.db;

import com.greendao.gen.DaoSession;
import com.greendao.gen.ProductBeanDao;

import java.util.List;

import xiaofu.com.buycar_han.MyApplication;
import xiaofu.com.buycar_han.bean.ProductBean;

public class DBUtils {
    public static List<ProductBean> qurrey() {
        DaoSession daoSession = MyApplication.getInstances().getDaoSession();
        daoSession.clear(); //清除缓存，即时刷新
        ProductBeanDao productBeanDao = daoSession.getProductBeanDao();

        List<ProductBean> productBeans = productBeanDao.loadAll();
        if (productBeans != null && productBeans.size() > 0) {
            return productBeans;
        }
        return null;
    }

    public static void delete(Long id) {
        ProductBeanDao productBeanDao = MyApplication.getInstances().getDaoSession().getProductBeanDao();
        productBeanDao.deleteByKey(id);
    }

    public static void insert(ProductBean bean) {
        ProductBeanDao productBeanDao = MyApplication.getInstances().getDaoSession().getProductBeanDao();
        ProductBean load = productBeanDao.load(bean.getId());
        if (load == null) {
            productBeanDao.insert(bean);
        } else {
            productBeanDao.update(bean);
        }
    }
}
