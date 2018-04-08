package org.swistowski.agata.newsfeedapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final int EARTHQUAKE_LOADER_ID = 1;
    private NewsAdapter mAdapter;
    private TextView EmptyStateTextView;


    private static final String GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?section=travel&order-by=newest&show-fields=trailText&api-key=test";

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    /**
     * @param loader The Loader that has finished.
     * @param news   The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        EmptyStateTextView.setText(R.string.no_news);

        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    /**
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

            ListView newsListView = (ListView) findViewById(R.id.list);

            EmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            newsListView.setEmptyView(EmptyStateTextView);

            mAdapter = new NewsAdapter(this, new ArrayList<News>());

            newsListView.setAdapter(mAdapter);

            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    News currentNews = mAdapter.getItem(position);

                    Uri newsUri = Uri.parse(currentNews.getmUrl());

                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                    startActivity(websiteIntent);
                }
            });
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            TextView NoInternetTextView = (TextView) findViewById(R.id.no_internet_access);
            NoInternetTextView.setText(R.string.no_internet_access);
        }
    }
}