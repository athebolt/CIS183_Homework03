package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AddEmployee extends AppCompatActivity
{
    EditText et_j_a_fName;
    EditText et_j_a_lName;
    EditText et_j_a_email;
    EditText et_j_a_uName;
    EditText et_j_a_pass;
    EditText et_j_a_age;
    TextView tv_j_a_error;
    Button btn_j_a_add;
    Button btn_j_a_back;
    Intent mainActivityIntent;
    User user;
    DatabaseHelper dbHelper;
    ArrayList<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        et_j_a_fName = findViewById(R.id.et_v_a_fName);
        et_j_a_lName = findViewById(R.id.et_v_a_lName);
        et_j_a_email = findViewById(R.id.et_v_a_email);
        et_j_a_uName = findViewById(R.id.et_v_a_uName);
        et_j_a_pass = findViewById(R.id.et_v_a_pass);
        et_j_a_age = findViewById(R.id.et_v_a_age);
        tv_j_a_error = findViewById(R.id.tv_v_a_error);
        btn_j_a_add = findViewById(R.id.btn_v_a_add);
        btn_j_a_back = findViewById(R.id.btn_v_a_back);

        dbHelper = new DatabaseHelper(this);

        usernames = dbHelper.getAllUsernames();

        mainActivityIntent = new Intent(AddEmployee.this, MainActivity.class);

        addButtonEventHandler();
        backButtonEventHandler();
    }

    public void addButtonEventHandler()
    {
        btn_j_a_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Log.d("Add Employee", "====");

                if(!et_j_a_fName.getText().toString().equals("") &&
                        !et_j_a_lName.getText().toString().equals("") &&
                        !et_j_a_email.getText().toString().equals("") &&
                        !et_j_a_uName.getText().toString().equals("") &&
                        !et_j_a_pass.getText().toString().equals("") &&
                        !et_j_a_age.getText().toString().equals(""))
                {

                    if(isUsernameUnique())
                    {
                        //getting text from gui and storing it into a new user
                        user = new User(et_j_a_fName.getText().toString(),
                                et_j_a_lName.getText().toString(),
                                et_j_a_email.getText().toString(),
                                et_j_a_uName.getText().toString(),
                                et_j_a_pass.getText().toString(),
                                et_j_a_age.getText().toString());

                        Log.d("Adding user", "=-==");
                        dbHelper.addNewEmployee(user);

                        startActivity(mainActivityIntent);
                    }
                }
                else
                {
                    tv_j_a_error.setText("ERROR: Please fill all fields!");
                    tv_j_a_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void backButtonEventHandler()
    {
        btn_j_a_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(mainActivityIntent);
            }
        });
    }

    public boolean isUsernameUnique()
    {
        for(int i = 0; i < usernames.size(); i++)
        {
            Log.d("Username: ", usernames.get(i) + "");

            if(usernames.get(i).equals(et_j_a_uName.getText().toString()))
            {
                tv_j_a_error.setText("ERROR: Username already taken!");
                tv_j_a_error.setVisibility(View.VISIBLE);

                return false;
            }
        }

        return true;
    }
}