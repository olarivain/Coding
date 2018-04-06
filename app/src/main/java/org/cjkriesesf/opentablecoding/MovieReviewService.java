package org.cjkriesesf.opentablecoding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

interface MovieReviewService {
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //svc/movies/v2/reviews/dvd-picks.json?order=by-date&api-key=
    @GET("svc/movies/v2/reviews/dvd-picks.json?order=by-date&api-key=" + BuildConfig.SERVICE_API_KEY)
    Call<MovieReviewResult> movieReviewList();
}
