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
public class TopPredictors extends Fragment {


    Spinner itemRanking;


    LinearLayout rankingsLayout;
    LinearLayout titleLayout;

    List<String> itemSpinnerList;



    String itemSituation="OVERALL";





    RequestQueue requestQueue;


    String getOverallScoresURL = "http://31.145.7.60/willitriseorfallphp/getOverallScores.php";

    String getDollarScoresURL = "http://31.145.7.60/willitriseorfallphp/showDollarScores.php";

    String getEuroScoresURL = "http://31.145.7.60/willitriseorfallphp/showEuroScores.php";

    String getPoundScoresURL = "http://31.145.7.60/willitriseorfallphp/showPoundScores.php";

    String getImkbScoresURL = "http://31.145.7.60/willitriseorfallphp/showImkbScores.php";







    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.toppredictors,container,false);

        itemRanking = (Spinner)rootView.findViewById(R.id.itemRanking);
        rankingsLayout= (LinearLayout)rootView.findViewById(R.id.rankings);
        titleLayout = (LinearLayout)rootView.findViewById(R.id.titleLayout);





        final ArrayAdapter<String> adapter1;
        itemSpinnerList = new ArrayList<String>();
        itemSpinnerList.add("OVERALL");
        itemSpinnerList.add("dollar");
        itemSpinnerList.add("euro");
        itemSpinnerList.add("pound");
        itemSpinnerList.add("imkb");


        adapter1= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,itemSpinnerList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemRanking.setAdapter(adapter1);

        adapter1.notifyDataSetChanged();











        itemRanking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedString = parent.getItemAtPosition(position).toString();

                rankingsLayout.removeAllViews();
                rankingsLayout.addView(titleLayout);

                itemSituation=selectedString;
                checkSituation();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                checkSituation();

            }
        });











        return rootView;
    }


    public void checkSituation(){

        if(itemSituation.equals("OVERALL")){



            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getOverallScoresURL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("TOTAL");

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



                            rankingsLayout.addView(newLine);




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


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getDollarScoresURL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("TOTAL");

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



                            rankingsLayout.addView(newLine);




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


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getEuroScoresURL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("TOTAL");

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



                            rankingsLayout.addView(newLine);




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


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getPoundScoresURL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("TOTAL");

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



                            rankingsLayout.addView(newLine);




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


            JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getImkbScoresURL, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {







                    try {
                        JSONArray scores = response.getJSONArray("scores");

                        for (int i=0;i<scores.length();i++){
                            JSONObject score = scores.getJSONObject(i);

                            String rankingNumber = String.valueOf(i+1);

                            String username= score.getString("username");

                            String totalScore = score.getString("TOTAL");

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



                            rankingsLayout.addView(newLine);




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




