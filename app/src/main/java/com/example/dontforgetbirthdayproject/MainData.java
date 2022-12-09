package com.example.dontforgetbirthdayproject;

public class MainData {
    private String tv_group;
    private String tv_name;
    private String tv_so_birth;
    private String tv_lu_birth;
    private String tv_memo;

    public MainData(String tv_group, String tv_name, String tv_so_birth, String tv_lu_birth, String tv_memo) {
        this.tv_group = tv_group;
        this.tv_name = tv_name;
        this.tv_so_birth = tv_so_birth;
        this.tv_lu_birth = tv_lu_birth;
        this.tv_memo = tv_memo;
    }

    public String getTv_group() {
        return tv_group;
    }

    public void setTv_group(String tv_group) {
        this.tv_group = tv_group;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_so_birth() {
        return tv_so_birth;
    }

    public void setTv_so_birth(String tv_so_birth) {
        this.tv_so_birth = tv_so_birth;
    }

    public String getTv_lu_birth() {
        return tv_lu_birth;
    }

    public void setTv_lu_birth(String tv_lu_birth) {
        this.tv_lu_birth = tv_lu_birth;
    }

    public String getTv_memo() {
        return tv_memo;
    }

    public void setTv_memo(String tv_memo) {
        this.tv_memo = tv_memo;
    }
}
