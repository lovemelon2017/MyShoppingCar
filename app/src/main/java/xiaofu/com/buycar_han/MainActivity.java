package xiaofu.com.buycar_han;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xiaofu.com.buycar_han.adapter.MyProductAdapter;
import xiaofu.com.buycar_han.bean.ProductBean;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.car_tv)
    TextView tvCar;
    @BindView(R.id.pro_rv)
    RecyclerView rvProduct;

    List<ProductBean> beans = new ArrayList<>();
    MyProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //模拟数据
        getListData();
        // 创建一个线性布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // 设置布局管理器
        rvProduct.setLayoutManager(gridLayoutManager);
        adapter = new MyProductAdapter(R.layout.rv_item, beans);
        rvProduct.setAdapter(adapter);
        //开启动画
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

    }

    @OnClick(R.id.car_tv)
    public void onClick() {
        startActivity(new Intent(this, CarActivity.class));
    }


    private void getListData() {
        beans.add(new ProductBean(1l, "1", "胭脂涂粉系列", "该系列纯属胡闹", 100, true, 1));
        beans.add(new ProductBean(2l, "2", "英雄联盟小鱼人", "小鱼人-刺客", 23, true, 1));
        beans.add(new ProductBean(3l, "3", "英雄联盟寒冰射手", "寒冰-尺帝拿手绝活", 10, true, 1));
        beans.add(new ProductBean(4l, "4", "哈哈哈", "傻笑系列", 50, true, 1));
        beans.add(new ProductBean(5l, "5", "杭州西湖", "西湖美达哒", 30, true, 1));
        beans.add(new ProductBean(6l, "6", "最美的不是下雨天", "周杰伦-晴天", 200, true, 1));
        beans.add(new ProductBean(7l, "7", "汉堡豆干", "吃货系列", 660, true, 1));
        beans.add(new ProductBean(8l, "8", "糖炒栗子", "该系列纯属胡闹", 40, true, 1));
        beans.add(new ProductBean(9l, "9", "卡路里", "卡路里-卡琉璃", 88, true, 1));
        beans.add(new ProductBean(10l, "10", "电脑", "戴尔-900", 5000, true, 1));
        beans.add(new ProductBean(11l, "11", "电脑薄薄系列", "小米-Air", 6000, true, 1));
        beans.add(new ProductBean(12l, "12", "嘻嘻嘻说点啥", "斗破苍穹", 100, true, 1));
        beans.add(new ProductBean(13l, "13", "汉朝超", "濮阳毕业", 1000, true, 1));
        beans.add(new ProductBean(14l, "14", "安阳工学院", "河南-牛逼大学", 300, true, 1));
        beans.add(new ProductBean(15l, "15", "北京大学", "计算机科学-人文景观", 2000, true, 1));
        beans.add(new ProductBean(16l, "16", "上海", "东方明珠", 280, true, 1));
        beans.add(new ProductBean(17l, "17", "小乔流水", "小巧小巧拉拉", 110, true, 1));
        beans.add(new ProductBean(18l, "18", "中国地质大学", "考古专家", 800, true, 1));
        beans.add(new ProductBean(19l, "19", "听歌", "挺好的", 720, true, 1));
        beans.add(new ProductBean(20l, "20", "Android-Studio", "RNG-NO-GIVE-UP", 10, true, 1));
    }
}
