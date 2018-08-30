package com.lrts.me.barryyangdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ClassifyFilterView classifyFilterView = findViewById(R.id.tagsViewGroup);
        ArrayList<Category> list = new ArrayList<>();
        list.add(new Category(0,"全部类型"));
        list.add(new Category(1,"百科"));
        list.add(new Category(2,"国学"));
        list.add(new Category(3,"手工"));
        list.add(new Category(4,"儿儿歌"));
        list.add(new Category(5,"数学"));
        list.add(new Category(6,"美术"));
        list.add(new Category(7,"舞蹈"));
        list.add(new Category(8,"艺术"));
        list.add(new Category(9,"教教育"));
        list.add(new Category(10,"儿歌"));
        list.add(new Category(11,"英语"));
        list.add(new Category(12,"语文"));
        list.add(new Category(13,"少儿电影"));
        list.add(new Category(14,"少儿综艺"));
        list.add(new Category(15,"少儿综艺"));
        list.add(new Category(16,"少儿综艺"));
        list.add(new Category(17,"儿歌"));
        list.add(new Category(18,"少儿综艺"));
        list.add(new Category(19,"少儿综艺"));
        list.add(new Category(20,"少儿综艺"));
        list.add(new Category(21,"少儿综艺"));
        list.add(new Category(22,"儿歌"));
        list.add(new Category(23,"少儿综艺"));
        list.add(new Category(24,"少儿综艺"));
        list.add(new Category(25,"少儿综艺"));
        list.add(new Category(26,"少儿综艺"));
        list.add(new Category(27,"儿歌"));
        list.add(new Category(28,"少儿综艺"));
        list.add(new Category(29,"少儿综"));
        list.add(new Category(30,"综艺"));
        list.add(new Category(31,"少儿综艺"));
        list.add(new Category(32,"少儿艺"));
        classifyFilterView.setData(list);
    }

}
