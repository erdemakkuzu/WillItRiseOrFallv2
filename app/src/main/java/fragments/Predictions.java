package fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.example.erdem.willitriseorfallv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Erdem on 07.03.2017.
 */
public class Predictions extends Fragment {

    String INSTURMENT;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    Spinner predictSpinner;
    String[] insturments = {"dollar","euro","pound","imkb"};
    String[] currentValueArray = {"3.50","4.11","4.90","94394"};
    ArrayAdapter<CharSequence> adapter;
    ImageView insturmentSign;
    LinearLayout predictLayout;
    LinearLayout buttonLayouts;
    TextView predictionText;

    Button submitPrediction;
    Button updatePrediction;

    String userId;
    String predictionId;

    private StringRequest request;


    EditText commentText;
    TextView currentValue;
    ImageButton riseButton;
    ImageButton stableButton;
    ImageButton fallButton;
    TextView testText;
    RequestQueue requestQueue;

    String showItemUrl = "http://31.145.7.60/willitriseorfallphp/item_controll.php";

    String showCurrentValueUrl="http://31.145.7.60/willitriseorfallphp/showCurrentValues.php";

    String submitPredictionUrl="http://31.145.7.60/willitriseorfallphp/submitPrediction.php";

    String controllPredictionUrl="http://31.145.7.60/willitriseorfallphp/predictionControll.php";

    String updatePredictionUrl="http://31.145.7.60/willitriseorfallphp/updatePrediction.php";



    List<String> list;

