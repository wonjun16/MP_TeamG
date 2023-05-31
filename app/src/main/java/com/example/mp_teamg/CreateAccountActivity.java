package com.example.mp_teamg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity  extends AppCompatActivity {

    private EditText mIdEditText;
    private EditText mPasswordEditText;
    private EditText mPasswordCheckEditText;
    private EditText mEmailEditText;
    private EditText mContactEditText;

    private Button mCreateAccountButton;

    private FirebaseAuth mAuth;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        tag = "계정생성";

        mIdEditText = (EditText)findViewById(R.id.edit_text_id_input);
        mPasswordEditText = (EditText)findViewById(R.id.edit_text_pw);
        mPasswordCheckEditText = (EditText)findViewById(R.id.edit_text_pw_check);
        mEmailEditText = (EditText)findViewById(R.id.edit_text_email);
        mContactEditText = (EditText)findViewById(R.id.edit_text_contact);

        mCreateAccountButton = findViewById(R.id.button_create_account);

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 계정 생성
                createAccount();

            }
        });

    }

    private void createAccount(){
        String email = mIdEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(tag, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            gotoLogin();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(tag, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    private void gotoLogin(){
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
