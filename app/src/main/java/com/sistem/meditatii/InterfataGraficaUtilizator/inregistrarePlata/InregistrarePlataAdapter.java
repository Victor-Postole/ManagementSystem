package com.sistem.meditatii.InterfataGraficaUtilizator.inregistrarePlata;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerInregistrarePlata;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InregistrarePlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InregistrarePlataModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InsertInregistrarePlataDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class InregistrarePlataAdapter extends RecyclerView.Adapter<InregistrarePlataAdapter.CustomViewHolder> {

    View view;
    private List<InregistrarePlataModel_INNER_JOIN> dataList;
    private Context context;
    GetInregistrarePlataSelectedItem getSelectedItem;

    public InregistrarePlataAdapter(List<InregistrarePlataModel_INNER_JOIN> dataList, Context context, GetInregistrarePlataSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView inregistrare_plata_factura;
        TextView inregistrare_plata;

        ImageButton sterge_inregistrare_plata;
        LinearLayout inregistrarePlataBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            inregistrare_plata_factura = mView.findViewById(R.id.inregistrare_plata_factura);
            inregistrare_plata = mView.findViewById(R.id.inregistrare_plata);


            sterge_inregistrare_plata = mView.findViewById(R.id.sterge_inregistrare_plata);
            inregistrarePlataBackground = mView.findViewById(R.id.inregistrarePlataBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_inregistrare_plata, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.inregistrare_plata_factura.setText(String.valueOf(dataList.get(position).getFactural()));
        holder.inregistrare_plata.setText(dataList.get(position).getPlata());


        holder.sterge_inregistrare_plata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerInregistrarePlata(context).deleteInregistrarePlataById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.inregistrarePlataBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InregistrarePlataModel_INNER_JOIN> updateInregistrarePlata) {
        this.dataList = updateInregistrarePlata;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
