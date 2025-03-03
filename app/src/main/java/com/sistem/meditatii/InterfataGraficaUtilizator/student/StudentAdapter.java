package com.sistem.meditatii.InterfataGraficaUtilizator.student;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerAngajat;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.InterfataGraficaUtilizator.angajat.GetAngajatSelectedItem;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.R;

import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.CustomViewHolder> {

    View view;
    private List<InsertStudentDBModel> dataList;
    private Context context;
    GetStudentSelectedItem getSelectedItem;

    public StudentAdapter(List<InsertStudentDBModel> dataList, Context context, GetStudentSelectedItem getSelectedItem){
        this.dataList = dataList;
        this.context = context;
        this.getSelectedItem = getSelectedItem;
    }


    static class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView nume_student;
        TextView prenume_student;
        TextView curs_student;
        TextView telefon_student;
        TextView utilizator_student;

        ImageButton stergeStudent;
        LinearLayout studentBackground;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nume_student = mView.findViewById(R.id.nume_student);
            prenume_student = mView.findViewById(R.id.prenume_student);
            curs_student = mView.findViewById(R.id.curs_student);
            telefon_student = mView.findViewById(R.id.telefon_student);
            utilizator_student = mView.findViewById(R.id.utilizator_student);
            stergeStudent = mView.findViewById(R.id.stergeStudent);
            studentBackground = mView.findViewById(R.id.studentBackground);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_student, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        int counter = position + 1;

        holder.nume_student.setText(String.valueOf(dataList.get(position).getNume()));
        holder.prenume_student.setText(dataList.get(position).getPrenume());
        holder.curs_student.setText(dataList.get(position).getCurs());
        holder.telefon_student.setText(String.valueOf(dataList.get(position).getNumarTelefon()));
        holder.utilizator_student.setText(String.valueOf(dataList.get(position).getUtilizator()));

        holder.stergeStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleteAngajat = new TableControllerStudent(context).deleteStudentById(dataList.get(position).getId());


                if (deleteAngajat) {
                    dataList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataList.size());
                }
            }
        });

        holder.studentBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectedItem.getSelectedItem(dataList.get(position));
            }
        });
    }

    public void updateData(List<InsertStudentDBModel> updatedFacturiList) {
        this.dataList = updatedFacturiList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
