package com.example.gitjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class JobActivity extends AppCompatActivity {

    TextView jobTitle, jobDate, jobType, jobLocation, jobCompany, jobDescription;
    ImageView companyLogo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        Job job = (Job) getIntent().getExtras().getSerializable("job");

        jobTitle = findViewById(R.id.jobTitle);
        jobDate = findViewById(R.id.jobDate);
        jobType = findViewById(R.id.jobType);
        jobLocation = findViewById(R.id.jobLocation);
        jobCompany = findViewById(R.id.jobCompany);
        jobDescription = findViewById(R.id.jobDescription);
        companyLogo = findViewById(R.id.companyLogo);

        jobTitle.setText(job.getTitle());
        jobDate.setText(job.getDate());
        jobType.setText(job.getType());
        jobLocation.setText(job.getLocation());
        jobCompany.setText(job.getCompany());
        jobDescription.setText(job.getDescription());

        Picasso.get().load(job.getCompanyLogo()).into(companyLogo);


    }
}