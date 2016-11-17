package com.example.wuzihao.myfood;

/**
 * Created by wuzihao on 2016/11/16.
 */

public class MyFoodMenu {
    private String name;
    private String tag;
    private String addr;
    public MyFoodMenu(String name,String tag,String addr){
        this.name = name;
        this.tag = tag;
        this.addr = addr;
    }
    public String getName(){
        return this.name;
    }
    public String getTag(){
        return this.tag;
    }
    public String getAddr(){
        return this.addr;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setTag(String tag){
        this.tag = tag;
    }
    public void setAddr(String addr){
        this.addr = addr;
    }


}
