package org.cjkriesesf.opentablecoding;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.review_list)
    public RecyclerView recyclerViewList;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerViewList.setLayoutManager(layoutManager);

        // Get the view model
        ReviewsViewModel model = ViewModelProviders.of(this).get(ReviewsViewModel.class);

        // The adapter calls this listener when it needs more reviews to populate the list.
        final ReviewListAdapter adapter = new ReviewListAdapter(this, () -> {
            model.requestData();
            progressBar.setVisibility(View.VISIBLE);
        });

        model.getReviews().observe(this, reviewList -> {
            Timber.d("movie reviews updated, list has %d items", reviewList.size());
            adapter.setList(reviewList);
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        });

        recyclerViewList.setAdapter(adapter);
    }
}
