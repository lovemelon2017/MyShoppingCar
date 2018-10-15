package xiaofu.com.buycar_han.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 商品 bean
 */
@Entity
public class ProductBean {
    @Id(autoincrement = true)
    private Long id;
    private String imageUrl;
    private String title;
    private String descripe;
    private long price;
    private boolean isCheck; //记录购物车商品是否选中(简单操作)
    private int num; //记录购物车商品数量
    @Generated(hash = 114941851)
    public ProductBean(Long id, String imageUrl, String title, String descripe,
            long price, boolean isCheck, int num) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.descripe = descripe;
        this.price = price;
        this.isCheck = isCheck;
        this.num = num;
    }
    @Generated(hash = 669380001)
    public ProductBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescripe() {
        return this.descripe;
    }
    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }
    public long getPrice() {
        return this.price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public boolean getIsCheck() {
        return this.isCheck;
    }
    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    
}
