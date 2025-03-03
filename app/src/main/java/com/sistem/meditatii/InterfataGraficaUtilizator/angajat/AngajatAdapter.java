package com.sistem.meditatii.InterfataGraficaUtilizator.angajat;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerAngajat;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.InterfataGraficaUtilizator.factura.GetSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class AngajatAdapter extends RecyclerView.Adapter<AngajatAdapter.CustomViewHolder> {

    View view;
    private List<InsertAngajatDBModel> dataList;
    private Context context;
    GetAngajatSelectedItem getSelectedItem;

    public AngajatAdapter(List<InsertAngajatDBModel> dataList, Context context, GetAngajatSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView nume_angajat_adapter;
        TextView prenume_angajat_adapter;
        TextView cnp_angajat_adapter;
        TextView telefon_angajat_adapter;
        TextView email_angajat_adapter;

        ImageButton stergeAngajat;
        LinearLayout angajatBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nume_angajat_adapter = mView.findViewById(R.id.nume_angajat_adapter);
            prenume_angajat_adapter = mView.findViewById(R.id.prenume_angajat_adapter);
            cnp_angajat_adapter = mView.findViewById(R.id.cnp_angajat_adapter);
            telefon_angajat_adapter = mView.findViewById(R.id.telefon_angajat_adapter);
            email_angajat_adapter = mView.findViewById(R.id.email_angajat_adapter);
            stergeAngajat = mView.findViewById(R.id.stergeAngajat);
            angajatBackground = mView.findViewById(R.id.angajatBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_angajat, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.nume_angajat_adapter.setText(String.valueOf(dataList.get(position).getNume()));
        holder.prenume_angajat_adapter.setText(dataList.get(position).getPrenume());
        holder.cnp_angajat_adapter.setText(dataList.get(position).getCnp());
        holder.telefon_angajat_adapter.setText(String.valueOf(dataList.get(position).getTelefon()));
        holder.email_angajat_adapter.setText(String.valueOf(dataList.get(position).getEmail()));

        holder.stergeAngajat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteAngajat = new TableControllerAngajat(context).deleteAngajatById(dataList.get(position).getId());

                if (deleteAngajat) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.angajatBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertAngajatDBModel> updatedFacturiList) {
        this.dataList = updatedFacturiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
