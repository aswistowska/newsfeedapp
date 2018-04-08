package org.swistowski.agata.newsfeedapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by agataswistowska on 07.04.2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    /**
     * Constructor
     *
     * @param context  The current context.
     */
    public NewsAdapter(@NonNull Context context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item_list, parent, false);
        }

        News currentNews = getItem(position);

        TextView categoryView = (TextView) listItemView.findViewById(R.id.news_category);
        categoryView.setText(currentNews.getmCategory());

        TextView dateView = (TextView) listItemView.findViewById(R.id.news_date);
        dateView.setText(currentNews.getmDate().toString());

        TextView titleView = (TextView) listItemView.findViewById(R.id.news_title);
        titleView.setText(currentNews.getmTitle());

        TextView storyView = (TextView) listItemView.findViewById(R.id.news_trail);
        storyView.setText(currentNews.getmStory());

        TextView authorView = listItemView.findViewById(R.id.news_author);
        if (currentNews.getmAuthor() != "") {
            authorView.setVisibility(View.VISIBLE);
            authorView.setText(currentNews.getmAuthor());
        } else {
            authorView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
