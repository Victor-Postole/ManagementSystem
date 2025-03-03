package com.sistem.meditatii.InterfataGraficaUtilizator.predareCurs;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPredareCurs;
import com.sistem.meditatii.ModeleInterogareBazaDate.PredareCursuri.PredareCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class PredareCursAdapter extends RecyclerView.Adapter<PredareCursAdapter.CustomViewHolder> {

    View view;
    private List<PredareCursModel_INNER_JOIN> dataList;
    private Context context;
    GetPredareCursSelectedItem getSelectedItem;

    public PredareCursAdapter(List<PredareCursModel_INNER_JOIN> dataList, Context context, GetPredareCursSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView predare_curs_nume_profesor;
        TextView predare_curs_prenume_profesor;
        TextView predare_curs_denumire;

        ImageButton stergePredareCurs;
        LinearLayout predareCursBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            predare_curs_nume_profesor = mView.findViewById(R.id.predare_curs_nume_profesor);
            predare_curs_prenume_profesor = mView.findViewById(R.id.predare_curs_prenume_profesor);
            predare_curs_denumire = mView.findViewById(R.id.predare_curs_denumire);

            stergePredareCurs = mView.findViewById(R.id.stergePredareCurs);
            predareCursBackground = mView.findViewById(R.id.predareCursBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_predare_curs, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.predare_curs_nume_profesor.setText(String.valueOf(dataList.get(position).getNumeProfesor()));
        holder.predare_curs_prenume_profesor.setText(dataList.get(position).getPrenumeProfesor());
        holder.predare_curs_denumire.setText(dataList.get(position).getNumeCurs());


        holder.stergePredareCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerPredareCurs(context).deletePredareCursById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.predareCursBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<PredareCursModel_INNER_JOIN> predareCursModels) {
        this.dataList = predareCursModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
