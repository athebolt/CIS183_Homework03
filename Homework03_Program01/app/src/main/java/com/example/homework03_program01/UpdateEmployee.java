package com.example.homework03_program01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateEmployee extends AppCompatActivity
{
    EditText et_j_u_fName;
    EditText et_j_u_lName;
    TextView tv_j_u_uName;
    EditText et_j_u_pass;
    EditText et_j_u_email;
    EditText et_j_u_age;
    TextView tv_j_u_error;
    Button btn_j_u_update;
    Button btn_j_u_back;
    Intent mainActivity;
    User userPassed;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        et_j_u_fName = findViewById(R.id.et_v_u_fName);
        et_j_u_lName = findViewById(R.id.et_v_u_lName);
        tv_j_u_uName = findViewById(R.id.tv_v_u_uName);
        et_j_u_pass = findViewById(R.id.et_v_u_pass);
        et_j_u_email = findViewById(R.id.et_v_u_email);
        et_j_u_age = findViewById(R.id.et_v_u_age);
        tv_j_u_error = findViewById(R.id.tv_v_u_error);
        btn_j_u_update = findViewById(R.id.btn_v_u_update);
        btn_j_u_back = findViewById(R.id.btn_v_u_back);

        dbHelper = new DatabaseHelper(this);

        mainActivity = new Intent(UpdateEmployee.this, MainActivity.class);

        Intent cameFrom = getIntent();

        userPassed = (User) cameFrom.getSerializableExtra("Employee");

        //fill in info
        et_j_u_fName.setText(userPassed.getfName());
        et_j_u_lName.setText(userPassed.getlName());
        tv_j_u_uName.setText(userPassed.getuName());
        et_j_u_pass.setText(userPassed.getPass());
        et_j_u_email.setText(userPassed.getEmail());
        et_j_u_age.setText(userPassed.getAge());

        updateButtonEvent();
        backButtonEvent();
    }

    public void updateButtonEvent()
    {
        btn_j_u_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(!et_j_u_fName.getText().toString().equals("") &&
                        !et_j_u_lName.getText().toString().equals("") &&
                        !et_j_u_email.getText().toString().equals("") &&
                        !et_j_u_pass.getText().toString().equals("") &&
                        !et_j_u_age.getText().toString().equals(""))
                {
                    userPassed.setfName(et_j_u_fName.getText().toString());
                    userPassed.setlName(et_j_u_lName.getText().toString());
                    userPassed.setPass(et_j_u_pass.getText().toString());
                    userPassed.setEmail(et_j_u_email.getText().toString());
                    userPassed.setAge(et_j_u_age.getText().toString());

                    dbHelper.updateUser(userPassed);

                    startActivity(mainActivity);
                }
                else
                {
                    tv_j_u_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void backButtonEvent()
    {
        btn_j_u_back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(mainActivity);
            }
        });
    }
}