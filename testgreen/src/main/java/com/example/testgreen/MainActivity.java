package com.example.testgreen;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dao.MyDataDao;

import java.util.List;

import de.greenrobot.dao.query.Query;
import greendao.MyData;

public class MainActivity extends ListActivity {

    private SQLiteDatabase db;
    private EditText edit_input;
    private Cursor cursor;

    private String str_input;

    private MyDataDao dataDao;

    //定义一个全局变量i 用来模拟年龄
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_input = (EditText) findViewById(R.id.edit_input);
        MyApplication myApplication=new MyApplication();
        MyDataDao myDataDao=myApplication.daoSession.getMyDataDao();
        SQLiteDatabase sqLiteDatabase=myApplication.db;
        String column=MyDataDao.Properties.Name.columnName;
        String order=column+" COLLATE LOCALIZED ASC";
        //获取全局的daoSession然后通过daoSession获取mydataDao
        dataDao = myApplication.daoSession.getMyDataDao();
        db = myApplication.db;
        Cursor cursor1=db.query(dataDao.getTablename(),dataDao.getAllColumns(),null,null,null,null,order);
        //获取name字段的行
        String textColumn = MyDataDao.Properties.Name.columnName;
        //按本地语言进行排序
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        //执行查询返回一个cursor
        cursor = db.query(dataDao.getTablename(), dataDao.getAllColumns(), null, null, null, null, orderBy);

        //SimpleCursorAdapter的使用
        String[] from = {textColumn, MyDataDao.Properties.Age.columnName};

        int[] to = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
                to);
        setListAdapter(adapter);

    }

    public void onMyClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                add_data();
                break;
            case R.id.search:
                search_data();
                break;
        }
    }

    //添加一条数据
    public void add_data() {
        str_input = edit_input.getText().toString();
        edit_input.setText("");

        //1、创建对象
        MyData data = new MyData();
        String name = str_input;
        String age = "20" + i++;
        String cool = "yes";
        //2、设置数据
        data.setName(name);
        data.setAge(age);
        data.setCool(cool);
        //3、插入数据
        dataDao.insert(data);
        cursor.requery();
    }

    //查询
    public void search_data() {
        str_input = edit_input.getText().toString();

        if (edit_input.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
        } else {
            Query query = dataDao.queryBuilder()
                    //查询name为""的数据
                    .where(MyDataDao.Properties.Name.eq(str_input))
                    //以Age排序
                    .orderAsc(MyDataDao.Properties.Age)
                    .build();
            List ls = query.list();   //查询返回的list
            Toast.makeText(MainActivity.this, "有" + ls.size() + "条数据符合", Toast.LENGTH_SHORT).show();
            edit_input.setText("");
        }
    }

    //删除
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //删除操作,通过id删除
        dataDao.deleteByKey(id);
        cursor.requery();
    }
}
