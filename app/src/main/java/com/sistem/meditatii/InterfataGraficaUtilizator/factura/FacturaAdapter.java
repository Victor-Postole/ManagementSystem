package com.sistem.meditatii.InterfataGraficaUtilizator.factura;



import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.CustomViewHolder> {

    View view;
    private List<InsertFacturaDBModel> dataList;
    private Context context;
    GetSelectedItem getSelectedItem;

    public FacturaAdapter(List<InsertFacturaDBModel> dataList, Context context, GetSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView id_factura;
        TextView data_emitere_factura;
        TextView status_plata_factura;
        TextView plata_factura;
        TextView suma_totala_factura;
        ImageButton deleteFactura;
        LinearLayout facturaBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            id_factura = mView.findViewById(R.id.id_factura);
            data_emitere_factura = mView.findViewById(R.id.data_emitere_factura);
            status_plata_factura = mView.findViewById(R.id.status_plata_factura);
            plata_factura = mView.findViewById(R.id.plata_factura);
            suma_totala_factura = mView.findViewById(R.id.suma_totala_factura);
            deleteFactura = mView.findViewById(R.id.deleteFactura);
            facturaBackground = mView.findViewById(R.id.facturaBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_factura, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;
        holder.id_factura.setText(String.valueOf(dataList.get(position).getId()));
        holder.data_emitere_factura.setText(dataList.get(position).getDataEmitere());
        holder.status_plata_factura.setText(dataList.get(position).getStatusPlata());
        holder.plata_factura.setText(String.valueOf(dataList.get(position).getPlata()));
        holder.suma_totala_factura.setText(String.valueOf(dataList.get(position).getSumaTotala()));
        holder.deleteFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inserareFactura = new TableControllerFactura(context).deleteFacturaById(dataList.get(position).getId());
                if (inserareFactura) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.facturaBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertFacturaDBModel> updatedFacturiList) {
        this.dataList = updatedFacturiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
