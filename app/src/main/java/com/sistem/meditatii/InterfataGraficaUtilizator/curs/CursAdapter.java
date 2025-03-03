package com.sistem.meditatii.InterfataGraficaUtilizator.curs;


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
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.InterfataGraficaUtilizator.factura.GetSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class CursAdapter extends RecyclerView.Adapter<CursAdapter.CustomViewHolder> {

    View view;
    private List<InsertCursDBModel> dataList;
    private Context context;
    GetCursSelectedItem getSelectedItem;

    public CursAdapter(List<InsertCursDBModel> dataList, Context context, GetCursSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView nume_curs;
        TextView student_curs;
        TextView meditatie_curs;
        TextView data_curs;
        TextView locatie_curs;
        TextView resursa_curs;
        TextView profesor_curs;


        ImageButton stergeCurs;
        LinearLayout cursBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nume_curs = mView.findViewById(R.id.nume_curs);
            student_curs = mView.findViewById(R.id.student_curs);
            meditatie_curs = mView.findViewById(R.id.meditatie_curs);
            data_curs = mView.findViewById(R.id.data_curs);
            locatie_curs = mView.findViewById(R.id.locatie_curs);
            resursa_curs = mView.findViewById(R.id.resursa_curs);
            profesor_curs = mView.findViewById(R.id.profesor_curs);


            stergeCurs = mView.findViewById(R.id.stergeCurs);
            cursBackground = mView.findViewById(R.id.cursBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_curs, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;
        holder.nume_curs.setText(String.valueOf(dataList.get(position).getNumeCurs()));
        holder.student_curs.setText(dataList.get(position).getStudent());
        holder.meditatie_curs.setText(dataList.get(position).getMeditatie());
        holder.data_curs.setText(String.valueOf(dataList.get(position).getDataCurs()));
        holder.locatie_curs.setText(String.valueOf(dataList.get(position).getLocatieCurs()));
        holder.resursa_curs.setText(String.valueOf(dataList.get(position).getResursa()));
        holder.profesor_curs.setText(String.valueOf(dataList.get(position).getProfesor()));



        holder.stergeCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inserareFactura = new TableControllerCurs(context).deleteCursById(dataList.get(position).getId());
                if (inserareFactura) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.cursBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertCursDBModel> updatedFacturiList) {
        this.dataList = updatedFacturiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
