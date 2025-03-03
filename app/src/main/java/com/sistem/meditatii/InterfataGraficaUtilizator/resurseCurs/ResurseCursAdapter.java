package com.sistem.meditatii.InterfataGraficaUtilizator.resurseCurs;


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
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseSesiuneMeditatie;
import com.sistem.meditatii.InterfataGraficaUtilizator.resurseMeditatieSesiune.GetResurseMeditatieSesiuneSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseCurs.ResurseCursModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseSesiuneMeditatieModel.ResurseSesiuneMeditatieModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;


public class ResurseCursAdapter extends RecyclerView.Adapter<ResurseCursAdapter.CustomViewHolder> {

    View view;
    private List<ResurseCursModel_INNER_JOIN> dataList;
    private Context context;
    GetResurseCursSelectedItem getSelectedItem;

    public ResurseCursAdapter(List<ResurseCursModel_INNER_JOIN> dataList, Context context, GetResurseCursSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView numeResursaCurs;
        TextView numeResursaNume;

        ImageButton stergeResursaCurs;
        LinearLayout resurseCursBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            numeResursaCurs = mView.findViewById(R.id.numeResursaCurs);
            numeResursaNume = mView.findViewById(R.id.numeResursaNume);

            stergeResursaCurs = mView.findViewById(R.id.stergeResursaCurs);
            resurseCursBackground = mView.findViewById(R.id.resurseCursBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_resurse_curs, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;


        holder.numeResursaCurs.setText(String.valueOf(dataList.get(position).getNumeCurs()));
        holder.numeResursaNume.setText(String.valueOf(dataList.get(position).getNumeResursa()));

        holder.stergeResursaCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteInregistrarePlataById = new TableControllerResurseCurs(context).deleteResursaCursById(dataList.get(position).getId());


                if (deleteInregistrarePlataById) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });
        holder.resurseCursBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<ResurseCursModel_INNER_JOIN> plataSesiuneModel) {
        this.dataList = plataSesiuneModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
