package org.cjkriesesf.opentablecoding;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ReviewListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerViewList = findViewById(R.id.review_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerViewList.setLayoutManager(layoutManager);
        adapter = new ReviewListAdapter(this);
        recyclerViewList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private void getData() {

        MovieReviewService service = MovieReviewService.retrofit.create(MovieReviewService.class);

        Call<MovieReviewResult> call = service.movieReviewList();

        call.enqueue(new Callback<MovieReviewResult>() {
            @Override
            public void onResponse(Call<MovieReviewResult> call, Response<MovieReviewResult> response) {
                // handle success
                Timber.d(response.body().toString());
                Timber.d("returned %d items", response.body().num_results);
                adapter.setList(response.body().results);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieReviewResult> call, Throwable t) {
                // handle failure
                Timber.e("call failed");
            }

        });

    }
}
