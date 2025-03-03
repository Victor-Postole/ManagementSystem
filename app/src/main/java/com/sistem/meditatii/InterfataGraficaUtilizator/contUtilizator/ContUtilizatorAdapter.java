package com.sistem.meditatii.InterfataGraficaUtilizator.contUtilizator;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerContUtilizator;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerParticipareCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.participareCurs.GetParticipareCursSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.ContUtilizator.ContUtilizatorModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class ContUtilizatorAdapter extends RecyclerView.Adapter<ContUtilizatorAdapter.CustomViewHolder> {

    View view;
    private List<ContUtilizatorModel_INNER_JOIN> dataList;
    private Context context;
    GetContUtilizatorSelectedItem getSelectedItem;

    public ContUtilizatorAdapter(List<ContUtilizatorModel_INNER_JOIN> dataList, Context context, GetContUtilizatorSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView cont_utilizator_denumire;
        TextView cont_utilizator_student;

        ImageButton sterge_cont_utilizator;
        LinearLayout contUtilizatorBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            cont_utilizator_denumire = mView.findViewById(R.id.cont_utilizator_denumire);
            cont_utilizator_student = mView.findViewById(R.id.cont_utilizator_student);

            sterge_cont_utilizator = mView.findViewById(R.id.sterge_cont_utilizator);
            contUtilizatorBackground = mView.findViewById(R.id.contUtilizatorBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_cont_utilizator, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;


        holder.cont_utilizator_denumire.setText(String.valueOf(dataList.get(position).getNumeContUtilizator()));
        holder.cont_utilizator_student.setText(String.valueOf(dataList.get(position).getNumeStudent()));

        holder.sterge_cont_utilizator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerContUtilizator(context).deleteContUtilizatorById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.contUtilizatorBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<ContUtilizatorModel_INNER_JOIN> plataSesiuneModel) {
        this.dataList = plataSesiuneModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
