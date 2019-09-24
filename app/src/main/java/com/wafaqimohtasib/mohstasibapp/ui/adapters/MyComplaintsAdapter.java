package com.wafaqimohtasib.mohstasibapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wafaqimohtasib.mohstasibapp.R;
import com.wafaqimohtasib.mohstasibapp.data.local.models.MyRequests;

import java.util.ArrayList;
import java.util.List;

public class MyComplaintsAdapter extends RecyclerView.Adapter<MyComplaintsAdapter.MyComplaintsHolder> {

    private List<MyRequests> myRequests = new ArrayList<>();
    private Context context;

    public MyComplaintsAdapter(Context context)
    {
        this.context=context;
    }

    @NonNull
    @Override
    public MyComplaintsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_my_complaints, parent, false);
        return new MyComplaintsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyComplaintsHolder holder, int position) {
        MyRequests currentRequest = myRequests.get(position);
        holder.tvStatus.setText("" + currentRequest.getStatus());
        holder.tvComplaintAgainst.setText(context.getResources().getString(R.string.complaint_againsts)+ " : " + currentRequest.getComplaintAgainst());
        holder.tvSubject.setText(context.getResources().getString(R.string.subject)+  " : " + currentRequest.getSubject());
        holder.tvDate.setText(context.getResources().getString(R.string.date)+  " : " + currentRequest.getDate());
        if(currentRequest.getStatus().equals("1"))
        {
            holder.tvStatus.setText("Pending");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.dark_red));
        }
        else if(currentRequest.getStatus().equals("2"))
        {
            holder.tvStatus.setText("Approved");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.dark_green));
        }
        else if(currentRequest.getStatus().equals("3"))
        {
            holder.tvStatus.setText("Rejected");
            holder.tvStatus.setTextColor(context.getResources().getColor(R.color.dark_brown));
        }
    }
    @Override
    public int getItemCount() {
        return myRequests.size();
    }

    public void setMyRequests(List<MyRequests> myRequests) {
        this.myRequests=myRequests;
        notifyDataSetChanged();
    }

    class MyComplaintsHolder extends RecyclerView.ViewHolder {

        private TextView tvComplaintAgainst,tvStatus,tvSubject,tvDate;
        public MyComplaintsHolder(View itemView) {
            super(itemView);
            tvStatus= itemView.findViewById(R.id.tv_status);
            tvComplaintAgainst = itemView.findViewById(R.id.tv_complaints_againts);
            tvSubject = itemView.findViewById(R.id.tv_subject);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}