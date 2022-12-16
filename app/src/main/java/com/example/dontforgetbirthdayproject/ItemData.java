package com.example.dontforgetbirthdayproject;

public class ItemData {
    private String tv_item_id;
    private String tv_item_name;
    private int tv_item_solar_birth;
    private int tv_item_lunar_birth;
    private String tv_item_memo;
    private boolean item_alram_on;

    public ItemData(String tv_item_id, String tv_item_name, int tv_item_solar_birth, int tv_item_lunar_birth, String tv_item_memo, boolean item_alram_on) {
        this.tv_item_id = tv_item_id;
        this.tv_item_name = tv_item_name;
        this.tv_item_solar_birth = tv_item_solar_birth;
        this.tv_item_lunar_birth = tv_item_lunar_birth;
        this.tv_item_memo = tv_item_memo;
        this.item_alram_on = item_alram_on;
    }

    public String getTv_item_id() {
        return tv_item_id;
    }

    public void setTv_item_id(String tv_item_id) {
        this.tv_item_id = tv_item_id;
    }

    public String getTv_item_name() {
        return tv_item_name;
    }

    public void setTv_item_name(String tv_item_name) {
        this.tv_item_name = tv_item_name;
    }

    public int getTv_item_solar_birth() {
        return tv_item_solar_birth;
    }

    public void setTv_item_solar_birth(int tv_item_solar_birth) {
        this.tv_item_solar_birth = tv_item_solar_birth;
    }

    public int getTv_item_lunar_birth() {
        return tv_item_lunar_birth;
    }

    public void setTv_item_lunar_birth(int tv_item_lunar_birth) {
        this.tv_item_lunar_birth = tv_item_lunar_birth;
    }

    public String getTv_item_memo() {
        return tv_item_memo;
    }

    public void setTv_item_memo(String tv_item_memo) {
        this.tv_item_memo = tv_item_memo;
    }

    public boolean isItem_alram_on() {
        return item_alram_on;
    }

    public void setItem_alram_on(boolean item_alram_on) {
        this.item_alram_on = item_alram_on;
    }
}