    int itemId;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragmentpredictions,container,false);

        userId=getActivity().getIntent().getStringExtra("userId");

        commentText=(EditText)rootView.findViewById(R.id.commentText);

        insturmentSign=(ImageView)rootView.findViewById(R.id.materialSign) ;
        predictLayout=(LinearLayout)rootView.findViewById(R.id.predictionLayout);
        buttonLayouts=(LinearLayout)rootView.findViewById(R.id.buttonLayouts);

        currentValue=(TextView)rootView.findViewById(R.id.currentValue);
        riseButton=(ImageButton)rootView.findViewById(R.id.riseButton);
        stableButton=(ImageButton)rootView.findViewById(R.id.stableButton);
        fallButton=(ImageButton)rootView.findViewById(R.id.fallButton);
        testText=(TextView)rootView.findViewById(R.id.testText);

        predictionText=(TextView)rootView.findViewById(R.id.predictionText);

        submitPrediction = (Button)rootView.findViewById(R.id.submitPrediction);
        updatePrediction= (Button)rootView.findViewById(R.id.updatePrediction);


        predictLayout.setVisibility(View.GONE);
        insturmentSign.setVisibility(View.GONE);
        currentValue.setVisibility(View.GONE);
        buttonLayouts.setVisibility(View.GONE);
        commentText.setVisibility(View.GONE);




        predictSpinner = (Spinner) rootView.findViewById(R.id.predictSpinner);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(),R.array.insturmentsArray,R.layout.support_simple_spinner_dropdown_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);




        final ArrayAdapter<String> adapter;




        list = new ArrayList<String>();

        list.add("Select from here");

        adapter= new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        predictSpinner.setAdapter(adapter);


        adapter.notifyDataSetChanged();



        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        testText.setText("Select item to predict!");
        ///////SHOWING ITEMS


        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, showItemUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {







                try {
                    JSONArray items = response.getJSONArray("items");

                    for (int i=0;i<items.length();i++){
                        JSONObject item = items.getJSONObject(i);

                        String itemName= item.getString("item_name");

                        list.add(itemName);


                    }
                        adapter.notifyDataSetChanged();



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




        //////////////




        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());


        ///////SHOWING current value


        JsonObjectRequest newjsonObjectRequest  = new JsonObjectRequest(Request.Method.POST, showCurrentValueUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {







                try {
                    JSONArray items = response.getJSONArray("items");

                    for (int i=0;i<items.length();i++){
                        JSONObject item = items.getJSONObject(i);

                        String itemValue= item.getString("item_value");

                        currentValueArray[i]=itemValue;


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

        requestQueue.add(newjsonObjectRequest);























        predictSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedString = parent.getItemAtPosition(position).toString();




                 if(selectedString.equals("dollar")){

                    insturmentSign.setImageResource(R.drawable.dollarsign);
                    predictLayout.setVisibility(View.VISIBLE);
                    buttonLayouts.setVisibility(View.VISIBLE);
                    insturmentSign.setVisibility(View.VISIBLE);
                    currentValue.setVisibility(View.VISIBLE);
                     commentText.setVisibility(View.VISIBLE);

                    INSTURMENT="dollar";
                     itemId=1;
                     currentValue.setText("Current Value = "+currentValueArray[itemId-1]);
                     checkPredicts();


                }







                else if(selectedString.equals("euro")){

                   insturmentSign.setImageResource(R.drawable.eurosign);
                    predictLayout.setVisibility(View.VISIBLE);
                     buttonLayouts.setVisibility(View.VISIBLE);
                    insturmentSign.setVisibility(View.VISIBLE);
                    currentValue.setVisibility(View.VISIBLE);
                     commentText.setVisibility(View.VISIBLE);
                     INSTURMENT="euro";
                     itemId=2;
                     currentValue.setText("Current Value = "+currentValueArray[itemId-1]);

                     checkPredicts();




                }

                else if(selectedString.equals("pound")){

                    insturmentSign.setImageResource(R.drawable.poundsign);
                    predictLayout.setVisibility(View.VISIBLE);
                     buttonLayouts.setVisibility(View.VISIBLE);
                    insturmentSign.setVisibility(View.VISIBLE);
                    currentValue.setVisibility(View.VISIBLE);
                     commentText.setVisibility(View.VISIBLE);
                     INSTURMENT="pound";
                     itemId=3;
                     currentValue.setText("Current Value = "+currentValueArray[itemId-1]);
                     checkPredicts();



                }

                else if(selectedString.equals("imkb")){

                    insturmentSign.setImageResource(R.drawable.imkgbsign);
                    predictLayout.setVisibility(View.VISIBLE);
                     buttonLayouts.setVisibility(View.VISIBLE);
                    insturmentSign.setVisibility(View.VISIBLE);
                    currentValue.setVisibility(View.VISIBLE);
                     commentText.setVisibility(View.VISIBLE);
                     INSTURMENT="imkb";
                     itemId=4;
                     currentValue.setText("Current Value = "+currentValueArray[itemId-1]);
                     checkPredicts();



                }







                else{
                    predictLayout.setVisibility(View.GONE);
                     buttonLayouts.setVisibility(View.GONE);
                    insturmentSign.setVisibility(View.GONE);
                    currentValue.setVisibility(View.GONE);
                     commentText.setVisibility(View.GONE);
                     INSTURMENT="Nothing";
                     predictionText.setVisibility(View.GONE);

                }





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                insturmentSign.setVisibility(View.GONE);
                predictLayout.setVisibility(View.GONE);

            }
        });

        riseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*
                predictLayout.setVisibility(View.GONE);

                insturmentSign.setVisibility(View.GONE);
                currentValue.setVisibility(View.GONE);
                riseButton.startAnimation(buttonClick);

               */

                predictionText.setVisibility(View.VISIBLE);
                predictionText.setText("rise");
                predictionText.setTextColor(Color.GREEN);


                Toast toast = Toast.makeText(getContext(),"Your prediction: "+ INSTURMENT+" will RISE", Toast.LENGTH_LONG);
                toast.show();
            }
        }

         );


        stableButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              /*
                                              predictLayout.setVisibility(View.GONE);

                                              insturmentSign.setVisibility(View.GONE);
                                              currentValue.setVisibility(View.GONE);
                                                */

                                              predictionText.setVisibility(View.VISIBLE);
                                              predictionText.setText("stable");
                                              predictionText.setTextColor(Color.BLACK);






                                              Toast toast = Toast.makeText(getContext(),"Your prediction: "+ INSTURMENT+" will stay STABLE", Toast.LENGTH_LONG);
                                              toast.show();
                                          }
                                      }

        );


        fallButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {


                                                /*
                                                predictLayout.setVisibility(View.GONE);

                                                insturmentSign.setVisibility(View.GONE);
                                                currentValue.setVisibility(View.GONE);
                                                            */


                                                predictionText.setVisibility(View.VISIBLE);
                                                predictionText.setText("fall");
                                                predictionText.setTextColor(Color.RED);



                                                Toast toast = Toast.makeText(getContext(),"Your prediction: "+ INSTURMENT+" will FALL", Toast.LENGTH_LONG);
                                                toast.show();
                                            }
                                        }

        );





        submitPrediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest request = new StringRequest(Request.Method.POST, submitPredictionUrl, new Response.Listener<String>() {
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

                        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                        String myDate = format.format(new Date());

                        parameters.put("userId",userId);
                        parameters.put("itemId",String.valueOf(itemId));
                        parameters.put("comment",commentText.getText().toString());
                        parameters.put("point",predictionText.getText().toString());
                        parameters.put("dateTime",myDate);


                        return parameters;


                    }
                };


                requestQueue.add(request);
                predictSpinner.setSelection(0);








            }
        });




        updatePrediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest request = new StringRequest(Request.Method.POST, updatePredictionUrl, new Response.Listener<String>() {
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

                        parameters.put("predictionComment",commentText.getText().toString());
                        parameters.put("predictionPoint",predictionText.getText().toString());
                        parameters.put("predictionId",predictionId);

                        return parameters;


                    }
                };


                requestQueue.add(request);
                predictSpinner.setSelection(0);


            }
        });







        return rootView;

    }



    public void checkPredicts(){

        request= new StringRequest(Request.Method.POST, controllPredictionUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    ;
                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.names().get(0).equals("predictionId")){

                        testText.setText("Upade your prediction");
                        predictionId=jsonObject.getString("predictionId");

                        String predictionComment =jsonObject.getString("predictionComment");
                        String predictionPoint=jsonObject.getString("predictionPoint");

                        predictionText.setText(predictionPoint);
                        predictionText.setVisibility(View.VISIBLE);
                        commentText.setText(predictionComment);


                        submitPrediction.setEnabled(false);
                        updatePrediction.setEnabled(true);


                        /*
                        testText.setText(jsonObject.getString("predictionId"));
                        testText.append(jsonObject.getString("predictionComment"));
                        testText.append(jsonObject.getString("predictionPoint"));  */


                    }else{

                        testText.setText("Make a new prediction!");

                        submitPrediction.setEnabled(true);
                        updatePrediction.setEnabled(false);
                        commentText.setText("");
                        predictionText.setText("");


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
                hashMap.put("userId",userId);
                hashMap.put("itemId",String.valueOf(itemId));
                return hashMap;

            }
        };
        requestQueue.add(request);







    }








}
