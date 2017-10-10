package com.nisd93.roomapiexample.ui;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nisd93.roomapiexample.R;
import com.nisd93.roomapiexample.adapter.UserAdapter;
import com.nisd93.roomapiexample.database.AppDatabase;
import com.nisd93.roomapiexample.entity.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity  implements UserAdapter.ActionCallback {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.add_user)
    FloatingActionButton addUser;

    private UserAdapter userAdapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).build();

        init();
    }

    private void init()
    {
        userAdapter = new UserAdapter(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);
    }

    @OnClick(R.id.add_user)
    public void onViewClicked() {
        Intent i=new Intent(MainActivity.this,AddUserActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();

    }

    private void loadData()
    {
        new AsyncTask<Void, Void, List<User>>() {
            @Override
            protected List<User> doInBackground(Void... params) {
                return db.userDao().getAll();
            }

            @Override
            protected void onPostExecute(List<User> notes) {
                userAdapter.setNotes(notes);
            }
        }.execute();
    }
}
