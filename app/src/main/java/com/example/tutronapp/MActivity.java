package com.example.tutronapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        Button loginbtn = (Button) findViewById(R.id.Login_button);
        Button tutor = (Button) findViewById(R.id.tutor_button);
        Button student = (Button) findViewById(R.id.std_button);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("username")&& password.getText().toString().equals("password")){
                    //we have use the user name to stop through errors we will figure out how to store user data later
                    //we should open the next login page.....and determine if a student or tutor
                    Toast.makeText(MainActivity.this,"successful login",Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(MainActivity.this,"unsuccessful login",Toast.LENGTH_SHORT);

                }
            }
        });

    }
}