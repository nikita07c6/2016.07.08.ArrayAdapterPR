package com.example.ccei.arrayadapterpj;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView taraList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taraList = (ListView) findViewById(R.id.tara_list);

        Resources res = getResources();

        ArrayList<TARAValueObject> items = new ArrayList<TARAValueObject>();
        items.add(new TARAValueObject("소연", res.getDrawable(R.drawable.t_ara_icon_soyeon), 0, res.getDrawable(R.drawable.heart)));
        items.add(new TARAValueObject("지연", res.getDrawable(R.drawable.t_ara_icon_jiyeon), 0, res.getDrawable(R.drawable.heart)));
        items.add(new TARAValueObject("큐리", res.getDrawable(R.drawable.t_ara_icon_qri), 0, res.getDrawable(R.drawable.heart)));
        items.add(new TARAValueObject("보람", res.getDrawable(R.drawable.t_ara_icon_boram), 0, res.getDrawable(R.drawable.heart)));
        items.add(new TARAValueObject("효민", res.getDrawable(R.drawable.t_ara_icon_hyomin), 0, res.getDrawable(R.drawable.heart)));
        items.add(new TARAValueObject("은정", res.getDrawable(R.drawable.t_ara_icon_eunjung), 0, res.getDrawable(R.drawable.heart)));

        TARAArrayAdapter adapter = new TARAArrayAdapter(this, items);

        taraList.setAdapter(adapter);

       // taraList.setOnItemClickListener(itemListener);
    }

    // 주석 처리 하면 아이템 각각에 이벤트를 걸 수 있다.
    // 아이템 행을 터치하면 발생하는 이벤트이며 아이템안에 존재하는 각 위젯에 이벤트를
    // 설정시 itemclick 이벤트가 먼저 발생하게 되므로 이 이벤트는 설정하지 않는 것이 효율적이다.
    /*private AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            *//* LinearLayout itemRoot = (LinearLayout)view;
            String memberName = ((TextView)((LinearLayout) view).getChildAt(1)).getText().toString(); */
    // -> 두 가지 방법
    /*

            TARAValueObject valueObject = (TARAValueObject)parent.getItemAtPosition(position);

            Toast.makeText(MainActivity.this, valueObject.memberName + "를 선택하셨습니다.", Toast.LENGTH_SHORT).show();
        }
    }; */

    // Adapter (보통 내부 클래스 사용)
    // 가장 기본이 되는 어댑터이고 개발자의 능력에 따라 자료구조를 다양하게 재정의하여 사용할 수 있다.
    // 유연성이 좋은 어댑터이다.
    private class TARAArrayAdapter extends ArrayAdapter<TARAValueObject> {

        private Context currentContext;    // 만들어지는 위치

        public TARAArrayAdapter(Context context, List<TARAValueObject> objects) {
            super(context, 0, objects);
            currentContext = context;
        }

        /*
           이 메소드는 어댑터뷰에서 그려질 아이템의 수만큼 호출된다.
           또한 화면에서 보여질 때 마다 매번 호출됨을 명심해 주세요
        */


        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            final TARAValueObject valueObject = (TARAValueObject)getItem(position);
            final ViewHolder viewHolder; // 추가 후 하트 변경 가능

            // convertView인자는 그려질 아이템의 root(보통)값을 의미한다. 아이템에 그려질
            // 위젯이 하나라면 그 위젯의 객체가 될 수 있다.
            // 안드로이드 시스템에 의해 convertView 객체는 계속 재사용도리 수 있음을 명심하세요.
            // 없으면 계속 inflation함, 필수 코드
            if(convertView == null){
                convertView = LayoutInflater.from(currentContext).inflate(R.layout.tara_list_view_item, null);
                viewHolder = new ViewHolder();
                viewHolder.memberImageWD = (ImageView)convertView.findViewById(R.id.member_image);
                viewHolder.memberNameWD = (TextView)convertView.findViewById(R.id.member_name);
                viewHolder.heartWD = (ImageView)convertView.findViewById(R.id.heart);
                viewHolder.countWD = (TextView)convertView.findViewById(R.id.count);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.memberImageWD.setImageDrawable(valueObject.memberImage);
            viewHolder.memberNameWD.setText(valueObject.memberName);
            viewHolder.heartWD.setImageDrawable(valueObject.heart);
            viewHolder.countWD.setText(Integer.toString(valueObject.count));

            viewHolder.memberImageWD.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Toast.makeText(currentContext, valueObject.memberName + "을 선택함.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            viewHolder.heartWD.setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    valueObject.count += 1;
                    viewHolder.countWD.setText(Integer.toString(valueObject.count));

                    return false;
                }
            });

            // 한 행이 그려질 root 레이아웃을 리턴해야한다.
            return convertView;
        }
        private class ViewHolder {
            public ImageView memberImageWD;
            public TextView memberNameWD;
            public ImageView heartWD;
            public TextView countWD;
        }
    }
}