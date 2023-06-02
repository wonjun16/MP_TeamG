package com.example.mp_teamg;

import static java.lang.Boolean.FALSE;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.security.auth.login.LoginException;

public class LoginActivity extends AppCompatActivity {

    private EditText mIdEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    private Button mRegisterButton;

    private FirebaseAuth mAuth;
    private String tag;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        tag = "로그인";

        mIdEditText = (EditText) findViewById(R.id.edit_text_id);
        mPasswordEditText = (EditText) findViewById(R.id.edit_text_pw);
        mLoginButton = (Button)findViewById(R.id.button_login);
        mRegisterButton = (Button)findViewById(R.id.button_register);

        //로그인 버튼 관련 기능
        mLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //로그인 시도
                login();
            }
        });



        // 회원가입 버튼 관련 기능
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
               startActivity(intent);
            }
        });

        /* 관리자로 로그인
        if ((username.equals(getAdmin_id()) && password.equals(getAdmin_pw())) || (username.equals(getClient_id()) && password.equals(getAdmin_pw())) ) {
            // 메인 액티비티로 넘어가는 코드
            Toast.makeText(LoginActivity.this, "Admin : Logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            // 로그인 액티비티에서 메인 액티비티로 인텐트 전달

            intent.putExtra("username", username); // 아이디 정보 전달
            intent.putExtra("password", password); // 패스워드 정보 전달
            startActivity(intent);

            startActivity(intent);
        } else if (username.equals(getAdmin_id()) && password.equals(getAdmin_pw())==FALSE) {
            Toast.makeText(LoginActivity.this, "Log in failed : Wrong Password", Toast.LENGTH_SHORT).show();
        } else if (username.equals(getAdmin_id()) == FALSE && password.equals(getAdmin_pw())) {
            Toast.makeText(LoginActivity.this, "Log in failed : Wrong Username", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "Log in failed : Empty Values", Toast.LENGTH_SHORT).show();
        }
         */

        // 패스워드 보이기/숨기기 버튼 등록
        //ImageView passwordToggle = findViewById(R.id.ic_visibility);

        /*
        // 패스워드 토글 아이콘 클릭 시 패스워드 보이기/숨기기
        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPasswordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // 패스워드가 보이는 상태일 때
                    mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.ic_visibility);
                } else {
                    // 패스워드가 숨겨진 상태일 때
                    mPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.ic_visibility);
                }
            }
        });

         */



        /*


        passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPasswordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    // passwordVisibilityToggle.setImageResource(R.drawable.ic_password_visibility_off);
                } else {
                    mPasswordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    // passwordVisibilityToggle.setImageResource(R.drawable.ic_password_visibility);
                }
            }
        });

         */

        //private boolean isValidCredentials(String username, String password) {
        // 유효한 로그인 정보인지 검사
        //return username.equals("username") && password.equals("password");
        //}
    }

    private void login(){
        String email = mIdEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if(email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(tag, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //메인 액티비티 이동
                                startMain();
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(tag, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 틀렸습니다",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{
            Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
        }
    }

    private void startMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
