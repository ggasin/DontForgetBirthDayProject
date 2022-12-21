package com.example.dontforgetbirthdayproject;

public class ItemData {
    private String tv_item_group;
    private String tv_item_name;
    private String tv_item_solar_birth;
    private String tv_item_lunar_birth;
    private String tv_item_memo;
    private int item_alram_on;

    public int getItem_alram_on() {
        return item_alram_on;
    }

    public void setItem_alram_on(int item_alram_on) {
        this.item_alram_on = item_alram_on;
    }

    public ItemData(String tv_item_name, String tv_item_group, String tv_item_solar_birth, String tv_item_lunar_birth, String tv_item_memo, int item_alram_on) {
        this.tv_item_group = tv_item_group;
        this.tv_item_name = tv_item_name;
        this.tv_item_solar_birth = tv_item_solar_birth;
        this.tv_item_lunar_birth = tv_item_lunar_birth;
        this.tv_item_memo = tv_item_memo;
        this.item_alram_on = item_alram_on;
    }

    public String getTv_item_group() {
        return tv_item_group;
    }

    public void setTv_item_group(String tv_item_group) {
        this.tv_item_group = tv_item_group;
    }

    public String getTv_item_name() {
        return tv_item_name;
    }

    public void setTv_item_name(String tv_item_name) {
        this.tv_item_name = tv_item_name;
    }

    public String getTv_item_solar_birth() {
        return tv_item_solar_birth;
    }

    public void setTv_item_solar_birth(String tv_item_solar_birth) {
        this.tv_item_solar_birth = tv_item_solar_birth;
    }

    public String getTv_item_lunar_birth() {
        return tv_item_lunar_birth;
    }

    public void setTv_item_lunar_birth(String tv_item_lunar_birth) {
        this.tv_item_lunar_birth = tv_item_lunar_birth;
    }

    public String getTv_item_memo() {
        return tv_item_memo;
    }

    public void setTv_item_memo(String tv_item_memo) {
        this.tv_item_memo = tv_item_memo;
    }


}
