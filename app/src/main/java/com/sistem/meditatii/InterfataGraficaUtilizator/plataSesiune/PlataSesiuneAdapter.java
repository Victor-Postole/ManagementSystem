package com.sistem.meditatii.InterfataGraficaUtilizator.plataSesiune;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPlataSesiune;
import com.sistem.meditatii.ModeleInterogareBazaDate.PlataSesiune.PlataSesiuneModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class PlataSesiuneAdapter extends RecyclerView.Adapter<PlataSesiuneAdapter.CustomViewHolder> {

    View view;
    private List<PlataSesiuneModel_INNER_JOIN> dataList;
    private Context context;
    GetPlataSesiuneSelectedItem getSelectedItem;

    public PlataSesiuneAdapter(List<PlataSesiuneModel_INNER_JOIN> dataList, Context context, GetPlataSesiuneSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView plata_sesiune;
        TextView sesiune_meditatie;

        ImageButton stergePlataSesiune;
        LinearLayout plataSesiuneBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            plata_sesiune = mView.findViewById(R.id.plata_sesiune);
            sesiune_meditatie = mView.findViewById(R.id.sesiune_meditatie);

            stergePlataSesiune = mView.findViewById(R.id.stergePlataSesiune);
            plataSesiuneBackground = mView.findViewById(R.id.plataSesiuneBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_plata_sesiune, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.plata_sesiune.setText(String.valueOf(dataList.get(position).getIdPlata()));
        holder.sesiune_meditatie.setText(dataList.get(position).getIdSesiuneMeditatie());

        holder.stergePlataSesiune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerPlataSesiune(context).deletePlataSesiuneById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.plataSesiuneBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<PlataSesiuneModel_INNER_JOIN> plataSesiuneModel) {
        this.dataList = plataSesiuneModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
