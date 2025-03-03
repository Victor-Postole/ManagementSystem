package com.sistem.meditatii.InterfataGraficaUtilizator.plata;


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
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertPlataModel;
import com.sistem.meditatii.R;

import java.util.List;


public class PlataAdapter extends RecyclerView.Adapter<PlataAdapter.CustomViewHolder> {

    View view;
    private List<InsertPlataModel> dataList;
    private Context context;
    GetPlataSelectedItem getSelectedItem;

    public PlataAdapter(List<InsertPlataModel> dataList, Context context, GetPlataSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView plata_factura;
        TextView plata_metoda;
        TextView plata_data;
        TextView plata_suma;
        TextView plata_sesiune;

        ImageButton stergePlata;
        LinearLayout plataBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            plata_factura = mView.findViewById(R.id.plata_factura_adapter);
            plata_metoda = mView.findViewById(R.id.plata_metoda_adapter);
            plata_data = mView.findViewById(R.id.plata_data_adapter);
            plata_suma = mView.findViewById(R.id.plata_suma_adapter);
            plata_sesiune = mView.findViewById(R.id.plata_sesiune_adapter);

            stergePlata = mView.findViewById(R.id.stergePlata);
            plataBackground = mView.findViewById(R.id.plataBackground);

        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_plata, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.plata_factura.setText(String.valueOf(dataList.get(position).getFactura()));
        holder.plata_metoda.setText(dataList.get(position).getMetodaPlata());
        holder.plata_data.setText(dataList.get(position).getDataPlata());
        holder.plata_suma.setText(String.valueOf(dataList.get(position).getSuma()));
        holder.plata_sesiune.setText(String.valueOf(dataList.get(position).getSesiuneMeditatii()));

        holder.stergePlata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deletePlataById = new TableControllerPlata(context).deletePlataById(dataList.get(position).getId());

                if (deletePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.plataBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
   }

    public void updateData(List<InsertPlataModel> updatedFacturiList) {
        this.dataList = updatedFacturiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
