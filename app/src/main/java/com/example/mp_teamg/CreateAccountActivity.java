package com.example.mp_teamg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity  extends AppCompatActivity {


    private EditText mIdEditText;
    private EditText mPasswordEditText;
    private EditText mPasswordCheckEditText;
    private EditText mEmailEditText;
    private EditText mContactEditText;

    private Button mCreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mIdEditText = findViewById(R.id.edit_text_id_input);
        mPasswordEditText = findViewById(R.id.edit_text_pw);
        mPasswordCheckEditText = findViewById(R.id.edit_text_pw_check);
        mEmailEditText = findViewById(R.id.edit_text_email);
        mContactEditText = findViewById(R.id.edit_text_contact);

        mCreateAccountButton = findViewById(R.id.button_create_account);

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력한 값이 맞는지 확인하는 코드 부분
                String username = mIdEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String email = mEmailEditText.getText().toString();
                String contact = mContactEditText.getText().toString();


                // 로그인 액티비티로 넘어가는 코드
                Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
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

    }

}
