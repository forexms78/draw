package com.example.draw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    int color = Color.BLACK;
    int type = 1;
    int fill = 1;
    Button cy, pi, fi, st, wh, gr, bl;
    View.OnClickListener cl;
    LinearLayout ar;

    class MyView extends View {
        Context con;
        Paint p;
        float x1=0, y1=0, x2=0, y2=0, u=0;

        MyView(Context c){
            super(c);
            con = c;
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            p.setColor(color);
            if(fill  ==1 ){
                p.setStyle(Paint.Style.FILL_AND_STROKE);
            }else if(fill == 2){
                p.setStyle(Paint.Style.STROKE);
            }
            if(type==1)
            canvas.drawLine(x1,y1,x2,y2,p);
            else if (type ==2)
                canvas.drawRect(x1,y1,x2,y2,p);
            else if(type ==3)
                canvas.drawCircle(x1,y1,u,p);
            String s = String.format("x1 = %5.1f y1=%5.1f x2-%5.1f y2=%5.1f  ",x1,y1,x2,y2);
            canvas.drawText(s,10,600,p);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    x1 = event.getX();
                    y1 = event.getY();
                    u = event.getX();
                    break;
                case MotionEvent.ACTION_UP :
                    break;
                case MotionEvent.ACTION_MOVE:
                        x2 = event.getX();
                        y2 = event.getY();
                        u = event.getX();
                        invalidate();
                        break;
            }
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView mv;
        mv = new MyView(this);
        setContentView(R.layout.activity_main);

        ar = (LinearLayout)findViewById(R.id.area);
        ar.addView(mv);

        cy = (Button)findViewById(R.id.cyan);
        pi = (Button)findViewById(R.id.pink);
        st = (Button)findViewById(R.id.stroke);
        fi = (Button)findViewById(R.id.fill);
        bl= (Button)findViewById(R.id.black);
        wh = (Button)findViewById(R.id.white);
        gr = (Button)findViewById(R.id.gray);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.cyan:
                        color = Color.CYAN;
                        break;
                    case R.id.pink:
                        color = Color.MAGENTA;
                        break;
                    case R.id.fill:
                        fill = 1;
                        break;
                    case R.id.stroke:
                        fill = 2;
                        break;
                }
            }
        };
        cy.setOnClickListener(cl);
        pi.setOnClickListener(cl);
        fi.setOnClickListener(cl);
        st.setOnClickListener(cl);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,101,0,"빨강");
        menu.add(0,102,0,"녹색");
        menu.add(0,103,0,"파랑");
        menu.add(0,104,0,"핑크");
        menu.add(0,105,0,"하늘");
        menu.add(0,106,0,"검정");
        menu.add(0,201,0,"직선");
        menu.add(0,202,0,"사각형");
        menu.add(0,203,0,"원");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 101:
                color = Color.RED;
                break;
            case 102:
                color = Color.GREEN;
                break;
            case 103:
                color = Color.BLUE;
                break;
            case 104:
                color = Color.MAGENTA;
                break;
            case 201:
                type =1;
                break;
            case 202:
                type =2;
                break;
            case 203:
                type =3;
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
