package iss.workshop.adproject_team5_movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import iss.workshop.adproject_team5_movieapp.Model.User;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");



        BottomNavigationView btmNavBar = findViewById(R.id.nav_bar);
        btmNavBar.setOnItemSelectedListener(this);
        btmNavBar.setSelectedItemId(R.id.nav_bar);
        if(savedInstanceState==null){
            btmNavBar.setSelectedItemId(R.id.item_films);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        final SharedPreferences pref = getSharedPreferences("user_credentials",MODE_PRIVATE);
        switch (item.getItemId()){


            case R.id.logout:
                //Do Logout
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                finish();

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item != null) {
            return switchFragment(item.getItemId());
        }

        return false;
    }

    private boolean switchFragment(int itemId) {


        switch (itemId) {
            case R.id.item_films:

                //commitTransaction(filmFragment);

                FilmsFragment filmFragment = new FilmsFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                Bundle b = new Bundle();
                b.putSerializable("user",user);
                filmFragment.setArguments(b);
                trans.add(R.id.fragment_frame,filmFragment);
                trans.commit();
                commitTransaction(filmFragment);
                return true;
//            case R.id.item_reviews:
//                ReviewsFragment reviewsFragment = new ReviewsFragment();
//                commitTransaction(reviewsFragment);
//                return true;
            case R.id.item_lists:
                ListsFragment listsFragment= new ListsFragment();
                FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction trans1 = fm1.beginTransaction();
                Bundle b1 = new Bundle();

                b1.putSerializable("user1",user);
                listsFragment.setArguments(b1);
                trans1.add(R.id.fragment_frame,listsFragment);
                trans1.commit();
                commitTransaction(listsFragment);
                return true;
            case R.id.item_users:
                UsersFragment usersFragment = new UsersFragment();

                FragmentManager fm3 = getSupportFragmentManager();
                FragmentTransaction trans3 = fm3.beginTransaction();
                Bundle b3 = new Bundle();
                b3.putSerializable("user",user);
                usersFragment.setArguments(b3);
                trans3.add(R.id.fragment_frame,usersFragment);
                trans3.commit();
                commitTransaction(usersFragment);

                return true;
        }
        return false;
    }
    private void commitTransaction(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.fragment_frame, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }

}