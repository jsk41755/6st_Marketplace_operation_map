package com.example.testtest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import android.view.Menu;
import android.widget.TextView;
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

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class MainActivity extends AppCompatActivity {
    public Button btn_Zone1;
    public Button btn_Zone2;
    ImageView imageview2;           //지도 아이디 값 저장
    ActionBar actionBar;

    private RadioGroup rg;
    private RadioButton rb_stname, rb_address;
    private String str_result;

    RecyclerView recview;
    CustomAdapter adapter;

    private EditText mSearchField; //검색창
    private ImageButton mSearchBtn; //검색버튼

    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    //ItemTouchHelper helper;

    //private List<ExampleItem> exampleList;
    //private List<ExampleItem> exampleListFull;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //화면 회전 이동 세로 고정

        //setTitle("건물명을 입력하시오.");

        mSearchField = (EditText) findViewById(R.id.mSearchField); // 검색창
        mSearchBtn = (ImageButton) findViewById(R.id.mSearchBtn); //검색버튼

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFA4614")));      //액션바 색상

        ActionBar actionBar = getSupportActionBar();
         actionBar.hide();                              //액션바 숨김

        str_result = getString(R.string.st_name); //라디오 버튼 값

        rg = findViewById(R.id.rg);
        rb_stname = findViewById(R.id.rb_stname);
        rb_address = findViewById(R.id.rb_address);

        rb_stname.setChecked(true);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { //라디오의 상태 값의 변경됨을 감지.
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.rb_stname){
                    Toast.makeText(MainActivity.this,"상호명",Toast.LENGTH_SHORT).show();
                    str_result = getString(R.string.st_name);
                } else if(checkedId == R.id.rb_address){
                    Toast.makeText(MainActivity.this,"주소값",Toast.LENGTH_SHORT).show();
                    str_result = getString(R.string.address);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {                                                                     //상태바 색상
            window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.firefighter_color));
        }

        recview = (RecyclerView) findViewById(R.id.recyclerView);                                            //DB 리싸이클러뷰
        recview.setLayoutManager(new LinearLayoutManager(this));
        imageview2 = (ImageView) findViewById(R.id.imageView2);

        FirebaseRecyclerOptions<User> options =                                                     //검색 알고리즘_1
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"), User.class)
                        .build();

        adapter = new CustomAdapter(options);
        recview.setAdapter(adapter);

        mSearchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {      //검색창에서 키보드에서 검색을 눌렀을 때도 검색이 되게 만들음.
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                switch (actionId)
                {
                    case IME_ACTION_SEARCH :
                        String searchText = mSearchField.getText().toString();

                        ProcessSearch(searchText);
                        break;
                }
                return true;
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {                          //검색창 옆에있는 버튼을 눌렀을 때 검색이 되게 만들음.
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                ProcessSearch(searchText);

            }
        });

        //    helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        //    helper.attachToRecyclerView(recview);                                                    //recyclerview에  itemtouchhelper 붙임 스와이프

        imageview2.setOnTouchListener(new View.OnTouchListener() {                  //지도 터치로 이동시키기. 아직 완성 x
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int parentWidth = ((ViewGroup) view.getParent()).getWidth();
                int parentHeight = ((ViewGroup) view.getParent()).getHeight();    //각각 부모view의 너비와 높이

                int action = motionEvent.getAction();


                if (action == MotionEvent.ACTION_DOWN) {  //뷰를 눌렀을 때
                    float curX = motionEvent.getX();
                    float curY = motionEvent.getY();

                    Log.d("viewTest", "oldXvalue : " + curX + " oldYvalue : " + curY);    // View 내부에서 터치한 지점의 상대 좌표값.
                    Log.d("viewTest", "v.getX() : " + view.getX());    // View 의 좌측 상단이 되는 지점의 절대 좌표값.
                    Log.d("viewTest", "RawX : " + motionEvent.getRawX() + " RawY : " + motionEvent.getRawY());    // View 를 터치한 지점의 절대 좌표값.
                    Log.d("viewTest", "v.getHeight : " + view.getHeight() + " v.getWidth : " + view.getWidth());    // View 의 Width, Height

                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    // 뷰 이동 중
                    view.setX(view.getX() + (motionEvent.getX()) - (view.getWidth() / 2));
                    view.setY(view.getY() + (motionEvent.getY()) - (view.getHeight() / 2));

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    // 뷰에서 손을 뗌

                    if (view.getX() < 0) {
                        view.setX(0);
                    } else if ((view.getX() + view.getWidth()) > parentWidth) {
                        view.setX(parentWidth - view.getWidth());
                    }

                    if (view.getY() < 0) {
                        view.setY(0);
                    } else if ((view.getY() + view.getHeight()) > parentHeight) {
                        view.setY(parentHeight - view.getHeight());
                    }
                }
                return true;
            }
        });

        adapter.setOnItemclicklistener(new CustomAdapter.OnPersonItemClickListener() {    //카드뷰 클릭 시 작동.
            @Override
            public void onItemClick(View view, int position) {
                User user = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), user.getId()+"가 선택됨", Toast.LENGTH_SHORT).show();
            }
        });


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
                ProcessSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ProcessSearch(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void ProcessSearch(String s)                                                        //검색 알고리즘_3
    {
        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").orderByChild(str_result).startAt(s).endAt(s + "\uf8ff"), User.class)
                        .build();

        adapter = new CustomAdapter(options);
        adapter.startListening();

        recview.setAdapter(adapter);
        adapter.setOnItemclicklistener(new CustomAdapter.OnPersonItemClickListener() {    //카드뷰 클릭 시 작동.
            @Override
            public void onItemClick(View view, int position) {
                User user = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), user.getId()+"가 선택됨", Toast.LENGTH_SHORT).show();
            }
        });

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