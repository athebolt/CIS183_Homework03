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
    static ArrayList<User> listOfUsers = new ArrayList<User>();
    UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GUI to code connections
        lv_j_employees = findViewById(R.id.lv_v_employees);
        btn_j_add = findViewById(R.id.btn_v_add);

        //create new instance of intent addEmployee
        addEmployeeIntent = new Intent(MainActivity.this, AddEmployee.class);

        initializeListView();

        Intent cameFrom = getIntent();

        //get info from intent
        Bundle infoPassedToMe = cameFrom.getExtras();

        //if there is info
        if(infoPassedToMe != null)
        {
            //get info called User
            User userPassed = (User) cameFrom.getSerializableExtra("User");

            //add user to the list of users
            listOfUsers.add(userPassed);

            adapter.notifyDataSetChanged();
        }

        //listen for a press of add employee button
        addButtonEventListener();
    }

    public void addButtonEventListener()
    {
        btn_j_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Button pressed", "========");

                //start add employee intent
                startActivity(addEmployeeIntent);

            }
        });
    }

    public void initializeListView()
    {
        //create new instance of an Array List of Users stored in listOfUsers
        listOfUsers = new ArrayList<User>();

        adapter = new UserListAdapter(this, listOfUsers);

        //set custom adapter called adapter to the list view
        lv_j_employees.setAdapter(adapter);
    }

}