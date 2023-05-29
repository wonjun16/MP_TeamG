package com.example.mp_teamg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity {

    //관리자 메뉴
    FloatingActionsMenu floatingMenu;
    FloatingActionButton plusBtn;
    FloatingActionButton deleteBtn;

    //board 동적으로 생성을 위한 변수
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
        for(int i=0; i<100; i++)
            tableRow[i] = new TableRow(MainActivity.this);
        rowIndex = -1;
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
        //table row 마다 margintop : 70 / circleButton 크기 : 250, 250
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(250, 250);
        layoutParams.setMargins(0, 70, 0, 0);

        CircleButton circleButton = new CircleButton(MainActivity.this);
        circleButton.setImageResource(R.drawable.silhouette);
        circleButton.setColorFilter(Color.WHITE);
        circleButton.setColor(Color.GRAY);
        boardList.add(circleButton);

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listview.class);
                startActivity(intent);
            }
        });

        //table row가 필요할때마다 추가
        if (boardList.size() % 3 == 1){
            rowIndex += 1;
            tableLayout.addView(tableRow[rowIndex], layoutParams);
        }

        tableRow[rowIndex].addView(circleButton, layoutParams);
    }

    private void deleteBoard() {

    }

    //primary id로 어느 사람에게 연결될건지 결정
    protected class MyCircleButton extends CircleButton{
        int primary_id;

        public MyCircleButton(Context c, int id){
            super(c);
            primary_id = id;
        }
    }
}