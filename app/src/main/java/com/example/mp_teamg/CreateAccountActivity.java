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
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAccountActivity  extends AppCompatActivity {

    private EditText mIdEditText;
    private EditText mPasswordEditText;
    private EditText mPasswordCheckEditText;
    private EditText mNameEditText;
    private EditText mPhoneEditText;

    private Button mCreateAccountButton;

    private FirebaseAuth mAuth;

    FirebaseFirestore db;

    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        tag = "계정생성";

        mIdEditText = (EditText)findViewById(R.id.edit_text_id_input);
        mPasswordEditText = (EditText)findViewById(R.id.edit_text_pw);
        mPasswordCheckEditText = (EditText)findViewById(R.id.edit_text_pw_check);
        mNameEditText = (EditText)findViewById(R.id.edit_text_name);
        mPhoneEditText = (EditText)findViewById(R.id.edit_text_phone);

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
        String name = mNameEditText.getText().toString();
        String phone = mPhoneEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(tag, "createUserWithEmail:success");
                            store_user_info(email, password, name, phone);
                            gotoLogin();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(tag, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    //로그인화면으로 이동
    private void gotoLogin(){
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    //파이어스토어에 회원정보 저장
    public void store_user_info(String id, String password, String name, String phone){
        User userInfo = new User(name, id, password, phone);
        db.collection("User").document(mAuth.getCurrentUser().getUid()).set(userInfo);
    }
}
