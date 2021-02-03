package com.example.gitjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JobsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Job> jobs;
    Adapter adapter;

    String URL = "https://jobs.github.com/positions.json?markdown=true";
    RequestQueue requestQueue;
    SweetAlertDialog errorDialog, successDialog, progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jobs);

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.jobsList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobs = new ArrayList<>();

        errorDialog = new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE);
        successDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        extractJobs();

    }

    private void extractJobs() {

        progressDialog.setTitle("Loading jobs");
        progressDialog.setTitleText("Keep calm");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                try {

                    for (int i = 0; i < response.length(); i++) {

                    JSONObject jobObject = response.getJSONObject(i);
                    String title = jobObject.getString("title");
                    String date = jobObject.getString("created_at");
                    String type = jobObject.getString("type");
                    String location = jobObject.getString("location");
                    String company = jobObject.getString("company");
                    String description = jobObject.getString("description");
                    String companyLogo = jobObject.getString("company_logo");


                    jobs.add(new Job(title, date, type, location, company, description, companyLogo));

                }
                  adapter = new Adapter(JobsActivity.this, jobs);
                  recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Tag", "onErrorResponse" + error.getMessage());
                progressDialog.dismiss();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}