package com.example.erdem.willitriseorfallv2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Erdem on 22.04.2017.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;

    BackgroundTask(Context ctx){


    this.ctx=ctx;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://192.168.1.9//webapp/register.php";


        String method=params[0];

        if(method.equals("register")){

            String name = params[1];
            String password=params[2];
            String email= params[4];
            try {
                URL url = new URL(reg_url);

                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream  OS =  httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =  new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data =URLEncoder.encode("user_name","UTF-8") + "="+URLEncoder.encode(name,"UTF-8")+ "&"+
                        URLEncoder.encode("password","UTF-8") + "="+URLEncoder.encode(password,"UTF-8")+ "&"+
                        URLEncoder.encode("e_mail","UTF-8") + "="+URLEncoder.encode(email,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS =  httpURLConnection.getInputStream();
                IS.close();

                return "Registration Success";



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }







        return null;
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
