package com.sistem.meditatii.InterfataGraficaUtilizator.utilizator;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerUtilizator;
import com.sistem.meditatii.InterfataGraficaUtilizator.student.GetStudentSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertUtilizatorDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class UtilizatorAdapter extends RecyclerView.Adapter<UtilizatorAdapter.CustomViewHolder> {

    View view;
    private List<InsertUtilizatorDBModel> dataList;
    private Context context;
    GetUtilizatorSelectedItem getSelectedItem;

    public UtilizatorAdapter(List<InsertUtilizatorDBModel> dataList, Context context, GetUtilizatorSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView nume_utilizator;
        TextView email_utilizator;
        TextView dataCreare_utilizator;
        TextView ultimaConectare_utilizator;

        ImageButton stergeUtilizator;
        LinearLayout utilizatorBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nume_utilizator = mView.findViewById(R.id.nume_utilizator);
            email_utilizator = mView.findViewById(R.id.email_utilizator);
            dataCreare_utilizator = mView.findViewById(R.id.dataCreare_utilizator);
            ultimaConectare_utilizator = mView.findViewById(R.id.ultimaConectare_utilizator);

            stergeUtilizator = mView.findViewById(R.id.stergeUtilizator);
            utilizatorBackground = mView.findViewById(R.id.utilizatorBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_utilizator, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.nume_utilizator.setText(String.valueOf(dataList.get(position).getNume()));
        holder.email_utilizator.setText(dataList.get(position).getEmail());
        holder.dataCreare_utilizator.setText(dataList.get(position).getDataCreareCont());
        holder.ultimaConectare_utilizator.setText(String.valueOf(dataList.get(position).getUltimaConectare()));

        holder.stergeUtilizator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteUtilizatorById = new TableControllerUtilizator(context).deleteUtilizatorById(dataList.get(position).getId());


                if (deleteUtilizatorById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.utilizatorBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertUtilizatorDBModel> updateUtilizatoriList) {
        this.dataList = updateUtilizatoriList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
