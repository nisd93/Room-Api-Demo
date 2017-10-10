package com.nisd93.roomapiexample.ui;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.Toast;

import com.nisd93.roomapiexample.R;
import com.nisd93.roomapiexample.database.AppDatabase;
import com.nisd93.roomapiexample.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserActivity extends AppCompatActivity {

    private AppDatabase db;

    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtAddress)
    EditText edtAddress;
    @BindView(R.id.edtAge)
    EditText edtAge;
    @BindView(R.id.btn_add_user)
    AppCompatButton btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        ButterKnife.bind(this);

        db = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).build();

    }

    @OnClick(R.id.btn_add_user)
    public void onViewClicked() {
        addUser();
    }

    private void addUser() {
        User user = new User();
        user.setAge(Integer.parseInt(edtAge.getText().toString()));
        user.setName(edtAge.getText().toString());
        user.setAddress(edtAddress.getText().toString());

        new AsyncTask<User, Void, Void>() {
            @Override
            protected Void doInBackground(User... params) {
                User save_user = params[0];
                db.userDao().insertAll(save_user);
                return null;
        }

        @Override
        protected void onPostExecute (Void aVoid){
            Toast.makeText(getApplicationContext(),"Data Added.",Toast.LENGTH_SHORT).show();
            finish();
        }
    }.execute(user);
}
}
