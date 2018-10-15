package xiaofu.com.buycar_han.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xiaofu.com.buycar_han.MainActivity;
import xiaofu.com.buycar_han.R;
import xiaofu.com.buycar_han.bean.ProductBean;
import xiaofu.com.buycar_han.db.DBUtils;

public class MyProductAdapter extends BaseQuickAdapter<ProductBean, MyProductAdapter.ViewHolder> {
    public MyProductAdapter(int layoutResId, @Nullable List<ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, final ProductBean item) {

        helper.tvTitle.setText(item.getTitle());
        String imageUrl = item.getImageUrl();
        switch (imageUrl) {
            case "1":
                Glide.with(mContext).load(R.mipmap.ic_product_1).into(helper.ivProduct);
                break;
            case "2":
                Glide.with(mContext).load(R.mipmap.ic_product_2).into(helper.ivProduct);
                break;
            case "3":
                Glide.with(mContext).load(R.mipmap.ic_product_3).into(helper.ivProduct);
                break;
            case "4":
                Glide.with(mContext).load(R.mipmap.ic_product_4).into(helper.ivProduct);
                break;
            case "5":
                Glide.with(mContext).load(R.mipmap.ic_product_5).into(helper.ivProduct);
                break;
            case "6":
                Glide.with(mContext).load(R.mipmap.ic_product_6).into(helper.ivProduct);
                break;
            case "7":
                Glide.with(mContext).load(R.mipmap.ic_product_7).into(helper.ivProduct);
                break;
            case "8":
                Glide.with(mContext).load(R.mipmap.ic_product_8).into(helper.ivProduct);
                break;
            case "9":
                Glide.with(mContext).load(R.mipmap.ic_product_9).into(helper.ivProduct);
                break;
            case "10":
                Glide.with(mContext).load(R.mipmap.ic_product_10).into(helper.ivProduct);
                break;
            case "11":
                Glide.with(mContext).load(R.mipmap.ic_product_11).into(helper.ivProduct);
                break;
            case "12":
                Glide.with(mContext).load(R.mipmap.ic_product_12).into(helper.ivProduct);
                break;
            case "13":
                Glide.with(mContext).load(R.mipmap.ic_product_13).into(helper.ivProduct);
                break;
            case "14":
                Glide.with(mContext).load(R.mipmap.ic_product_14).into(helper.ivProduct);
                break;
            case "15":
                Glide.with(mContext).load(R.mipmap.ic_product_15).into(helper.ivProduct);
                break;
            case "16":
                Glide.with(mContext).load(R.mipmap.ic_product_16).into(helper.ivProduct);
                break;
            case "17":
                Glide.with(mContext).load(R.mipmap.ic_product_17).into(helper.ivProduct);
                break;
            case "18":
                Glide.with(mContext).load(R.mipmap.ic_product_18).into(helper.ivProduct);
                break;
            case "19":
                Glide.with(mContext).load(R.mipmap.ic_product_19).into(helper.ivProduct);
                break;
            case "20":
                Glide.with(mContext).load(R.mipmap.ic_product_20).into(helper.ivProduct);
                break;
        }

        helper.ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(item);
            }
        });

    }

    /**
     * 加入购物车 对话框
     */
    private void showDialog(final ProductBean bean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("加入购物车");
        builder.setMessage("是否加入购物车?");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //加入数据库
                DBUtils.insert(bean);
                Toast.makeText(mContext, "成功加入购物车", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    static class ViewHolder extends BaseViewHolder {


        ImageView ivProduct;
        TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            ivProduct = view.findViewById(R.id.rv_item_iv);
            tvTitle = view.findViewById(R.id.rv_item_tv);
        }
    }
}
