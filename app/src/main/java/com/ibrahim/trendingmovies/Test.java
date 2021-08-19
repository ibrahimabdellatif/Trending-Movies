package com.ibrahim.trendingmovies;

import java.util.List;

public class Test {
    private int userId;
    private int id;
    private String title;
    private String body;
    private List<Test> testList;

    public Test(List<Test> testList) {
        this.testList = testList;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public List<Test> getTestList() {
        return testList;
    }
}
