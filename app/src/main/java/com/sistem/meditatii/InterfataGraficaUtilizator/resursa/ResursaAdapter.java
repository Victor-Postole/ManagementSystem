package com.sistem.meditatii.InterfataGraficaUtilizator.resursa;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneMeditatie.GetSesiuneMeditatieSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class ResursaAdapter extends RecyclerView.Adapter<ResursaAdapter.CustomViewHolder> {

    View view;
    private List<InsertResursaDBModel> dataList;
    private Context context;
    GetResursaSelectedItem getSelectedItem;

    public ResursaAdapter(List<InsertResursaDBModel> dataList, Context context, GetResursaSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView nume_resursa;
        TextView cantitate_resursa;
        TextView sesiune_resursa;
        TextView raport_resursa;
        TextView tip_raport_resursa;
        TextView tip_resursa;
        TextView curs_resursa;

        ImageButton stergeResursa;
        LinearLayout resurseBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nume_resursa = mView.findViewById(R.id.nume_resursa);
            cantitate_resursa = mView.findViewById(R.id.cantitate_resursa);
            sesiune_resursa = mView.findViewById(R.id.sesiune_resursa);
            raport_resursa = mView.findViewById(R.id.raport_resursa);
            tip_raport_resursa = mView.findViewById(R.id.tip_raport_resursa);
            tip_resursa = mView.findViewById(R.id.tip_resursa);
            curs_resursa = mView.findViewById(R.id.curs_resursa);


            stergeResursa = mView.findViewById(R.id.stergeResursa);
            resurseBackground = mView.findViewById(R.id.resurseBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_resursa, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;
        holder.nume_resursa.setText(String.valueOf(dataList.get(position).getNumeResursa()));
        holder.cantitate_resursa.setText(String.valueOf(dataList.get(position).getCantitateResursa()));
        holder.sesiune_resursa.setText(dataList.get(position).getResursaSesiune());
        holder.raport_resursa.setText(String.valueOf(dataList.get(position).getResursaRaport()));
        holder.tip_resursa.setText(String.valueOf(dataList.get(position).getTipResursa()));
        holder.curs_resursa.setText(String.valueOf(dataList.get(position).getResursaCurs()));



        holder.stergeResursa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteSesiuneMeditatieById = new TableControllerResursa(context).deleteResursaById(dataList.get(position).getId());
                if (deleteSesiuneMeditatieById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.resurseBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertResursaDBModel> updateResursaDbModel) {
        this.dataList = updateResursaDbModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
