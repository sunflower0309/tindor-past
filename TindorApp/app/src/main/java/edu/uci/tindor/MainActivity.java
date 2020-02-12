package edu.uci.tindor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import edu.uci.tindor.Fragments.MatchesFragment;
import edu.uci.tindor.Fragments.ProfileFragment;
import edu.uci.tindor.Fragments.SwipeFragment;

public class MainActivity extends AppCompatActivity {

//    protected ArrayList<String> al = new ArrayList<String>();
//    protected ArrayAdapter<String> arrayAdapter;
//    protected int i = 0;

    private ProfileFragment profileFragment = null;
    private SwipeFragment swipeFragment = null;
    private MatchesFragment matchesFragment = null;

    private TabLayout.Tab profileTab = null;
    private TabLayout.Tab swipeTab = null;
    private TabLayout.Tab matchesTab = null;

    void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout navigationTabs = findViewById(R.id.navigationTabs);
        profileTab = navigationTabs.newTab();
        swipeTab = navigationTabs.newTab();
        matchesTab = navigationTabs.newTab();

        profileTab.setIcon(ContextCompat.getDrawable(this, R.drawable.tab_profile));
        swipeTab.setIcon(ContextCompat.getDrawable(this, R.drawable.tab_swipe));
        matchesTab.setIcon(ContextCompat.getDrawable(this, R.drawable.tab_matches));

        navigationTabs.addTab(profileTab);
        navigationTabs.addTab(swipeTab);
        navigationTabs.addTab(matchesTab);

        navigationTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == profileTab) {
                    if (profileFragment == null) {
                        profileFragment = new ProfileFragment();
                    }
                    replaceFragment(profileFragment);
                } else if (tab == swipeTab) {
                    if (swipeFragment == null) {
                        swipeFragment = new SwipeFragment();
                    }
                    replaceFragment(swipeFragment);
                } else if (tab == matchesTab) {
                    if (matchesFragment == null) {
                        matchesFragment = new MatchesFragment();
                    }
                    replaceFragment(matchesFragment);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
            }
        });


//        //add the view via xml or programmatically
//        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
//
//        al.add("php");
//        al.add("c");
//        al.add("python");
//        al.add("java");
//
//        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, al );
//
//        //set the listener and the adapter
//        flingContainer.setAdapter(arrayAdapter);
//        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
//            @Override
//            public void removeFirstObjectInAdapter() {
//                // this is the simplest way to delete an object from the Adapter (/AdapterView)
//                Log.d("LIST", "removed object!");
//                al.remove(0);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onLeftCardExit(Object dataObject) {
//                //Do something on the left!
//                //You also have access to the original object.
//                //If you want to use it just cast it (String) dataObject
//                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onRightCardExit(Object dataObject) {
//                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdapterAboutToEmpty(int itemsInAdapter) {
//                // Ask for more data here
//                al.add("XML ".concat(String.valueOf(i)));
//                arrayAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;
//            }
//
//            @Override
//            public void onScroll(float v) {
//
//            }
//        });
//
//        // Optionally add an OnItemClickListener
//        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClicked(int itemPosition, Object dataObject) {
//                //Toast.makeToast(MainActivity.this, "Clicked!");
//            }
//        });
    }
}
