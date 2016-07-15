package com.blskye.learnui;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blskye.learnui.adapter.FruitAdapter;
import com.blskye.learnui.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button button;
    private EditText editText;
    private ImageView imageView;
    private Button button2;
    private ProgressBar progressBar;
    private Button button3;
    private Button button4;

    private String[] data={"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango","dsad"};

    private List<Fruit> fruitList=new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏原有标题框
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button);
        editText= (EditText) findViewById(R.id.edit);
        button.setOnClickListener(this);

        button2= (Button) findViewById(R.id.button2);
        imageView= (ImageView) findViewById(R.id.imageView);
        button2.setOnClickListener(this);

        button3= (Button) findViewById(R.id.button3);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        button3.setOnClickListener(this);

        button4= (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        initFruits();
        FruitAdapter fruitAdapter=new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);


        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String inputText = editText.getText().toString();
                Toast.makeText(MainActivity.this, inputText,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                imageView.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.id.button3:
//                设置进度条
                int progress=progressBar.getProgress();
                progress=progress+10;
                progressBar.setProgress(progress);
                if (progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                    button3.setText("关闭加载");
                }else{
                    progressBar.setVisibility(View.GONE);
                    button3.setText("显示加载");
                }
                break;
            case R.id.button4:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("弹出了个对话的标题");
                dialog.setMessage("这段对话很重要");
                dialog.setCancelable(false);
                dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("点击了好的");
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("点击了取消");
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
    private void initFruits(){
        Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
        fruitList.add(mango);
    }

}
