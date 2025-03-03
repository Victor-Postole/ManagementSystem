package com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneCurs;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class SesiuneCursAdapter extends RecyclerView.Adapter<SesiuneCursAdapter.CustomViewHolder> {

    View view;
    private List<SesiuneCursModel_INNER_JOIN> dataList;
    private Context context;
    GetSesiuneCursSelectedItem getSelectedItem;

    public SesiuneCursAdapter(List<SesiuneCursModel_INNER_JOIN> dataList, Context context, GetSesiuneCursSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView sesiune_nume_curs;
        TextView sesiune_curs_meditatie;

        ImageButton stergeSesiuneCurs;
        LinearLayout sesiuneCursBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            sesiune_nume_curs = mView.findViewById(R.id.sesiune_nume_curs);
            sesiune_curs_meditatie = mView.findViewById(R.id.sesiune_curs_meditatie);

            stergeSesiuneCurs = mView.findViewById(R.id.stergeSesiuneCurs);
            sesiuneCursBackground = mView.findViewById(R.id.sesiuneCursBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_sesiune_curs, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;


        holder.sesiune_nume_curs.setText(String.valueOf(dataList.get(position).getNume_curs()));
        holder.sesiune_curs_meditatie.setText(String.valueOf(dataList.get(position).getIdSesiune()));

        holder.stergeSesiuneCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerSesiuneCurs(context).deleteSesiuneCursById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.sesiuneCursBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<SesiuneCursModel_INNER_JOIN> plataSesiuneModel) {
        this.dataList = plataSesiuneModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
