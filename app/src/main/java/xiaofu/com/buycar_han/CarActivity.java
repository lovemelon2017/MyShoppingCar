package xiaofu.com.buycar_han;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.greendao.DbUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xiaofu.com.buycar_han.adapter.CarAdapter;
import xiaofu.com.buycar_han.bean.ProductBean;
import xiaofu.com.buycar_han.db.DBUtils;
import xiaofu.com.buycar_han.myinterface.ItemAddOrDeleteLisener;

public class CarActivity extends AppCompatActivity {

    @BindView(R.id.buy_price_tv)
    TextView tvPrice;
    @BindView(R.id.tv_checked)
    TextView tvCheck;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    @BindView(R.id.car_have_cl)
    View vHaveProduct;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.title_car_bianji)
    TextView tvBianji;
    @BindView(R.id.car_rv)
    RecyclerView rvCar;
    @BindView(R.id.back_iv)
    ImageView ivBack;
    @BindView(R.id.to_buy_tv)
    TextView tvBuy;

    CarAdapter adapter;
    List<ProductBean> beanList;
    boolean isCheckedDelete = false;  //记录编辑 与 完成 按钮
    ArrayList<ProductBean> listChecked = new ArrayList<>();
    ArrayList<ProductBean> listDelete = new ArrayList<>();//删除记录列表
    ArrayList<ProductBean> listDeleteChecked = new ArrayList<>();//删除选中记录列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        ButterKnife.bind(this);
        //查询数据
        beanList = DBUtils.qurrey();
        if (beanList != null) {
            vHaveProduct.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            tvBianji.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.VISIBLE);
            vHaveProduct.setVisibility(View.GONE);
            tvBianji.setVisibility(View.GONE);
        }
        if (beanList != null) {
            setAdapter();
            setPrice();
        }
    }

    @OnClick(R.id.back_iv)
    public void onClick() {
        finish();
    }

    /**
     * 编辑 与 完成
     */
    @OnClick(R.id.title_car_bianji)
    public void edit() {
        //显示删除
        if (!isCheckedDelete) {
            listDelete.clear();
            listDeleteChecked.clear();
            isCheckedDelete = true;
            tvBianji.setText("完成");
            tvBuy.setText("删除所选");
            tvPrice.setVisibility(View.INVISIBLE);
            for (int i = 0; i < beanList.size(); i++) {
                ProductBean bean = beanList.get(i);
                bean.setIsCheck(false);
                listDelete.add(bean);
            }
            adapter.setNewData(listDelete);
            if (listDeleteChecked.size() == listDelete.size()) {
                ivCheck.setImageResource(R.mipmap.yes_checked);
                tvCheck.setText("全部" + " ( " + listDeleteChecked.size() + " )");
            } else {
                ivCheck.setImageResource(R.mipmap.no_check);
                tvCheck.setText("已选" + " ( " + listDeleteChecked.size() + " )");
            }
        } else {
            beanList.clear();
            beanList = DBUtils.qurrey();
            isCheckedDelete = false;
            tvBianji.setText("编辑");
            tvBuy.setText("立即下单");
            tvPrice.setVisibility(View.VISIBLE);
            adapter.setNewData(beanList);
            setPrice();
        }
    }

    /**
     * 下单
     */
    @OnClick(R.id.to_buy_tv)
    public void buy() {
        String strBuy = tvBuy.getText().toString();
        if (strBuy.equals("立即下单")) {
            Toast.makeText(CarActivity.this, "去下单吧!", Toast.LENGTH_SHORT).show();
        } else {
            //删除
            if (listDeleteChecked.size() > 0) {
                showDelete();
            } else {
                Toast.makeText(CarActivity.this, "您还未添加要删除的商品", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void showDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除");
        builder.setMessage("是否要移除购物车?");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //移除数据库
                for (int i = 0; i < listDeleteChecked.size(); i++) {
                    DBUtils.delete(listDeleteChecked.get(i).getId());
                    listDelete.remove(listDeleteChecked.get(i));
                }
                Toast.makeText(CarActivity.this, "成功移除购物车", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                if (listDelete.size() == 0) {
                    vHaveProduct.setVisibility(View.GONE);
                    tvBianji.setVisibility(View.GONE);
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
        builder.show();
    }

    /**
     * 全选按钮
     */
    @OnClick(R.id.iv_check)
    public void allCheck() {
        if (isCheckedDelete) {
            //删除全选 状态
            if (listDeleteChecked.size() == listDelete.size()) {
                for (int i = 0; i < listDelete.size(); i++) {
                    ProductBean bean = listDelete.get(i);
                    bean.setIsCheck(false);
                    ivCheck.setImageResource(R.mipmap.no_check);
                    adapter.setNewData(listDelete);
                    setDeletedCheck();
                }
            } else {
                for (int i = 0; i < listDelete.size(); i++) {
                    ProductBean bean = listDelete.get(i);
                    bean.setIsCheck(true);
                    ivCheck.setImageResource(R.mipmap.yes_checked);
                    adapter.setNewData(listDelete);
                    setDeletedCheck();
                }
            }


        } else {
            if (listChecked.size() == beanList.size()) {
                for (int i = 0; i < beanList.size(); i++) {
                    ProductBean bean = beanList.get(i);
                    bean.setIsCheck(false);
                    DBUtils.insert(bean);
                    ivCheck.setImageResource(R.mipmap.no_check);
                }
            } else {
                for (int i = 0; i < beanList.size(); i++) {
                    ProductBean bean = beanList.get(i);
                    bean.setIsCheck(true);
                    DBUtils.insert(bean);
                    ivCheck.setImageResource(R.mipmap.yes_checked);
                }
            }
            setPrice();
            adapter.notifyDataSetChanged();
        }

    }

    private void setAdapter() {
        rvCar.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(R.layout.rv_car_item);
        if (beanList != null) {
            adapter.addData(beanList);
        }
        rvCar.setAdapter(adapter);
        adapter.setAddOrDeleteLisener(new ItemAddOrDeleteLisener() {
            @Override
            public void change() {
                adapter.notifyDataSetChanged();
                setPrice();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (isCheckedDelete) {
                    ProductBean bean = listDelete.get(position);
                    if (bean.getIsCheck()) {
                        bean.setIsCheck(false);
                        listDeleteChecked.remove(bean);
                    } else {
                        bean.setIsCheck(true);
                        listDeleteChecked.add(bean);
                    }
                    setDeletedCheck();
                    adapter.notifyDataSetChanged();
                } else {
                    if (beanList.size() > 0) {
                        ProductBean bean = beanList.get(position);
                        boolean isCheck = bean.getIsCheck();
                        if (isCheck) {
                            bean.setIsCheck(false);
                        } else {
                            bean.setIsCheck(true);
                        }
                        DBUtils.insert(bean);
                    }
                    adapter.notifyDataSetChanged();
                    setPrice();
                }

            }
        });

    }

    /**
     * 删除选中
     */
    private void setDeletedCheck() {
        listDeleteChecked.clear();
        if (listDelete == null) {
            return;
        }
        for (int i = 0; i < listDelete.size(); i++) {
            boolean isCheck = listDelete.get(i).getIsCheck();
            if (isCheck) {
                listDeleteChecked.add(listDelete.get(i));
            }
        }
        if (listDeleteChecked.size() == listDelete.size()) {
            ivCheck.setImageResource(R.mipmap.yes_checked);
            tvCheck.setText("全部" + " ( " + listDeleteChecked.size() + " )");
        } else {
            ivCheck.setImageResource(R.mipmap.no_check);
            tvCheck.setText("已选" + " ( " + listDeleteChecked.size() + " )");
        }
    }

    float allPrice; //价格总和

    private void setPrice() {
        listChecked.clear();
        if (beanList == null) {
            return;
        }
        allPrice = 0;
        for (int i = 0; i < beanList.size(); i++) {
            ProductBean bean = beanList.get(i);
            boolean isCheck = bean.getIsCheck();
            if (isCheck) {
                allPrice = allPrice + bean.getNum() * bean.getPrice();
                listChecked.add(bean);
            }
        }

        tvPrice.setText("¥ " + allPrice);
        if (listChecked.size() == beanList.size()) {
            ivCheck.setImageResource(R.mipmap.yes_checked);
            tvCheck.setText("全部" + " ( " + listChecked.size() + " )");
        } else {
            ivCheck.setImageResource(R.mipmap.no_check);
            tvCheck.setText("已选" + " ( " + listChecked.size() + " )");
        }

    }
}
