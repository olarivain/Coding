package org.cjkriesesf.opentablecoding;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MovieReviewService {
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static final String SERVICE_PATH  = "svc/movies/v2/reviews/dvd-picks.json";

    @GET(SERVICE_PATH + "?order=by-date&api-key=" + BuildConfig.SERVICE_API_KEY)
    Observable<MovieReviewResult> movieReviewList(@Query("offset") int offset);
}
