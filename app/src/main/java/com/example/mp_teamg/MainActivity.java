package com.example.mp_teamg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionsMenu floatingMenu;
    FloatingActionButton plusBtn;
    FloatingActionButton deleteBtn;

    TableLayout tableLayout;
    TableRow tableRow[];
    int rowIndex;
    ArrayList<CircleButton> boardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingMenu = (FloatingActionsMenu) findViewById(R.id.floatingMenu);
        plusBtn = (FloatingActionButton) findViewById(R.id.plusBtn);
        deleteBtn = (FloatingActionButton) findViewById(R.id.deleteBtn);

        tableLayout = (TableLayout) findViewById(R.id.table);
        tableRow = new TableRow[100];
        rowIndex = 0;
        boardList = new ArrayList<>();

        //관리자로 로그인 시 floating menu 활성화
        if (managerLogin())
            floatingMenu.setVisibility(View.VISIBLE);

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBoard();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //관리자 아이디로 로그인 했을 경우 : true / else : false
    private boolean managerLogin() {
        return true;
    }

    //사용자별 게시판으로 이어지는 원형 버튼 생성
    private void createBoard() {
        CircleButton circleButton = new CircleButton(MainActivity.this);
        boardList.add(circleButton);
        if (rowIndex + 3 <= boardList.size())
            rowIndex += 1;
        tableRow[rowIndex].addView(circleButton);
        if (boardList.size() % 3 == 0)
            tableLayout.addView(tableRow[rowIndex]);
    }

    private void deleteBoard() {

    }
}