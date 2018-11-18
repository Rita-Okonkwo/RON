package com.example.rita.ron;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<TopNews>> {
    //create context of EarthquakeLoader
    /**
     * Query URL
     */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    public void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<TopNews> loadInBackground() {

        // TODO: Implement this method

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<TopNews> news = QueryUtils.fetchNewsData(mUrl);
        return news;


    }
}

