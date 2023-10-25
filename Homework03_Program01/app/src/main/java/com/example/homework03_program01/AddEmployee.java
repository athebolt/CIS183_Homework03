package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEmployee extends AppCompatActivity
{
    EditText et_j_a_fName;
    EditText et_j_a_lName;
    EditText et_j_a_email;
    EditText et_j_a_uName;
    EditText et_j_a_pass;
    EditText et_j_a_age;
    Button btn_j_a_add;
    Button btn_j_a_back;
    Intent mainActivityIntent;
    User user;

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
        btn_j_a_add = findViewById(R.id.btn_v_a_add);
        btn_j_a_back = findViewById(R.id.btn_v_a_back);

        addButtonEventHandler();
        backButtonEventHandler();

        mainActivityIntent = new Intent(AddEmployee.this, MainActivity.class);
    }

    public void addButtonEventHandler()
    {
        btn_j_a_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("Add Employee", "====");

                user = new User(et_j_a_fName.getText().toString(),
                                et_j_a_lName.getText().toString(),
                                et_j_a_email.getText().toString(),
                                et_j_a_uName.getText().toString(),
                                et_j_a_pass.getText().toString(),
                                et_j_a_age.getText().toString());

                mainActivityIntent.putExtra("User", user);
                startActivity(mainActivityIntent);
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
}