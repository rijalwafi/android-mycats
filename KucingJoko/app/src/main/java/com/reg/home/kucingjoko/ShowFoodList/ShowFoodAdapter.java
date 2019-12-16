package com.reg.home.kucingjoko.ShowFoodList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reg.home.kucingjoko.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShowFoodAdapter extends RecyclerView.Adapter<ShowFoodAdapter.ShowFoodCat> {
    private List<ShowFoodConfig> showFoodConfigList;
    private Context mCtx;
    public ShowFoodAdapter(Context mCtx,List<ShowFoodConfig>showFoodConfigList){
        this.showFoodConfigList=showFoodConfigList;
        this.mCtx=mCtx;
    }
    @NonNull
    @Override
    public ShowFoodCat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view =inflater.inflate(R.layout.activity_show_food_view,null);
        return new ShowFoodCat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowFoodCat holder, int position) {
        final ShowFoodConfig showFood=showFoodConfigList.get(position);
        holder.txtTypeCat.setText(showFood.getCat_type());
        holder.txtTypeFood.setText(showFood.getCat_food());
        holder.txtAmount.setText(showFood.getAmount());

    }

    @Override
    public int getItemCount() {
        return showFoodConfigList.size();
    }

    public class ShowFoodCat extends RecyclerView.ViewHolder {
        private TextView txtTypeCat,txtTypeFood,txtAmount;

        public ShowFoodCat(@NonNull View itemView) {
            super(itemView);
            txtTypeCat=itemView.findViewById(R.id.txt_show_cat_type);
            txtTypeFood=itemView.findViewById(R.id.txt_show_cat_food);
            txtAmount=itemView.findViewById(R.id.txt_show_cat_amount);
        }
    }
}
