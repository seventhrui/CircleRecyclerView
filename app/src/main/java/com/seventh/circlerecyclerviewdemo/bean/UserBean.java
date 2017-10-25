package com.seventh.circlerecyclerviewdemo.bean;

import java.io.Serializable;

/**
 */

public class UserBean implements Serializable {
    public int id;
    public int userId;//获取所有用户使用该字段
    public String userName;
    public String useravatarUrl;
    public String userEpc;
    public int attendanceStatus;
    public int lessonId;

    public int getId() {
        if (0 == userId)
            return id;
        return userId;
    }
}
