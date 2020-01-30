package com.example.bottomsheetdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class
BottomSheetDialogSignup extends BottomSheetDialogFragment {
    Button signup;
    Context context;
    EditText FirstName,LastName,EmailID,Password;
    String FIRSTNAME,LASTNAME,EMAILID,PASSWORD;
    FirebaseDatabase firebasedb;
    DatabaseReference dbref;
    FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.bottomsheet_signup, null, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity().getApplicationContext();
        signup = view.findViewById(R.id.signup_btn_bottom);
        firebaseAuth = FirebaseAuth.getInstance();
        firebasedb = FirebaseDatabase.getInstance();
        dbref = firebasedb.getReference("Users");

        FirstName = view.findViewById(R.id.fn_reg);
        LastName = view.findViewById(R.id.ln_reg);
        EmailID = view.findViewById(R.id.email_reg);
        Password = view.findViewById(R.id.password_reg);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FIRSTNAME = FirstName.getText().toString();
                LASTNAME = LastName.getText().toString();
                EMAILID = EmailID.getText().toString();
                PASSWORD = Password.getText().toString();
                if(!FIRSTNAME.isEmpty() && !LASTNAME.isEmpty() && !EMAILID.isEmpty() && !PASSWORD.isEmpty()) {
                    //Toast.makeText(getContext().getApplicationContext(), "Successful Registered!", Toast.LENGTH_LONG).show();
                    firebaseAuth.createUserWithEmailAndPassword(EMAILID,PASSWORD)
                            .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        User user = new User(FIRSTNAME,LASTNAME,EMAILID,PASSWORD);
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                   Toast.makeText(getContext().getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                                   startActivity(new Intent(context,MainActivity.class));
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(getContext().getApplicationContext(),"Something is Wrong!",Toast.LENGTH_SHORT).show();
                                    }
                                    if (!task.isSuccessful()) {
                                        Log.e("ERROR NOT Successful", "onComplete: Failed=" + task.getException().getMessage());
                                    }
                                }
                            });




                }
                else
                {
                    Toast.makeText(getContext().getApplicationContext(), "Something is missing!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
