package com.sistem.meditatii.InterfataGraficaUtilizator.resurseGenerareRapoarte;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseGenerareRapoarte;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseRaport.ResurseGenerareRapoarteModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class ResurseRaportAdapter extends RecyclerView.Adapter<ResurseRaportAdapter.CustomViewHolder> {

    View view;
    private List<ResurseGenerareRapoarteModel_INNER_JOIN> dataList;
    private Context context;
    GetResurseRaportSelectedItem getSelectedItem;

    public ResurseRaportAdapter(List<ResurseGenerareRapoarteModel_INNER_JOIN> dataList, Context context, GetResurseRaportSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView resurseRapoarteRaportNume;
        TextView resurseRapoarteResursaNume;

        ImageButton stergeResursaRapoarte;
        LinearLayout resurseRapoarteBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            resurseRapoarteRaportNume = mView.findViewById(R.id.resurseRapoarteRaportNume);
            resurseRapoarteResursaNume = mView.findViewById(R.id.resurseRapoarteResursaNume);

            stergeResursaRapoarte = mView.findViewById(R.id.stergeResursaRapoarte);
            resurseRapoarteBackground = mView.findViewById(R.id.resurseRapoarteBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_resurse_rapoarte, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;


        holder.resurseRapoarteRaportNume.setText(String.valueOf(dataList.get(position).getNumeRaport()));
        holder.resurseRapoarteResursaNume.setText(String.valueOf(dataList.get(position).getNumeResursa()));

        holder.stergeResursaRapoarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerResurseGenerareRapoarte(context).deleteResursaGenerareRaportById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.resurseRapoarteBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<ResurseGenerareRapoarteModel_INNER_JOIN> plataSesiuneModel) {
        this.dataList = plataSesiuneModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
