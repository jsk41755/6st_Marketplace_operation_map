package com.example.testtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.testtest.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Button btn_Zone1;
    public Button btn_Zone2;

    public EditText mSearchFild; //검색창
    public ImageButton mSearchBtn; //검색버튼

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    //private List<ExampleItem> exampleList;
    //private List<ExampleItem> exampleListFull;

    //private mBinding : ActivityMainBinding? = null; 잘 못 적음

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);// id 연결
        recyclerView.setHasFixedSize(true);// 리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //List<User> arrayList = new ArrayList<User>();
        arrayList = new ArrayList<>();// User 객체를 담을 어레이 리스트(어댑터쪽으로)


        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("User"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear();// 기존 배열리스트가 존재하지않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //반복문으로 데이터 List를 추출해냄
                    User user = snapshot.getValue(User.class); // 만들어왔던 User 객체에 데이터를 담는다.
                    arrayList.add(user); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰를 보낼 준비
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비를 가져오던중 에러 발생 시
                Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);

        //필터
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.example_menu, menu);

            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return false;
                }
            });
            return true;
        }
        //필터 끝

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_Zone1 = (Button) findViewById(R.id.btn_Zone1);
        btn_Zone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zone1();
            }
        });


        btn_Zone2 = (Button) findViewById(R.id.btn_Zone2);
        btn_Zone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zone2();
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