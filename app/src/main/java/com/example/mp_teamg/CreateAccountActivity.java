package com.example.mp_teamg;

import android.os.Bundle;
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

        Button mCreateAccountButton = findViewById(R.id.button_create_account);


    }

}
