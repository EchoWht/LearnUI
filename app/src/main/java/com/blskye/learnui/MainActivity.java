package com.blskye.learnui;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.blskye.learnui.db.MyDatabaseHelper;
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

    private Button button5;
    private MyDatabaseHelper myDatabaseHelper;

    private Button button6;

    private Button button7;

    private Button button8;

    private Button button9;

    private Button button10;


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


        myDatabaseHelper=new MyDatabaseHelper(this,"BookStore.db",null,2);
        button5= (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);


        button6= (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);

        button7= (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);

        button8= (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);

        button9= (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);

        button10= (Button) findViewById(R.id.button10);
        button10.setOnClickListener(this);
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
            case R.id.button5:
                myDatabaseHelper.getWritableDatabase();
                break;
            case R.id.button6:
                SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put("name", "The Da Vinci Code");
                contentValues.put("author", "Dan Brown");
                contentValues.put("pages", 454);
                contentValues.put("price", 16.96);
                //插入数据
                db.insert("Book", null, contentValues);
                contentValues.clear();

                contentValues.put("author", "Dan Brown");
                contentValues.put("pages", 510);
                contentValues.put("price", 19.95);
                db.insert("Book", null, contentValues);
                contentValues.clear();

                contentValues.put("name", "Hello");
                contentValues.put("author", "wang");
                contentValues.put("pages", 454);
                contentValues.put("price", 16.96);
                //插入数据
                db.insert("Book", null, contentValues);
                Toast.makeText(this,"Insert ",Toast.LENGTH_LONG).show();
                break;
            case R.id.button7:
                SQLiteDatabase db2=myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues2=new ContentValues();
                contentValues2.put("price",19.00);
                db2.update("Book",contentValues2,"name=?",new String[]{"The Da Vinci Code"});
                break;
            case R.id.button8:
                SQLiteDatabase db3=myDatabaseHelper.getWritableDatabase();
                db3.delete("Book","name=?",new String[]{"Hello"});
                Toast.makeText(this,"Delete ",Toast.LENGTH_LONG).show();
                break;
            case R.id.button9:
                SQLiteDatabase db4=myDatabaseHelper.getWritableDatabase();
                Cursor cursor=db4.query("Book",null,null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d("main","Book:"+name+author+pages+price);


                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.button10:
                SQLiteDatabase db5=myDatabaseHelper.getWritableDatabase();
//                开启事务
                db5.beginTransaction();
                try {
                    db5.delete("Book",null,null);//清空Book表
//                    if (true){
//                        // 在这里手动抛出一个异常，让事务失败
//                        throw new NullPointerException();
//                    }
                    ContentValues contentValues1=new ContentValues();
                    contentValues1.put("name","Hello World");
                    contentValues1.put("author", "Wanghaotian");
                    contentValues1.put("pages", 720);
                    contentValues1.put("price", 20.85);
                    db5.insert("Book", null, contentValues1);
                    db5.setTransactionSuccessful();
                    Toast.makeText(this,"OK",Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    db5.endTransaction();
                }

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
