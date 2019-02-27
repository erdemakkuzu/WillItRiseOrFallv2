package com.example.erdem.willitriseorfallv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    EditText userName;
    EditText password;
    EditText passworAgain;
    EditText eMail;
    CheckBox checkBox;
    Button submit;
    TextView regTest;
    RequestQueue requestQueue;
    TextView warning;

    String insertUrl = "http://192.168.56.1/willitriseorfall/registerUser.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        passworAgain = (EditText) findViewById(R.id.passwordAgain);
        eMail = (EditText) findViewById(R.id.eMail);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        submit = (Button) findViewById(R.id.submitUserButton);
        regTest = (TextView) findViewById(R.id.regText);
        warning = (TextView) findViewById(R.id.warning);

        warning.setVisibility(View.GONE);

        requestQueue = Volley.newRequestQueue(getApplicationContext());



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String beforehashPassword = password.getText().toString();



                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })  {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<String, String>();

                        parameters.put("userName",userName.getText().toString());
                        parameters.put("password",password.getText().toString());
                        parameters.put("eMail",eMail.getText().toString());

                        return parameters;


                    }
                };


                if(password.getText().toString().equals(passworAgain.getText().toString()) && checkBox.isChecked() && password.getText().length()>=8 && userName.getText().length()>=6){

                    warning.setVisibility(View.GONE);
                    requestQueue.add(request);
                    finish();
                    Intent i = new Intent(getApplicationContext(),StartActivity.class);
                    startActivity(i);
                    finish();



                }else{

                warning.setText("Wrong inputs. Try again!");
                warning.setVisibility(View.VISIBLE);



                }


            }
        });



    }

}