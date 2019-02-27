package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erdem on 10.05.2017.
 */
public class TopAccuracyPredictors extends Fragment {

    Spinner itemRankingAccuracy;
    List<String> accuracySpinnerList;
    String itemSituation="OVERALL";
    LinearLayout rankingsAccuracyLayout;
    LinearLayout titleLayoutAccuracy;
    RequestQueue requestQueue;

    String getOverallAccuracyURL = "http://31.145.7.60/willitriseorfallphp/getOverallAccuracy.php";
    String getDollarAccuracyURL = "http://31.145.7.60/willitriseorfallphp/getDollarAccuracy.php";
    String getEuroAccuracy = "http://31.145.7.60/willitriseorfallphp/getEuroAccuracy.php";
    String getPoundAccuracy = "http://31.145.7.60/willitriseorfallphp/getPoundAccuracy.php";
    String getImkbAccuracy = "http://31.145.7.60/willitriseorfallphp/getImkbAccuracy.php";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.accuracypredictors,container,false);

        itemRankingAccuracy = (Spinner) rootView.findViewById(R.id.itemRankingAccuracy);
        rankingsAccuracyLayout=(LinearLayout) rootView.findViewById(R.id.rankingsAccuracyLayout);
        titleLayoutAccuracy=(LinearLayout)rootView.findViewById(R.id.titleLayoutAccuracy);


        final ArrayAdapter<String> adapter1;
        accuracySpinnerList = new ArrayList<String>();
        accuracySpinnerList.add("OVERALL");
        accuracySpinnerList.add("dollar");
        accuracySpinnerList.add("euro");
        accuracySpinnerList.add("pound");
        accuracySpinnerList.add("imkb");


        adapter1= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,accuracySpinnerList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemRankingAccuracy.setAdapter(adapter1);

        adapter1.notifyDataSetChanged();




        itemRankingAccuracy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedString = parent.getItemAtPosition(position).toString();


                rankingsAccuracyLayout.removeAllViews();
                rankingsAccuracyLayout.addView(titleLayoutAccuracy);

                itemSituation=selectedString;
                checkSituation();




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });









        return rootView;
    }


    public void checkSituation(){


        if(itemSituation.equals("OVERALL")){

            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getOverallAccuracyURL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("accuracy");

                            TextView rankingNumberTv = new TextView(getActivity().getApplicationContext());

                            rankingNumberTv.setText(rankingNumber);

                            TextView usernameTv = new TextView(getActivity().getApplicationContext());
                            usernameTv.setText(username);



                            TextView totalScoreTv = new TextView(getActivity().getApplicationContext());
                            totalScoreTv.setText(totalScore);




                            LinearLayout newLine = new LinearLayout(getActivity().getApplicationContext());

                            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1.0f
                            );

                            rankingNumberTv.setLayoutParams(param);
                            usernameTv.setLayoutParams(param);
                            totalScoreTv.setLayoutParams(param);


                            newLine.setOrientation(LinearLayout.HORIZONTAL);
                            newLine.setPadding(100,30,0,30);


                            rankingNumberTv.setPadding(0,0,110,0);
                            totalScoreTv.setPadding(110,0,0,0);


                            newLine.addView(rankingNumberTv);
                            newLine.addView(usernameTv);
                            newLine.addView(totalScoreTv);



                            rankingsAccuracyLayout.addView(newLine);




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




        }


        else if(itemSituation.equals("dollar")){

            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getDollarAccuracyURL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("accuracy");

                            TextView rankingNumberTv = new TextView(getActivity().getApplicationContext());

                            rankingNumberTv.setText(rankingNumber);

                            TextView usernameTv = new TextView(getActivity().getApplicationContext());
                            usernameTv.setText(username);



                            TextView totalScoreTv = new TextView(getActivity().getApplicationContext());
                            totalScoreTv.setText(totalScore);




                            LinearLayout newLine = new LinearLayout(getActivity().getApplicationContext());

                            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1.0f
                            );

                            rankingNumberTv.setLayoutParams(param);
                            usernameTv.setLayoutParams(param);
                            totalScoreTv.setLayoutParams(param);


                            newLine.setOrientation(LinearLayout.HORIZONTAL);
                            newLine.setPadding(100,30,0,30);


                            rankingNumberTv.setPadding(0,0,110,0);
                            totalScoreTv.setPadding(110,0,0,0);


                            newLine.addView(rankingNumberTv);
                            newLine.addView(usernameTv);
                            newLine.addView(totalScoreTv);



                            rankingsAccuracyLayout.addView(newLine);




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




        }


        else if(itemSituation.equals("euro")){

            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getEuroAccuracy, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("accuracy");

                            TextView rankingNumberTv = new TextView(getActivity().getApplicationContext());

                            rankingNumberTv.setText(rankingNumber);

                            TextView usernameTv = new TextView(getActivity().getApplicationContext());
                            usernameTv.setText(username);



                            TextView totalScoreTv = new TextView(getActivity().getApplicationContext());
                            totalScoreTv.setText(totalScore);




                            LinearLayout newLine = new LinearLayout(getActivity().getApplicationContext());

                            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1.0f
                            );

                            rankingNumberTv.setLayoutParams(param);
                            usernameTv.setLayoutParams(param);
                            totalScoreTv.setLayoutParams(param);


                            newLine.setOrientation(LinearLayout.HORIZONTAL);
                            newLine.setPadding(100,30,0,30);


                            rankingNumberTv.setPadding(0,0,110,0);
                            totalScoreTv.setPadding(110,0,0,0);


                            newLine.addView(rankingNumberTv);
                            newLine.addView(usernameTv);
                            newLine.addView(totalScoreTv);



                            rankingsAccuracyLayout.addView(newLine);




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




        }


        else if(itemSituation.equals("pound")){

            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getPoundAccuracy, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("accuracy");

                            TextView rankingNumberTv = new TextView(getActivity().getApplicationContext());

                            rankingNumberTv.setText(rankingNumber);

                            TextView usernameTv = new TextView(getActivity().getApplicationContext());
                            usernameTv.setText(username);



                            TextView totalScoreTv = new TextView(getActivity().getApplicationContext());
                            totalScoreTv.setText(totalScore);




                            LinearLayout newLine = new LinearLayout(getActivity().getApplicationContext());

                            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1.0f
                            );

                            rankingNumberTv.setLayoutParams(param);
                            usernameTv.setLayoutParams(param);
                            totalScoreTv.setLayoutParams(param);


                            newLine.setOrientation(LinearLayout.HORIZONTAL);
                            newLine.setPadding(100,30,0,30);


                            rankingNumberTv.setPadding(0,0,110,0);
                            totalScoreTv.setPadding(110,0,0,0);


                            newLine.addView(rankingNumberTv);
                            newLine.addView(usernameTv);
                            newLine.addView(totalScoreTv);



                            rankingsAccuracyLayout.addView(newLine);




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




        }

        else if(itemSituation.equals("imkb")){

            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getImkbAccuracy, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("accuracy");

                            TextView rankingNumberTv = new TextView(getActivity().getApplicationContext());

                            rankingNumberTv.setText(rankingNumber);

                            TextView usernameTv = new TextView(getActivity().getApplicationContext());
                            usernameTv.setText(username);



                            TextView totalScoreTv = new TextView(getActivity().getApplicationContext());
                            totalScoreTv.setText(totalScore);




                            LinearLayout newLine = new LinearLayout(getActivity().getApplicationContext());

                            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    1.0f
                            );

                            rankingNumberTv.setLayoutParams(param);
                            usernameTv.setLayoutParams(param);
                            totalScoreTv.setLayoutParams(param);


                            newLine.setOrientation(LinearLayout.HORIZONTAL);
                            newLine.setPadding(100,30,0,30);


                            rankingNumberTv.setPadding(0,0,110,0);
                            totalScoreTv.setPadding(110,0,0,0);


                            newLine.addView(rankingNumberTv);
                            newLine.addView(usernameTv);
                            newLine.addView(totalScoreTv);



                            rankingsAccuracyLayout.addView(newLine);




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




        }







    }

}
