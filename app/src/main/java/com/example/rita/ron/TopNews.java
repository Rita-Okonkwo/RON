package com.example.rita.ron;

public class TopNews {
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mUrl;


    public TopNews(String title, String section, String date, String url){
        mTitle = title;
        mSection = section;
        mDate = date;
        mUrl = url;
    }

    public String getmTitle() {
        return mTitle;
    }
    public String getmSection(){
        return mSection;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }
}
