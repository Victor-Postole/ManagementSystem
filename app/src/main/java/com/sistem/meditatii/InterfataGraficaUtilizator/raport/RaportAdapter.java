package com.sistem.meditatii.InterfataGraficaUtilizator.raport;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerPlata;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerRaport;
import com.sistem.meditatii.InterfataGraficaUtilizator.plata.GetPlataSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertPlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertRaportDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class RaportAdapter extends RecyclerView.Adapter<RaportAdapter.CustomViewHolder> {

    View view;
    private List<InsertRaportDBModel> dataList;
    private Context context;
    GetRaportSelectedItem getSelectedItem;

    public RaportAdapter(List<InsertRaportDBModel> dataList, Context context, GetRaportSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView tip_raport;
        TextView data_raport;
        TextView detalii_raport;
        TextView resursa_raport;

        ImageButton stergeRaport;
        LinearLayout raportBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tip_raport = mView.findViewById(R.id.tip_raport);
            data_raport = mView.findViewById(R.id.data_raport);
            detalii_raport = mView.findViewById(R.id.detalii_raport);
            resursa_raport = mView.findViewById(R.id.resursa_raport);

            stergeRaport = mView.findViewById(R.id.stergeRaport);
            raportBackground = mView.findViewById(R.id.raportBackground);

        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_raport, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.tip_raport.setText(String.valueOf(dataList.get(position).getTipRaport()));
        holder.data_raport.setText(dataList.get(position).getDataGenerare());
        holder.detalii_raport.setText(dataList.get(position).getDetalii());
        holder.resursa_raport.setText(String.valueOf(dataList.get(position).getResursa()));

        holder.stergeRaport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deletePlataById = new TableControllerRaport(context).deleteRaportById(dataList.get(position).getId());

                if (deletePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.raportBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
   }

    public void updateData(List<InsertRaportDBModel> updateRaport) {
        this.dataList = updateRaport;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
