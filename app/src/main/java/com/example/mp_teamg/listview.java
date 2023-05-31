import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private Button reg_button;
    private String userId;
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<String> seqList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        userId = getUserId();

        listView = findViewById(R.id.listView_list);
        reg_button = findViewById(R.id.reg_button);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titleList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this, adapterView.getItemAtPosition(i) + " 클릭", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("board_seq", seqList.get(i));
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, RegisterActivity.class);
                intent.putExtra("userid", userId);
                startActivity(intent);
            }
        });

        // Firebase Realtime Database의 "boards" 레퍼런스를 가져옴
        databaseRef = FirebaseDatabase.getInstance().getReference("boards");

        // 게시물 리스트를 실시간으로 감시하고 업데이트를 수신함
        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                String seq = dataSnapshot.getKey();
                String title = dataSnapshot.child("title").getValue(String.class);
                titleList.add(title);
                seqList.add(seq);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                // 게시물이 변경된 경우 업데이트 처리를 수행.
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String seq = dataSnapshot.getKey();
                int index = seqList.indexOf(seq);
                if (index >= 0) {
                    seqList.remove(index);
                    titleList.remove(index);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                // 게시물의 순서가 변경된 경우 처리
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 데이터베이스 읽기/쓰기 실패
            }
        });
    }

    private String getUserId() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            return firebaseUser.getUid();
        } else {
            // 로그인되지 않은 경우.
            return "";
        }
    }
}