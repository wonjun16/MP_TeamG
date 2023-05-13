package com.example.mp_teamg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mIdEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    private Button mRegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIdEditText = findViewById(R.id.edit_text_id);
        mPasswordEditText = findViewById(R.id.edit_text_pw);
        mLoginButton = findViewById(R.id.button_login);
        mRegisterButton = findViewById(R.id.button_register);

        //로그인 버튼 관련 기능
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력한 값이 맞는지 확인하는 코드 부분
                String username = mIdEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                // 메인 액티비티로 넘어가는 코드
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

/*
                if (username.isEmpty() || password.isEmpty()) {
                } else if (!isValidCredentials(username, password)) {
                    // mErrorTextView.setVisibility(View.VISIBLE);
                    // mErrorTextView.setText(R.string.error_invalid_credentials);
                } else {
                    // 로그인 처리
                }
 */
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
}
