import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class DetailActivity extends AppCompatActivity {

    private TextView title_tv, content_tv, date_tv;
    private String board_seq = "";
    private FirebaseFirestore db;
    private ListenerRegistration docListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        board_seq = getIntent().getStringExtra("board_seq");

        title_tv = findViewById(R.id.title_tv);
        content_tv = findViewById(R.id.content_tv);
        date_tv = findViewById(R.id.date_tv);

        db = FirebaseFirestore.getInstance();
        docListener = db.collection("boards").document(board_seq)
                .addSnapshotListener((documentSnapshot, e) -> {
                    if (e != null) {
                        // Firestore에서 문서 읽기 실패
                        return;
                    }

                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        String title = documentSnapshot.getString("title");
                        String content = documentSnapshot.getString("content");
                        String crt_dt = documentSnapshot.getString("crt_dt");

                        title_tv.setText(title);
                        content_tv.setText(content);
                        date_tv.setText(crt_dt);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Listener 등록 해제
        if (docListener != null) {
            docListener.remove();
        }
    }
}