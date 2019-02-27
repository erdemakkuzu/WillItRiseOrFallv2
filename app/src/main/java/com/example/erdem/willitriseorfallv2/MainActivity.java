package com.example.erdem.willitriseorfallv2;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import fragments.ExchangeRates;
import fragments.HomePage;
import fragments.Logout;
import fragments.PredictionHistory;
import fragments.Predictions;
import fragments.SearchUser;
import fragments.TopAccuracyPredictors;
import fragments.TopPredictors;
import fragments.pieChartTest;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


        String username;
        String userId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username=getIntent().getStringExtra("username");
        userId=getIntent().getStringExtra("userId");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.cotentFrame,new HomePage()).commit();





    }


    public String getUserId(){
        return userId;
    }

    public String getUsername(){
        return username;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        FragmentManager fm = getSupportFragmentManager();






        int id = item.getItemId();

        if (id == R.id.homePage) {

            fm.beginTransaction().replace(R.id.cotentFrame,new HomePage()).commit();

            // Handle the camera action
        } else if (id == R.id.predicts) {

            fm.beginTransaction().replace(R.id.cotentFrame,new Predictions()).commit();

        } else if(id == R.id.predictionHistory){

            fm.beginTransaction().replace(R.id.cotentFrame,new PredictionHistory()).commit();


        }else if (id == R.id.Rankings) {
            fm.beginTransaction().replace(R.id.cotentFrame,new TopPredictors()).commit();
        }

        else if (id == R.id.rankingsAccuracy) {
            fm.beginTransaction().replace(R.id.cotentFrame,new TopAccuracyPredictors()).commit();
        }




        else if (id == R.id.exchangeRates) {
            fm.beginTransaction().replace(R.id.cotentFrame,new ExchangeRates()).commit();
        }

        else if (id == R.id.SearchUser) {
            fm.beginTransaction().replace(R.id.cotentFrame,new SearchUser()).commit();


        }  else if (id == R.id.logout) {
            fm.beginTransaction().replace(R.id.cotentFrame,new Logout()).commit();

        }
            else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
