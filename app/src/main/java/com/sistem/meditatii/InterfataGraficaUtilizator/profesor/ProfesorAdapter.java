package com.sistem.meditatii.InterfataGraficaUtilizator.profesor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerProfesor;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class ProfesorAdapter extends RecyclerView.Adapter<ProfesorAdapter.CustomViewHolder> {

    View view;
    private List<InsertFacturaDBModel.InsertProfesorDBModel> dataList;
    private Context context;
    GetProfesorSelectedItem getSelectedItem;

    public ProfesorAdapter(List<InsertFacturaDBModel.InsertProfesorDBModel> dataList, Context context, GetProfesorSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView nume_profesor_adapter;
        TextView prenume_profesor_adapter;
        TextView angajat_profesor_adapter;
        TextView telefon_profesor_adapter;
        TextView email_profesor_adapter;

        ImageButton stergeProfesor;
        LinearLayout profesorBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nume_profesor_adapter = mView.findViewById(R.id.nume_profesor_adapter);
            prenume_profesor_adapter = mView.findViewById(R.id.prenume_profesor_adapter);
            angajat_profesor_adapter = mView.findViewById(R.id.angajat_profesor_adapter);
            telefon_profesor_adapter = mView.findViewById(R.id.telefon_profesor_adapter);
            email_profesor_adapter = mView.findViewById(R.id.email_profesor_adapter);
            stergeProfesor = mView.findViewById(R.id.stergeProfesor);
            profesorBackground = mView.findViewById(R.id.profesorBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_profesor, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.nume_profesor_adapter.setText(String.valueOf(dataList.get(position).getNume()));
        holder.prenume_profesor_adapter.setText(dataList.get(position).getPrenume());
        holder.angajat_profesor_adapter.setText(dataList.get(position).getAngajat());
        holder.telefon_profesor_adapter.setText(String.valueOf(dataList.get(position).getTelefon()));
        holder.email_profesor_adapter.setText(String.valueOf(dataList.get(position).getEmail()));

        holder.stergeProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteProfesorById = new TableControllerProfesor(context).deleteProfesorById(dataList.get(position).getId());

                if (deleteProfesorById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.profesorBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertFacturaDBModel.InsertProfesorDBModel> updatedFacturiList) {
        this.dataList = updatedFacturiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
