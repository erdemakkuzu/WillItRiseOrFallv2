package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.erdem.willitriseorfallv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erdem on 13.05.2017.
 */
public class ExchangeRates extends Fragment{


    TextView dollartv;
    TextView euroTv;
    TextView poundTv;
    TextView goldTv;
    TextView imkbTv;

    String getExchangeRatesURL ="http://31.145.7.60/willitriseorfallphp/getExchangeRates.php";


    RequestQueue requestQueue;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.piecharttest,container,false);

        dollartv = (TextView)rootView.findViewById(R.id.dollarTv);
        euroTv = (TextView)rootView.findViewById(R.id.euroTv);
        poundTv = (TextView)rootView.findViewById(R.id.poundTv);
        imkbTv = (TextView)rootView.findViewById(R.id.imkbTv);










        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getExchangeRatesURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray predictions = response.getJSONArray("scores");

                    for (int i=0;i<predictions.length();i++){
                        JSONObject prediction = predictions.getJSONObject(i);

                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                1.0f
                        );


                        String itemName =prediction.getString("item_name");

                        String itemValue =prediction.getString("item_value");




                        if(itemName.equals("dollar")){
                            dollartv.append(itemValue);
                        }


                        if(itemName.equals("euro")){
                            euroTv.append(itemValue);
                        }

                        if(itemName.equals("pound")){
                            poundTv.append(itemValue);
                        }

                        if(itemName.equals("imkb")){
                            imkbTv.append(itemValue);
                        }





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



                return hashMap;
            }
        };

        requestQueue.add(jsonObjectRequest);









        return rootView;
    }
}
