package com.sistem.meditatii.InterfataGraficaUtilizator.participareCurs;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerParticipareCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.ModeleInterogareBazaDate.ParticipareCurs.ParticipareCursModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class ParticipareCursAdapter extends RecyclerView.Adapter<ParticipareCursAdapter.CustomViewHolder> {

    View view;
    private List<ParticipareCursModel_INNER_JOIN> dataList;
    private Context context;
    GetParticipareCursSelectedItem getSelectedItem;

    public ParticipareCursAdapter(List<ParticipareCursModel_INNER_JOIN> dataList, Context context, GetParticipareCursSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView participare_nume_curs;
        TextView participare_nume_student;

        ImageButton sterge_participare_curs;
        LinearLayout participareCursBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            participare_nume_curs = mView.findViewById(R.id.participare_nume_curs);
            participare_nume_student = mView.findViewById(R.id.participare_nume_student);

            sterge_participare_curs = mView.findViewById(R.id.sterge_participare_curs);
            participareCursBackground = mView.findViewById(R.id.participareCursBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_participare_curs, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;


        holder.participare_nume_curs.setText(String.valueOf(dataList.get(position).getNumeCurs()));
        holder.participare_nume_student.setText(String.valueOf(dataList.get(position).getNumeStudent()));

        holder.sterge_participare_curs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerParticipareCurs(context).deleteParticipareCursById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.participareCursBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<ParticipareCursModel_INNER_JOIN> plataSesiuneModel) {
        this.dataList = plataSesiuneModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
