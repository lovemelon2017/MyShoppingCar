package xiaofu.com.buycar_han.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import xiaofu.com.buycar_han.R;
import xiaofu.com.buycar_han.bean.ProductBean;
import xiaofu.com.buycar_han.myinterface.ItemAddOrDeleteLisener;


public class CarAdapter extends BaseQuickAdapter<ProductBean, CarAdapter.ViewHolder> {

    private ItemAddOrDeleteLisener addOrDeleteLisener;

    public void setAddOrDeleteLisener(ItemAddOrDeleteLisener addOrDeleteLisener) {
        this.addOrDeleteLisener = addOrDeleteLisener;
    }

    public CarAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(ViewHolder helper, final ProductBean item) {

        if (item != null) {
            helper.tvTitle.setText(item.getTitle());
            helper.tvDescripe.setText(item.getDescripe());
            helper.tvPrice.setText("¥ " + item.getPrice());
            helper.tvNum.setText(item.getNum() + "");
            setImage(item, helper);

            if (item.getIsCheck()){
                Glide.with(mContext).load(R.mipmap.yes_checked).into(helper.ivCheck);
            }else {
                Glide.with(mContext).load(R.mipmap.no_check).into(helper.ivCheck);
            }
            //减商品
            helper.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = item.getNum();
                    if (num > 1 && addOrDeleteLisener != null) {
                        item.setNum(num - 1);
                        addOrDeleteLisener.change();

                    } else {
                        Toast.makeText(mContext, "把我留在这里吧", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //加商品
            helper.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = item.getNum();
                    if (addOrDeleteLisener != null) {
                        item.setNum(num + 1);
                        addOrDeleteLisener.change();
                    }

                }
            });

        }

    }

    private void setImage(ProductBean item, ViewHolder helper) {
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
        }
    }


    static class ViewHolder extends BaseViewHolder {


        ImageView ivProduct;
        ImageView ivCheck;
        TextView tvTitle;
        TextView tvDescripe;
        TextView tvPrice;
        TextView tvNum;
        TextView tvDelete;
        TextView tvAdd;

        public ViewHolder(View view) {
            super(view);
            ivProduct = view.findViewById(R.id.car_item_iv);
            tvTitle = view.findViewById(R.id.car_title_tv);
            tvDescripe = view.findViewById(R.id.car_title_tv2);
            tvPrice = view.findViewById(R.id.car_price_tv);
            tvNum = view.findViewById(R.id.car_item_num);
            tvDelete = view.findViewById(R.id.car_item_jian);
            tvAdd = view.findViewById(R.id.car_item_jia);
            ivCheck=view.findViewById(R.id.car_check_iv);
        }
    }

}
