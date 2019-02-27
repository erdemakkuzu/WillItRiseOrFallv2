package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.erdem.willitriseorfallv2.MainActivity;
import com.example.erdem.willitriseorfallv2.R;
import com.example.erdem.willitriseorfallv2.StartActivity;

/**
 * Created by Erdem on 13.05.2017.
 */
public class Logout extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_homepage,container,false);


        Intent i = new Intent(getActivity().getApplicationContext(), StartActivity.class);
        startActivity(i);





        return rootView;
    }
}
