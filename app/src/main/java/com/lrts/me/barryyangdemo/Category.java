package com.lrts.me.barryyangdemo;

/**
 * @desc:
 * @author: BarryYang
 * @date:create on 2018/8/28 16:42
 */
public class Category {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
