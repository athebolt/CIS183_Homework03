package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ListView lv_j_employees;
    Button btn_j_add;
    Intent addEmployeeIntent;
    Intent updateEmployeeIntent;
    ArrayList<User> listOfUsers;
    UserListAdapter adapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GUI to code connections
        lv_j_employees = findViewById(R.id.lv_v_employees);
        btn_j_add = findViewById(R.id.btn_v_add);

        //create new instance of an array list
        listOfUsers = new ArrayList<User>();

        //read database and store info in the list of users
        initializeDatabase();
        
        Intent cameFrom = getIntent();

        Bundle infoPassedToMe = cameFrom.getExtras();

        if(infoPassedToMe != null)
        {
            User userPassed = (User) cameFrom.getSerializableExtra("User");

            listOfUsers.add(userPassed);
        }

        //new adapter, set adapter
        initializeListView();

        //event listeners
        addButtonEventListener();
        updateEmployeeInfoEvent();
        deleteEmployeeInfoEvent();
    }

    public void addButtonEventListener()
    {
        btn_j_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("Button pressed", "========");

                //create new instance of intent addEmployee
                addEmployeeIntent = new Intent(MainActivity.this, AddEmployee.class);

                //start add employee intent
                startActivity(addEmployeeIntent);
            }
        });
    }

    public void updateEmployeeInfoEvent()
    {
        lv_j_employees.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Log.d("Click Performed", "Viewing Employee Info");

                updateEmployeeIntent = new Intent(MainActivity.this, UpdateEmployee.class);

                updateEmployeeIntent.putExtra("Employee", listOfUsers.get(i));

                startActivity(updateEmployeeIntent);
            }
        });
    }

    public void deleteEmployeeInfoEvent()
    {
        lv_j_employees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                //Log.d("Long click performed", "Employee Info deleted");

                //call the delete function in our dbHelper and pass it username
                dbHelper.deleteUser(listOfUsers.get(i).getuName());

                //remove the user from our list of users
                listOfUsers.remove(i);

                //update the listview to see the changes
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }

    public void initializeDatabase()
    {
        //new instance of database helper called dbHelper
        dbHelper = new DatabaseHelper(this);

        //initialize the databaseHelper, filling the database with records
        dbHelper.initializeDB();

        //testing
        //Log.d("Num of records: ", dbHelper.numberOfRowsInTable() + "");

        //get rows of info from the database and store them in the array list
        //we only have to read the database once every time we start main activity
        listOfUsers = dbHelper.getAllRows();
    }

    public void initializeListView()
    {
        //create new instance of an Array List of Users stored in listOfUsers
        //listOfUsers = new ArrayList<User>();

        adapter = new UserListAdapter(this, listOfUsers);

        //set custom adapter called adapter to the list view
        lv_j_employees.setAdapter(adapter);
    }
}