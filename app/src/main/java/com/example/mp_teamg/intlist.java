import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    // 로그에 사용할 TAG 변수 선언
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText title_et, content_et;
    Button reg_button;

    // Firebase Realtime Database 및 Storage 참조
    private DatabaseReference databaseRef;
    private StorageReference storageRef;

    // 유저아이디 변수
    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Firebase Realtime Database 및 Storage 참조 초기화
        databaseRef = FirebaseDatabase.getInstance().getReference("posts");
        storageRef = FirebaseStorage.getInstance().getReference();

        // ListActivity 에서 넘긴 userid 를 변수로 받음
        userid = getIntent().getStringExtra("userid");

        // 컴포넌트 초기화
        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

        // 버튼 이벤트 추가
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 게시물 등록 함수 호출
                registerBoard(userid, title_et.getText().toString(), content_et.getText().toString());
            }
        });
    }

    private void registerBoard(String userid, String title, String content) {
        // Firebase Realtime Database에 게시물 데이터 등록
        DatabaseReference newPostRef = databaseRef.push();
        String postId = newPostRef.getKey();

        Map<String, Object> postValues = new HashMap<>();
        postValues.put("userid", userid);
        postValues.put("title", title);
        postValues.put("content", content);

        newPostRef.setValue(postValues)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 게시물 등록 성공
                        Toast.makeText(RegisterActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 게시물 등록 실패
                        Toast.makeText(RegisterActivity.this, "등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}