package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class SearchUser extends Fragment {


    String searchedUsername;
    Button searchButton;
    EditText searchUserEditText;
    RequestQueue requestQueue;
    String getScoreURL = "http://31.145.7.60/willitriseorfallphp/getSearchedUserOverallScore.php";
    String showPredictionsURL = "http://31.145.7.60/willitriseorfallphp/showPredictions.php";
    TextView overallScoreTv;
    TextView overallAccuracyTv;
    String searchedUserId;
    String [] itemArray = {"","dollar","euro","pound","imkb"};
    LinearLayout predictionLayoutSearch;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.serachuser,container,false);


        searchButton = (Button)rootView.findViewById(R.id.searchButton);
        searchUserEditText =(EditText)rootView.findViewById(R.id.searchUserEditText);
        overallScoreTv=(TextView)rootView.findViewById(R.id.overallScoreTv);
        overallAccuracyTv=(TextView)rootView.findViewById(R.id.overallAccuracyTv);
        predictionLayoutSearch=(LinearLayout)rootView.findViewById(R.id.predictionLayoutSearch);






        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchedUsername=searchUserEditText.getText().toString();



                getUsersScore();
                getUsersPredictions();



            }
        });












        return rootView;
    }

    public void getUsersScore(){

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, getScoreURL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray scores = response.getJSONArray("scores");

                    for (int i=0;i<scores.length();i++){
                        JSONObject score = scores.getJSONObject(i);




                        String userNameControll= score.getString("username");
                        String overallScore= score.getString("TOTAL");
                        String overallAccuracy= score.getString("accuracy");



                        if(userNameControll.equals(searchedUsername)){

                            searchedUserId = score.getString("id");
                            overallScoreTv.setText("Overall Score : "+overallScore);
                            overallAccuracyTv.setText("Overall Accuracy : "+overallAccuracy);



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






    }

    public void getUsersPredictions(){

        predictionLayoutSearch.removeAllViews();

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

                        if(userIdCheck.equals(searchedUserId)) {

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


                            predictionLayoutSearch.addView(nLine);

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





}
