package org.cjkriesesf.opentablecoding;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ReviewListAdapter adapter;
    private CompositeDisposable compositeDisposable;


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
        compositeDisposable = new CompositeDisposable();
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

        Observable<MovieReviewResult> observable = service.movieReviewList();

        compositeDisposable.add(observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(MovieReviewResult result) {
        Timber.d("returned %d items", result.num_results);
        adapter.setList(result.results);
        adapter.notifyDataSetChanged();
    }

    private void handleError(Throwable error) {
        Timber.e("call failed");
    }
}
