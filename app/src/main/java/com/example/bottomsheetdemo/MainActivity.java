package com.example.bottomsheetdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button Login,Signup;
    BottomsheetDialog bottomsheetDialog;
    BottomSheetDialogSignup bottomSheetDialogSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById(R.id.login_btn);
        Signup = findViewById(R.id.signup_btn);
        bottomsheetDialog = new BottomsheetDialog();
        bottomSheetDialogSignup = new BottomSheetDialogSignup();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomsheetDialog.show(getSupportFragmentManager(),"Login");
                bottomsheetDialog.getEnterTransition();
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogSignup.show(getSupportFragmentManager(),"Signup");
                bottomSheetDialogSignup.getEnterTransition();
            }
        });

    }
}
