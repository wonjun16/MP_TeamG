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

    private FirebaseFirestore firestore;
    private CollectionReference boardsCollection;

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

        // Firestore 인스턴스 및 "boards" 컬렉션 레퍼런스 가져오기
        firestore = FirebaseFirestore.getInstance();
        boardsCollection = firestore.collection("boards");

        // 게시물 리스트를 실시간으로 감시하고 업데이트를 수신함
        boardsCollection.addSnapshotListener(this, new com.google.firebase.firestore.EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot querySnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    // 오류 처리
                    return;
                }

                for (DocumentChange documentChange : querySnapshot.getDocumentChanges()) {
                    QueryDocumentSnapshot documentSnapshot = documentChange.getDocument();
                    String seq = documentSnapshot.getId();
                    String title = documentSnapshot.getString("title");

                    switch (documentChange.getType()) {
                        case ADDED:
                            // 게시물이 추가된 경우
                            titleList.add(title);
                            seqList.add(seq);
                            break;
                        case MODIFIED:
                            // 게시물이 수정된 경우
                            // 필요에 따라 업데이트 처리를 수행
                            break;
                        case REMOVED:
                            // 게시물이 삭제된 경우
                            int index = seqList.indexOf(seq);
                            if (index >= 0) {
                                seqList.remove(index);
                                titleList.remove(index);
                            }
                            break;
                    }
                }

                arrayAdapter.notifyDataSetChanged();
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