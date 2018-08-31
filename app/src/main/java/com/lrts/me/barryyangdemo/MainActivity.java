package com.lrts.me.barryyangdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.listview);
        final MyAdapter myAdapter = new MyAdapter(setData());
        findViewById(R.id.iv_scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        listView.setAdapter(myAdapter);
    }

    private List<TagsGroup> setData() {
        List<TagsGroup> list = new ArrayList<>();

        ArrayList<Category> categoryList1 = new ArrayList<>();
        categoryList1.add(new Category(0, "热播榜"));
        categoryList1.add(new Category(1, "独播榜"));
        categoryList1.add(new Category(2, "新片榜"));
        list.add(new TagsGroup(categoryList1));


        ArrayList<Category> categoryList2 = new ArrayList<>();
        categoryList2.add(new Category(0, "全部分类"));
        categoryList2.add(new Category(1, "儿歌"));
        categoryList2.add(new Category(2, "英语"));
        categoryList2.add(new Category(3, "少儿电影"));
        categoryList2.add(new Category(4, "少儿综艺"));
        categoryList2.add(new Category(5, "故事"));
        list.add(new TagsGroup(categoryList2));


        ArrayList<Category> categoryList3 = new ArrayList<>();
        categoryList3.add(new Category(0, "全部主题"));
        categoryList3.add(new Category(1, "百科"));
        categoryList3.add(new Category(2, "国学"));
        categoryList3.add(new Category(3, "手工"));
        categoryList3.add(new Category(4, "识字"));
        categoryList3.add(new Category(5, "数学"));
        categoryList3.add(new Category(6, "美术"));
        categoryList3.add(new Category(7, "舞蹈"));
        categoryList3.add(new Category(8, "艺术"));
        categoryList3.add(new Category(9, "教育"));
        list.add(new TagsGroup(categoryList3));

        ArrayList<Category> categoryList4 = new ArrayList<>();
        categoryList4.add(new Category(0, "全部年纪"));
        categoryList4.add(new Category(1, "0-3岁"));
        categoryList4.add(new Category(2, "4-6岁"));
        categoryList4.add(new Category(3, "7-10岁"));
        categoryList4.add(new Category(4, "11岁以上"));
        list.add(new TagsGroup(categoryList4));

        ArrayList<Category> categoryList5 = new ArrayList<>();
        categoryList5.add(new Category(0, "全部地区"));
        categoryList5.add(new Category(1, "大陆"));
        categoryList5.add(new Category(2, "港台"));
        categoryList5.add(new Category(3, "欧美"));
        categoryList5.add(new Category(4, "日本"));
        list.add(new TagsGroup(categoryList5));

        ArrayList<Category> categoryList6 = new ArrayList<>();
        categoryList6.add(new Category(0, "全部内容"));
        categoryList6.add(new Category(1, "会员"));
        categoryList6.add(new Category(2, "免费"));
        list.add(new TagsGroup(categoryList6));
        return list;
    }

}
