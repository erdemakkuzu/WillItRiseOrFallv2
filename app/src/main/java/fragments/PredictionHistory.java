package fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.erdem.willitriseorfallv2.MainActivity;
import com.example.erdem.willitriseorfallv2.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erdem on 27.04.2017.
 */
public class PredictionHistory extends android.support.v4.app.Fragment {

    Spinner historySpinner;
    String userId;
    List<String> list;
    TextView showPredictsText;

    LinearLayout titleLayout;
    LinearLayout topLayout;



    LinearLayout predictionsLayout;

    String currentItem;

    RequestQueue requestQueue;
    private StringRequest request;

    String showPredictionsURL = "http://31.145.7.60/willitriseorfallphp/showPredictions.php";

    String [] itemArray = {"","dollar","euro","pound","imkb"};


    TextView itemText;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.predictionhistory,container,false);

        historySpinner= (Spinner)rootView.findViewById(R.id.historySpinner);
        predictionsLayout= (LinearLayout)rootView.findViewById(R.id.predictions);
        topLayout= (LinearLayout)rootView.findViewById(R.id.topLayout);


        titleLayout = (LinearLayout)rootView.findViewById(R.id.titleLayout);

        final ArrayAdapter<String> adapter;
        list = new ArrayList<String>();
        list.add("ALL");
        list.add("dollar");
        list.add("euro");
        list.add("pound");
        list.add("imkb");

        adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        historySpinner.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        MainActivity m = new MainActivity();
        userId=m.getUserId();









        historySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedString = parent.getItemAtPosition(position).toString();

                if(selectedString.equals("ALL")){

                    predictionsLayout.removeAllViews();

                    predictionsLayout.addView(titleLayout);

                    allPredictions();


                } else if(selectedString.equals("dollar")){

                    currentItem = "dollar";
                    predictionsLayout.removeAllViews();
                    predictionsLayout.addView(titleLayout);
                    onlySpecificPredictions();

                } else if(selectedString.equals("euro")){

                    currentItem = "euro";
                    predictionsLayout.removeAllViews();
                    predictionsLayout.addView(titleLayout);
                    onlySpecificPredictions();


                }
                else if(selectedString.equals("pound")){

                    currentItem = "pound";
                    predictionsLayout.removeAllViews();
                    predictionsLayout.addView(titleLayout);
                    onlySpecificPredictions();


                }
                else if(selectedString.equals("imkb")){

                    currentItem = "imkb";
                    predictionsLayout.removeAllViews();
                    predictionsLayout.addView(titleLayout);
                    onlySpecificPredictions();


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });































        return rootView;


    }




    public void allPredictions(){

        userId=getActivity().getIntent().getStringExtra("userId");




        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, showPredictionsURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray predictions = response.getJSONArray("predictions");

                    for (int i=0;i<predictions.length();i++){
                        JSONObject prediction = predictions.getJSONObject(i);

                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                1.0f
                        );

                        String item= prediction.getString("item_id");
                        int itemIndex = Integer.parseInt(item);

                        String userIdCheck =prediction.getString("user_id");

                        String point= prediction.getString("prediction_point");

                        String result= prediction.getString("prediction_result");

                        String score= prediction.getString("prediction_final_score");

                       if(userIdCheck.equals(userId)) {

                           TextView itemTv = new TextView(getActivity().getApplicationContext());


                           itemTv.setText(itemArray[itemIndex]);
                           itemTv.setTextColor(Color.CYAN);

                           itemTv.setLayoutParams(param);


                           TextView pointTv = new TextView(getActivity().getApplicationContext());

                           pointTv.setText(point);

                           if (point.equals("rise")) {

                               pointTv.setTextColor(Color.GREEN);
                           } else if (point.equals("fall")) {

                               pointTv.setTextColor(Color.RED);

                           }


                           pointTv.setLayoutParams(param);

                           TextView resultTv = new TextView(getActivity().getApplicationContext());

                           if (result.equals("null")) {
                               result = "waiting";
                           }

                           if (score.equals("null")) {
                               score = "waiting";
                           }




                           resultTv.setText(result);


                           if (result.equals("rise")) {

                               resultTv.setTextColor(Color.GREEN);
                           } else if (result.equals("fal")) {

                               resultTv.setTextColor(Color.RED);

                           }





                           resultTv.setLayoutParams(param);

                           TextView scoreTv = new TextView(getActivity().getApplicationContext());

                           scoreTv.setText(score);
                           scoreTv.setLayoutParams(param);


                           LinearLayout nLine = new LinearLayout(getActivity().getApplicationContext());


                           nLine.setOrientation(LinearLayout.HORIZONTAL);
                           nLine.setPadding(60, 20, 20, 20);

                           nLine.addView(itemTv);
                           nLine.addView(pointTv);
                           nLine.addView(resultTv);
                           nLine.addView(scoreTv);


                           predictionsLayout.addView(nLine);

                       }
                        else{


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






    }



    public void onlySpecificPredictions(){





        userId=getActivity().getIntent().getStringExtra("userId");




        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());





        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, showPredictionsURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {







                try {
                    JSONArray predictions = response.getJSONArray("predictions");

                    for (int i=0;i<predictions.length();i++){
                        JSONObject prediction = predictions.getJSONObject(i);

                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                1.0f
                        );

                        String item= prediction.getString("item_id");
                        int itemIndex = Integer.parseInt(item);

                        String userIdCheck =prediction.getString("user_id");

                        String point= prediction.getString("prediction_point");

                        String result= prediction.getString("prediction_result");

                        String score= prediction.getString("prediction_final_score");

                        if(userIdCheck.equals(userId) && itemArray[itemIndex].equals(currentItem)  ) {

                            TextView itemTv = new TextView(getActivity().getApplicationContext());


                            itemTv.setText(itemArray[itemIndex]);
                            itemTv.setTextColor(Color.CYAN);

                            itemTv.setLayoutParams(param);


                            TextView pointTv = new TextView(getActivity().getApplicationContext());

                            pointTv.setText(point);

                            if (point.equals("rise")) {

                                pointTv.setTextColor(Color.GREEN);
                            } else if (point.equals("fall")) {

                                pointTv.setTextColor(Color.RED);

                            }


                            pointTv.setLayoutParams(param);

                            TextView resultTv = new TextView(getActivity().getApplicationContext());

                            if (result.equals("null")) {
                                result = "waiting";
                            }

                            if (score.equals("null")) {
                                score = "waiting";
                            }




                            resultTv.setText(result);


                            if (result.equals("rise")) {

                                resultTv.setTextColor(Color.GREEN);
                            } else if (result.equals("fall")) {

                                resultTv.setTextColor(Color.RED);

                            }





                            resultTv.setLayoutParams(param);

                            TextView scoreTv = new TextView(getActivity().getApplicationContext());

                            scoreTv.setText(score);
                            scoreTv.setLayoutParams(param);


                            LinearLayout nLine = new LinearLayout(getActivity().getApplicationContext());


                            nLine.setOrientation(LinearLayout.HORIZONTAL);
                            nLine.setPadding(60, 20, 20, 20);

                            nLine.addView(itemTv);
                            nLine.addView(pointTv);
                            nLine.addView(resultTv);
                            nLine.addView(scoreTv);


                            predictionsLayout.addView(nLine);

                        }
                        else{


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

                // hashMap.put("userId",userId);
                return hashMap;
            }
        };

        requestQueue.add(jsonObjectRequest);













    }





}


