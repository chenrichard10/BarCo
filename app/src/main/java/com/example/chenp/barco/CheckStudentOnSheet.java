package com.example.chenp.barco;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CheckStudentOnSheet extends AppCompatActivity implements View.OnClickListener {

    // Create Java variables to store the XML objects.
    EditText studentNumberEditText;
    Button checkStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getSupportActionBar().setTitle("Check Student On Sheet");
        String stuNum = intent.getExtras().getString("password");
        setContentView(R.layout.activity_check_student_on_sheet);

        studentNumberEditText = (EditText) findViewById(R.id.studentNumberToCheckEditText);
        studentNumberEditText.setText(stuNum);
        checkStudentButton = (Button) findViewById(R.id.toCheckButton);
        //Add an on click listener to the button
        checkStudentButton.setOnClickListener(this);
    }

    private void checkStudentOnSheet() {
        final ProgressDialog loading = ProgressDialog.show(this, "Checking student", "Please wait");
        final String studentNumber = studentNumberEditText.getText().toString().trim();
        Intent secondIntent = getIntent();
        // Gets the sheet name
        final String sheetName = secondIntent.getStringExtra("spreadSheetName");

        // Has URL of web app that has access to the Google Sheets
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbz23ZasmP5RpboMtGCrm5fG47BbXvd_8Laki8O0fOn8iF7WIN0/exec",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(CheckStudentOnSheet.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainScreen.class);
                        startActivity(intent);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                // Sends parameters to Google Apps Script
                params.put("action", "checkStudent");
                params.put("studentNumber", studentNumber);
                params.put("sheetName", sheetName);

                return params;
            }
        };

        // Create the timeout length and retry if this time has passed
        int socketTimeOut = 5000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }

    @Override
    public void onClick(View v) {
        // If the 'checkStudentButton' is clicked, it will call the addItemToSheet() function.
        if (v == checkStudentButton) {
            checkStudentOnSheet();
        }
    }
}
