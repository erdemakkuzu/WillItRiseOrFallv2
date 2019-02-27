package com.example.erdem.willitriseorfallv2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import fragments.BCrypt;


public class StartActivity extends AppCompatActivity {


    EditText usernameLogin;
    EditText passwordLogin;
    Button loginButton;
    RequestQueue requestQueue;

    TextView forgotPw;

    String userId;
    String username;
    String userPassword;
    String hashedPw;

    private static final String URL ="http://31.145.7.60/willitriseorfallphp/user_control.php";
    private StringRequest request;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        TextView goToSignUp = (TextView) findViewById(R.id.goToSignUp);
        usernameLogin = (EditText) findViewById(R.id.userNameLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        loginButton = (Button) findViewById(R.id.loginButton);
        forgotPw = (TextView) findViewById(R.id.forgotPw);


        requestQueue = Volley.newRequestQueue(this);


        loginButton.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {



                request= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.names().get(0).equals("success")){


                                Toast.makeText(getApplicationContext(),jsonObject.getString("success"),Toast.LENGTH_SHORT);
                                forgotPw.setText(jsonObject.getString("success"));
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                userId=jsonObject.getString("success");
                                hashedPw=jsonObject.getString("hash");
                                username=usernameLogin.getText().toString();


                                if(BCrypt.checkpw(passwordLogin.getText().toString(),hashedPw)){
                                    i.putExtra("username",username);
                                    i.putExtra("userId",userId);

                                    startActivity(i);
                                    finish();
                                }
                                else{
                                    forgotPw.setText("olmadı");

                                }







                            }

                            else{


                                Toast.makeText(getApplicationContext(),"hayır",Toast.LENGTH_LONG);
                                forgotPw.setText("olmadı");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){


                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("username",usernameLogin.getText().toString());
                        hashMap.put("password",passwordLogin.getText().toString());
                        return hashMap;

                    }
                };
                requestQueue.add(request);


            }
        });















        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://31.145.7.60/willitriseorfall/signup";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);







                /*

                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                finish();

                */

            }
        });



        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://31.145.7.60/willitriseorfall/forgettenpassword";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });



       /*
        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
                getSupportActionBar();
            }
        });
*/

    }




}
