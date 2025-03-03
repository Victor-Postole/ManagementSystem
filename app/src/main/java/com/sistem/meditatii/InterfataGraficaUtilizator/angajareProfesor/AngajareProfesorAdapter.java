package com.sistem.meditatii.InterfataGraficaUtilizator.angajareProfesor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerAngajareProfesor;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerInregistrarePlata;
import com.sistem.meditatii.InterfataGraficaUtilizator.inregistrarePlata.GetInregistrarePlataSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.AngajareProfesor.AngajareProfesorModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InregistrarePlataModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class AngajareProfesorAdapter extends RecyclerView.Adapter<AngajareProfesorAdapter.CustomViewHolder> {

    View view;
    private List<AngajareProfesorModel_INNER_JOIN> dataList;
    private Context context;
    GetAngajareProfesorSelectedItem getSelectedItem;

    public AngajareProfesorAdapter(List<AngajareProfesorModel_INNER_JOIN> dataList, Context context, GetAngajareProfesorSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView angajare_nume_profesor;
        TextView angajare_prenume_profesor;
        TextView angajare_nume_angajat;
        TextView angajare_prenume_angajat;

        ImageButton stergeAngajareProfesor;
        LinearLayout angajareProfesorBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            angajare_nume_profesor = mView.findViewById(R.id.angajare_nume_profesor);
            angajare_prenume_profesor = mView.findViewById(R.id.angajare_prenume_profesor);
            angajare_nume_angajat = mView.findViewById(R.id.angajare_nume_angajat);
            angajare_prenume_angajat = mView.findViewById(R.id.angajare_prenume_angajat);

            stergeAngajareProfesor = mView.findViewById(R.id.stergeAngajareProfesor);
            angajareProfesorBackground = mView.findViewById(R.id.angajareProfesorBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_angajare_profesor, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.angajare_nume_profesor.setText(String.valueOf(dataList.get(position).getNumeProfesor()));
        holder.angajare_prenume_profesor.setText(dataList.get(position).getPrenumeProfesor());
        holder.angajare_nume_angajat.setText(dataList.get(position).getNumeAngajat());
        holder.angajare_prenume_angajat.setText(dataList.get(position).getPrenumeAngajat());


        holder.stergeAngajareProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteAngajareProfesor = new TableControllerAngajareProfesor(context).deleteAngajareProfesorById(dataList.get(position).getId());


                if (deleteAngajareProfesor) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.angajareProfesorBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<AngajareProfesorModel_INNER_JOIN> updateAngajareModelInnerJoin) {
        this.dataList = updateAngajareModelInnerJoin;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
