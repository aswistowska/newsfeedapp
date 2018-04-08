package org.swistowski.agata.newsfeedapp;

/**
 * Created by agataswistowska on 07.04.2018.
 */

public class News {
    private String mCategory;
    private String mDate;
    private String mTitle;
    private String mStory;
    private String mUrl;
    private String mAuthor;

    public News (String category, String date, String title, String story, String url, String author) {
        mCategory = category;
        mDate = date;
        mTitle = title;
        mStory = story;
        mUrl = url;
        mAuthor = author;
    }

    public String getmCategory() {
        return mCategory;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmStory() {
        return mStory;
    }
    public String getmAuthor() {
        return mAuthor;
    }
}
