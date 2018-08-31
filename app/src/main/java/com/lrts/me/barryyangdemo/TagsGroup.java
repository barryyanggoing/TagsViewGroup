package com.lrts.me.barryyangdemo;

import java.util.ArrayList;

/**
 * @desc:
 * @author: BarryYang
 * @date:create on 2018/8/31 11:45
 */
public class TagsGroup {
    private ArrayList<Category> list;

    public TagsGroup(ArrayList<Category> list) {
        this.list = list;
    }

    public ArrayList<Category> getList() {
        return list;
    }

    public void setList(ArrayList<Category> list) {
        this.list = list;
    }
}
