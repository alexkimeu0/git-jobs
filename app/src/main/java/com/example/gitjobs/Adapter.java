package com.example.gitjobs;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Job> jobs;
    private OnJobListener mOnJobListener;

    public Adapter(Context ctx, List<Job> jobs, OnJobListener onJobListener){
        this.context = ctx;
        this.jobs = jobs;
        this.mOnJobListener = onJobListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_layout, parent, false);
        return new ViewHolder(view, mOnJobListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.jobTitle.setText(jobs.get(position).getTitle());
        holder.jobType.setText(jobs.get(position).getType());
        holder.jobDate.setText(jobs.get(position).getDate());
        holder.jobLocation.setText(jobs.get(position).getLocation());
        holder.jobCompany.setText(jobs.get(position).getCompany());
        holder.jobDescription.setText(jobs.get(position).getDescription());
        Picasso.get().load(jobs.get(position).getCompanyLogo()).into(holder.companyLogo);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView jobTitle, jobType, jobDate, jobLocation, jobCompany, jobDescription;
        ImageView companyLogo;

        Button btnMore;

        OnJobListener onJobListener;

        public ViewHolder(@NonNull View itemView, OnJobListener onJobListener) {
            super(itemView);

            jobTitle = itemView.findViewById(R.id.jobTitle);
            jobDate = itemView.findViewById(R.id.jobDate);
            jobType = itemView.findViewById(R.id.jobType);
            jobLocation = itemView.findViewById(R.id.jobLocation);
            jobCompany = itemView.findViewById(R.id.jobCompany);
            jobDescription = itemView.findViewById(R.id.jobDescription);
            companyLogo = itemView.findViewById(R.id.companyLogo);
            btnMore = itemView.findViewById(R.id.btnMore);
            this.onJobListener = onJobListener;

            btnMore.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onJobListener.OnJobClick(getAdapterPosition());

        }
    }

    public interface OnJobListener{
        void OnJobClick(int position);
    }
}
