package com.reg.home.kucingjoko.ListCatFile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.reg.home.kucingjoko.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListCatAdapter extends RecyclerView.Adapter<ListCatAdapter.CatViewHolder> {
    Context mCtx;
    List<ListCatConfig> listCatConfigs;
    public ListCatAdapter(Context mCtx,List<ListCatConfig> listCatConfigs){
        this.mCtx=mCtx;
        this.listCatConfigs=listCatConfigs;
    }
    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mCtx);
        View view=layoutInflater.inflate(R.layout.activity_list_cat_view,null);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
    final ListCatConfig listCat= listCatConfigs.get(position);
        holder.txtCatName.setText(listCat.getCat_name());
        holder.txtCatGender.setText(listCat.getCat_gender());
        holder.txtCatType.setText(listCat.getCat_type());
        holder.txtCatColour.setText(listCat.getCat_colour());
        holder.txtCatFood.setText(listCat.getCat_food());
        //holder.btnAddFood.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return listCatConfigs.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCatName,txtCatGender,txtCatType,txtCatColour,txtCatFood;
        private Button btnAddFood;
        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCatName=itemView.findViewById(R.id.txt_cat_name);
            txtCatGender=itemView.findViewById(R.id.txt_cat_gender);
            txtCatType=itemView.findViewById(R.id.txt_cat_type);
            txtCatColour=itemView.findViewById(R.id.txt_cat_color);
            txtCatFood=itemView.findViewById(R.id.txt_cat_food);
            btnAddFood=itemView.findViewById(R.id.btn_give_food);
        }
    }
}
