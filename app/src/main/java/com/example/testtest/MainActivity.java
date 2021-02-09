package com.example.testtest;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.testtest.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Button btn_Zone1;
    public Button btn_Zone2;

    ActionBar actionBar;

    RecyclerView recview;
    CustomAdapter adapter;

    public EditText mSearchField; //검색창
    public ImageButton mSearchBtn; //검색버튼

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    //private List<ExampleItem> exampleList;
    //private List<ExampleItem> exampleListFull;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("건물명을 입력하시오.");

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFA4614")));      //액션바 색상

        if(Build.VERSION.SDK_INT>=21){                                                                     //상태바 색상
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.firefighter_color));
        }

        recview=(RecyclerView)findViewById(R.id.recyclerView);                                            //DB 리싸이클러뷰
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<User> options =                                                     //검색 알고리즘_1
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"), User.class)
                        .build();

        adapter=new CustomAdapter(options);
        recview.setAdapter(adapter);
            }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                                                 ////검색 알고리즘_2
        getMenuInflater().inflate(R.menu.example_menu, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
        private void processsearch(String s)                                                        //검색 알고리즘_3
        {
            FirebaseRecyclerOptions<User> options =
                    new FirebaseRecyclerOptions.Builder<User>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("User").orderByChild("st_name").startAt(s).endAt(s+"\uf8ff"), User.class)
                            .build();

            adapter=new CustomAdapter(options);
            adapter.startListening();
            recview.setAdapter(adapter);

        }

    public void Zone1() {
        Intent intent = new Intent(this, Zone1.class);
        startActivity(intent);
    }

    public void Zone2() {
        Intent intent = new Intent(this, Zone2.class);
        startActivity(intent);
    }

}