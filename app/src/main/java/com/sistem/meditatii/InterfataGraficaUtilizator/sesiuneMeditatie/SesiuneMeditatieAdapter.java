package com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneMeditatie;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.InterfataGraficaUtilizator.curs.GetCursSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class SesiuneMeditatieAdapter extends RecyclerView.Adapter<SesiuneMeditatieAdapter.CustomViewHolder> {

    View view;
    private List<InsertSesiuneMeditatieDBModel> dataList;
    private Context context;
    GetSesiuneMeditatieSelectedItem getSelectedItem;

    public SesiuneMeditatieAdapter(List<InsertSesiuneMeditatieDBModel> dataList, Context context, GetSesiuneMeditatieSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView sesiune_meditatie_plata;
        TextView sesiune_meditatie_data;
        TextView sesiune_meditatie_ora_inceput;
        TextView sesiune_meditatie_ora_sfarsit;
        TextView sesiune_meditatie_curs;
        TextView sesiune_meditatie_resursa;

        ImageButton stergeSesiuneMeditatie;
        LinearLayout sesiuneMeditatieBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            sesiune_meditatie_plata = mView.findViewById(R.id.sesiune_meditatie_plata);
            sesiune_meditatie_data = mView.findViewById(R.id.sesiune_meditatie_data);
            sesiune_meditatie_ora_inceput = mView.findViewById(R.id.sesiune_meditatie_ora_inceput);
            sesiune_meditatie_ora_sfarsit = mView.findViewById(R.id.sesiune_meditatie_ora_sfarsit);
            sesiune_meditatie_curs = mView.findViewById(R.id.sesiune_meditatie_curs);
            sesiune_meditatie_resursa = mView.findViewById(R.id.sesiune_meditatie_resursa);


            stergeSesiuneMeditatie = mView.findViewById(R.id.stergeSesiuneMeditatie);
            sesiuneMeditatieBackground = mView.findViewById(R.id.sesiuneMeditatieBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_sesiune_meditatie, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;
        holder.sesiune_meditatie_plata.setText(String.valueOf(dataList.get(position).getPlata()));
        holder.sesiune_meditatie_data.setText(dataList.get(position).getDataSesiune());
        holder.sesiune_meditatie_ora_inceput.setText(dataList.get(position).getOraInceput());
        holder.sesiune_meditatie_ora_sfarsit.setText(String.valueOf(dataList.get(position).getOraSfarsit()));
        holder.sesiune_meditatie_curs.setText(String.valueOf(dataList.get(position).getCurs()));
        holder.sesiune_meditatie_resursa.setText(String.valueOf(dataList.get(position).getResursa()));



        holder.stergeSesiuneMeditatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteSesiuneMeditatieById = new TableControllerSesiuneMeditatie(context).deleteSesiuneMeditatieById(dataList.get(position).getId());
                if (deleteSesiuneMeditatieById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.sesiuneMeditatieBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertSesiuneMeditatieDBModel> updatedFacturiList) {
        this.dataList = updatedFacturiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
