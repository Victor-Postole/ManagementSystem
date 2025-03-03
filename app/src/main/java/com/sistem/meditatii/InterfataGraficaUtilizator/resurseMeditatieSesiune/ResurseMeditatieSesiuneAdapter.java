package com.sistem.meditatii.InterfataGraficaUtilizator.resurseMeditatieSesiune;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneCurs.GetSesiuneCursSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseSesiuneMeditatieModel.ResurseSesiuneMeditatieModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseSesiuneMeditatieModel.ResurseSesiuneMeditatieModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class ResurseMeditatieSesiuneAdapter extends RecyclerView.Adapter<ResurseMeditatieSesiuneAdapter.CustomViewHolder> {

    View view;
    private List<ResurseSesiuneMeditatieModel_INNER_JOIN> dataList;
    private Context context;
    GetResurseMeditatieSesiuneSelectedItem getSelectedItem;

    public ResurseMeditatieSesiuneAdapter(List<ResurseSesiuneMeditatieModel_INNER_JOIN> dataList, Context context, GetResurseMeditatieSesiuneSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView resurse_sesiune_meditatie_nume;
        TextView resurse_sesiune_meditatie_nume_reursa;

        ImageButton stergeSesiuneMeditatie;
        LinearLayout resurseMeditatieSesiuneBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            resurse_sesiune_meditatie_nume = mView.findViewById(R.id.resurse_sesiune_meditatie_nume);
            resurse_sesiune_meditatie_nume_reursa = mView.findViewById(R.id.resurse_sesiune_meditatie_nume_reursa);

            stergeSesiuneMeditatie = mView.findViewById(R.id.stergeSesiuneMeditatie);
            resurseMeditatieSesiuneBackground = mView.findViewById(R.id.resurseMeditatieSesiuneBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_resurse_meditatie_sesiune, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;


        holder.resurse_sesiune_meditatie_nume.setText(String.valueOf(dataList.get(position).getIdSesiuneMeditatie()));
        holder.resurse_sesiune_meditatie_nume_reursa.setText(String.valueOf(dataList.get(position).getNumeResursa()));

        holder.stergeSesiuneMeditatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerResurseSesiuneMeditatie(context).deleteResursaSesiuneMeditatieById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.resurseMeditatieSesiuneBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<ResurseSesiuneMeditatieModel_INNER_JOIN> plataSesiuneModel) {
        this.dataList = plataSesiuneModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
