package com.reg.home.kucingjoko.TopCatFood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reg.home.kucingjoko.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListTopAdapter extends RecyclerView.Adapter<ListTopAdapter.TopView> {
    private List<ListTopConfig> listTopConfigList;
    private Context mCtx;

    public ListTopAdapter(Context mCtx,List<ListTopConfig>listTopConfigList){
        this.listTopConfigList=listTopConfigList;
        this.mCtx=mCtx;

    }
    @NonNull
    @Override
    public TopView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view =inflater.inflate(R.layout.activity_list_top_view,null);
        return new TopView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopView holder, int position) {
        final ListTopConfig listTop=listTopConfigList.get(position);
        holder.txtCatType.setText(listTop.getCat_type());
        holder.txtAmount.setText(listTop.getAmount());
    }

    @Override
    public int getItemCount() {
        return listTopConfigList.size();
    }

    public class TopView extends RecyclerView.ViewHolder {
        private TextView txtCatType,txtAmount;
        public TopView(@NonNull View itemView) {
            super(itemView);
            txtCatType=itemView.findViewById(R.id.txt_show_top_cat);
            txtAmount=itemView.findViewById(R.id.txt_show_top_amount);
        }
    }
}
