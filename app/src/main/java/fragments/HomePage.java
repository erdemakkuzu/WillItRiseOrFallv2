package fragments;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.erdem.willitriseorfallv2.MainActivity;
import com.example.erdem.willitriseorfallv2.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erdem on 06.03.2017.
 */
public class HomePage extends Fragment {

    TextView welcomeText;
    TextView scoreText;
    String scoreString;
    String accuracyString;
    String scoreUrl = "http://31.145.7.60/willitriseorfallphp/getUserOverallScore.php";
    String predictionNumbersUrl = "http://192.168.56.1/willitriseorfall/getOverallPredictionNumbers.php";
    RequestQueue requestQueue;
    String userId;
    private StringRequest request;
    TextView totalScoreTv;
    int newRightValue;
    int newWrongValue;



    PieChart chart;


    int tahminoran[] = {7,10};

    String tahminIsim[]={"Right Predictions","Wrong Predictions"};


    private static final String showScore = "http://192.168.56.1/willitriseorfall/getScore.php";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_homepage,container,false);

        welcomeText=(TextView)rootView.findViewById(R.id.welcomeText);

        totalScoreTv = (TextView)rootView.findViewById(R.id.totalScoreTv);


        final MainActivity m = (MainActivity)getActivity();

        userId=m.getUserId();

        welcomeText.setText("Welcome "+" "+ m.getUsername());


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());




        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, scoreUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray scores = response.getJSONArray("scores");

                    for (int i=0;i<scores.length();i++){
                        JSONObject score = scores.getJSONObject(i);



                        String userIdControll= score.getString("id");

                        String totalScore = score.getString("TOTAL");

                        if(userIdControll.equals(userId)){

                           totalScoreTv.append(totalScore);
                            break;



                        }else{


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
        });

        requestQueue.add(jsonObjectRequest);

        //get number of predictions

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

         jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, predictionNumbersUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray scores = response.getJSONArray("scores");

                    for (int i=0;i<scores.length();i++){
                        JSONObject score = scores.getJSONObject(i);

                        String userIdControll= score.getString("id");

                        String totalPredictions = score.getString("totalPredictions");

                        String rightPredictions = score.getString("rightPredictions");

                        int wrongPredictions = Integer.parseInt(totalPredictions) - Integer.parseInt(rightPredictions);

                        if(userIdControll.equals(userId)){




                            newRightValue=Integer.parseInt(rightPredictions);
                            newWrongValue=wrongPredictions;

                          //  tahminoran[0]= newRightValue;
                            //tahminoran[1]=newWrongValue;

                          totalScoreTv.append(String.valueOf(newRightValue));
                            totalScoreTv.append(String.valueOf(newWrongValue));



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
        });

        requestQueue.add(jsonObjectRequest);


        //tahminoran[0]= newRightValue;
        //tahminoran[1]=newWrongValue;



        //populating a list of pie entries

        List<PieEntry> pieEntries =  new ArrayList<>();

        for(int i=0; i<tahminoran.length;i++){

            pieEntries.add(new PieEntry( tahminoran[i],tahminIsim[i]));

        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"Tahminler");
        PieData data = new PieData(dataSet);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueLineColor(Color.BLUE);
        dataSet.setValueTextSize(50);

        dataSet.setValueTextColor(Color.BLUE);

        //GET THE CHART

        chart = (PieChart)rootView.findViewById(R.id.chart2);
        chart.setData(data);
        chart.animateY(2000);


        Legend legend = chart.getLegend();

        legend.setEnabled(false);

        chart.invalidate();


        return rootView;


    }
}
