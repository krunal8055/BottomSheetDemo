package com.example.bottomsheetdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class BottomsheetDialog extends BottomSheetDialogFragment {
    Button login;
    EditText UserName,Password;
    String USERNAME,PASSWORD;
    FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.bottomsheet_login, null, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        login = view.findViewById(R.id.signin_btn_bottom);
        UserName = view.findViewById(R.id.username_login);
        Password = view.findViewById(R.id.password_login);
        firebaseAuth = FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USERNAME = UserName.getText().toString();
                PASSWORD = Password.getText().toString();
                if(!USERNAME.isEmpty() && !PASSWORD.isEmpty())
                {
                    //Toast.makeText(getContext().getApplicationContext(),USERNAME + "is try to LoggedIN.",Toast.LENGTH_LONG).show();
                    firebaseAuth.signInWithEmailAndPassword(USERNAME,PASSWORD)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(getContext().getApplicationContext(),USERNAME + "Successfully Logged in.",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(getContext().getApplicationContext(),"Username or Password is missing!",Toast.LENGTH_LONG).show();
                }
            }
        });
        firebaseAuth.signOut();



    }

}
