package com.example.rita.ron;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Politics extends Fragment implements LoaderManager.LoaderCallbacks<List<TopNews>> {

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;
    private static final String NEWS_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2018-09-01&api-key=test\n";
    public TopNewsAdapter mAdapter;
    private TextView mEmptyStateTextView;


    public Politics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_politics, container, false);
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Get a reference to the ConnectivityManager to check state of network connectivity

        // Get a reference to the ConnectivityManager to check state of network connectivity


        // get a reference to the activity in which the fragment is located since the activity has a context whilw a fragment doesn't
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);


        // Get details on the currently active default data network


        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        // create a new adapter
        mAdapter = new TopNewsAdapter(getActivity(), new ArrayList<TopNews>());

        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_text);
        listView.setEmptyView(mEmptyStateTextView);


        if (networkInfo != null && networkInfo.isConnected()) {

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {

            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            // Set empty state text to display "No internet connection"
            mEmptyStateTextView.setText(R.string.empty_text);
        }


        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                TopNews currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getmUrl());

                // Create a new intent to view the news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);


            }
        });

        return rootView;
    }

    @Override
    public Loader<List<TopNews>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(getActivity(), NEWS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<TopNews>> loader, List<TopNews> news) {


        // Hide loading indicator because the data has been loaded
        View loadingIndicator = getView().findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);


        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // Set empty state text to display "No earthquakes found."
        // mEmptyStateTextView.setText(R.string.empty_text);


        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<TopNews>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


}


