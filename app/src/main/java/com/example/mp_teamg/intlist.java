import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText title_et, content_et;
    private Button reg_button;
    private String userid = "";

    private FirebaseFirestore db;
    private CollectionReference boardsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = FirebaseFirestore.getInstance();
        boardsRef = db.collection("boards");

        userid = getIntent().getStringExtra("userid");

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerBoard(userid, title_et.getText().toString(), content_et.getText().toString());
            }
        });
    }

    private void registerBoard(String userid, String title, String content) {
        Map<String, Object> boardData = new HashMap<>();
        boardData.put("userid", userid);
        boardData.put("title", title);
        boardData.put("content", content);

        boardsRef.document()
                .set(boardData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegisterActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
