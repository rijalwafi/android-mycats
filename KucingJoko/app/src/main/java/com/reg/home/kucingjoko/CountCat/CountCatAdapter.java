package com.reg.home.kucingjoko.CountCat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reg.home.kucingjoko.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountCatAdapter extends RecyclerView.Adapter<CountCatAdapter.CountCatView> {
    private List<CountCatConfig> countCatConfigs;
    private Context mCtx;
    public CountCatAdapter(Context mCtx, List<CountCatConfig>countCatConfigs ){
        this.countCatConfigs=countCatConfigs;
        this.mCtx=mCtx;
    }

    @NonNull
    @Override
    public CountCatAdapter.CountCatView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mCtx);
        View view=layoutInflater.inflate(R.layout.activity_count_view_cat,null);
        return new CountCatView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountCatAdapter.CountCatView holder, int position) {
    final CountCatConfig count=countCatConfigs.get(position);
    holder.tvCatType.setText(count.getCat_type());
    holder.tvCatGender.setText(count.getCat_gender());
    holder.tvNumberCat.setText(count.getNumber_of_cat());
    }

    @Override
    public int getItemCount() {
        return countCatConfigs.size();
    }

    public class CountCatView extends RecyclerView.ViewHolder {
        private TextView tvCatType,tvCatGender,tvNumberCat;
        public CountCatView(@NonNull View itemView) {
            super(itemView);
            tvCatType=itemView.findViewById(R.id.txt_count_cat_type);
            tvCatGender=itemView.findViewById(R.id.txt_count_car_gender);
            tvNumberCat=itemView.findViewById(R.id.txt_count_catt);
        }
    }
}
