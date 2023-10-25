package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ListView lv_j_employees;
    Button btn_j_add;
    Intent addEmployeeIntent;
    ArrayList<User> listOfUsers;
    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_j_employees = findViewById(R.id.lv_v_employees);
        btn_j_add = findViewById(R.id.btn_v_add);

        addEmployeeIntent = new Intent(MainActivity.this, AddEmployee.class);

        addButtonEventListener();

        Intent cameFrom = getIntent();

        User userPassed = (User) cameFrom.getSerializableExtra("User");

        addUser(userPassed);
    }

    public void addButtonEventListener()
    {
        btn_j_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Button pressed", "========");
                startActivity(addEmployeeIntent);

            }
        });
    }

    public void addUser(User user)
    {
        
    }


}