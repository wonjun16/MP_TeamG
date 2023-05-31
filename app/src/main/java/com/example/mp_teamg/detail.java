import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {

    private TextView title_tv, content_tv, date_tv;
    private String board_seq = "";
    private String userId = "";
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        board_seq = getIntent().getStringExtra("board_seq");
        userId = getIntent().getStringExtra("userid");

        title_tv = findViewById(R.id.title_tv);
        content_tv = findViewById(R.id.content_tv);
        date_tv = findViewById(R.id.date_tv);

        databaseRef = FirebaseDatabase.getInstance().getReference("boards").child(board_seq);

        // 선택한 게시물의 데이터를 읽어와서 화면에 표시
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title = dataSnapshot.child("title").getValue(String.class);
                String content = dataSnapshot.child("content").getValue(String.class);
                String crt_dt = dataSnapshot.child("crt_dt").getValue(String.class);

                title_tv.setText(title);
                content_tv.setText(content);
                date_tv.setText(crt_dt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 데이터베이스 읽기 실패
            }
        });
    }
}