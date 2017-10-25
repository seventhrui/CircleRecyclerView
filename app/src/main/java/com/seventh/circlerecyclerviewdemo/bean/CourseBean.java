package com.seventh.circlerecyclerviewdemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 */
public class CourseBean implements Serializable {
    public String lessonEnglishName;
    public long classEndTime;
    public String classRemark;
    public int lessonId;
    public long classBeginTime;
    public int classNum;
    public String lessonName;
    public List<UserBean> teachers;
    public List<UserBean> students;
}
