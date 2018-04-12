package org.cjkriesesf.opentablecoding;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ReviewsViewModel extends ViewModel {

    public ReviewsViewModel () { 
        reviews = new MutableLiveData<>();
        reviews.setValue(new ArrayList<>());
        requestData();
    }
    
    public ReviewsViewModel (Application application) { }
    
    private MutableLiveData<List<MovieReview>> reviews = null;
    private boolean hasMore = true;  // initially assume that there's something to get
    
    public LiveData<List<MovieReview>> getReviews() {
        return reviews;
    }

    public void requestData() {
        if (!hasMore) {
            Timber.d("No more data to request");
        }
        MovieReviewService service = MovieReviewService.retrofit.create(MovieReviewService.class);
        // offset into the list of reivews is based on the number of reviews that we currenly have
        int offset =  reviews.getValue() == null ? 0 : reviews.getValue().size();
        Observable<MovieReviewResult> observable = service.movieReviewList(offset);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError);
    }

    private void handleResponse(MovieReviewResult result) {
        Timber.d("returned %d items", result.num_results);
        List<MovieReview> reviewList = reviews.getValue();
        reviewList.addAll(result.results);
        Timber.d("list has now %d items", reviews.getValue().size());
        reviews.setValue(reviewList);
        hasMore = result.has_more;
    }

    private void handleError(Throwable error) {
        Timber.e("call failed");
    }
}
