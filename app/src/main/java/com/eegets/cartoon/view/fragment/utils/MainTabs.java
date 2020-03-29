package com.eegets.cartoon.view.fragment.utils;

import com.eegets.cartoon.R;
import com.eegets.cartoon.view.fragment.CategoryTabFragment;
import com.eegets.cartoon.view.fragment.HomeTabFragment;
import com.eegets.cartoon.view.fragment.MineTabFragment;

public enum MainTabs {

    Home(0,"首页", R.drawable.tab_home, HomeTabFragment.class),
    Brand(1,"分类", R.drawable.tab_home, CategoryTabFragment.class),
    Mine(2,"个人中心", R.drawable.tab_home, MineTabFragment.class);
 
    private int i;
    private String name;
    private int icon;
    private Class<?> cla;
 
     MainTabs(int i, String name, int icon, Class<?> cla) {
        this.i = i;
        this.name = name;
        this.icon = icon;
        this.cla = cla;
    }
 
    public int getI() {
        return i;
    }
 
    public void setI(int i) {
        this.i = i;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getIcon() {
        return icon;
    }
 
    public void setIcon(int icon) {
        this.icon = icon;
    }
 
    public Class<?> getCla() {
        return cla;
    }
 
    public void setCla(Class<?> cla) {
        this.cla = cla;
    }
}