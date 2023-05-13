package com.example.mp_teamg;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mIdEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private TextView mErrorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mIdEditText = findViewById(R.id.edit_text_id);
        mPasswordEditText = findViewById(R.id.edit_text_pw);
        mLoginButton = findViewById(R.id.button_login);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mIdEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
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


        // 패스워드 보이기/숨기기 버튼 등록
        ImageView passwordToggle = findViewById(R.id.ic_visibility);

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







    }

    //private boolean isValidCredentials(String username, String password) {
        // 유효한 로그인 정보인지 검사
        //return username.equals("username") && password.equals("password");
    //}
}
