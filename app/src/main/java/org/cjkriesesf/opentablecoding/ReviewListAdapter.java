package org.cjkriesesf.opentablecoding;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import timber.log.Timber;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewViewHolder>{

    interface LoadListener {
        void load();
    }

    private final LoadListener loadListener;

    private final Context context;
    List<MovieReview> reviewList;

    public ReviewListAdapter(Context context, LoadListener listener) {
        this.context = context;
        this.loadListener = listener;
    }

    public void setList(List<MovieReview> reviews) {
        this.reviewList = reviews;
    }

    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        // if at last item, request more
        if ((position >= getItemCount() - 1) && loadListener != null) {
            loadListener.load();
        }

        final MovieReview review = reviewList.get(position);
        holder.displayTitle.setText(review.displayTitle);
        holder.mpaaRating.setText(review.mpaaRating);
        holder.byline.setText(review.byline);
        holder.headline.setText(review.headline);
        holder.summaryShort.setText(review.summaryShort);
        holder.publicationDate.setText(formatDate(review.publicationDate));

        holder.view.setOnClickListener(v -> {
            Timber.d("clicked view");
            if (review.link != null && review.link.link_type == Link.LinkType.urlType.name()) {
                Intent showDetails = new Intent(context, ReviewDetailActivity.class);
                showDetails.putExtra(ReviewDetailActivity.REVIEW_URL, review.link.url);
                context.startActivity(showDetails);
            } else {
                Timber.e("Missing link or unsupported link type");
            }
        });
        Glide
            .with(context)
            .load(review.multimedia.src)
            .apply(new RequestOptions()
                    .override(review.multimedia.width, review.multimedia.height)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground))
            .into(holder.multimedia);
    }

    private String formatDate(String publicationDate) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(publicationDate);
        } catch (ParseException e) {
            Timber.d("Could not parse publication date");
            e.printStackTrace();
            return "";
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat("MMM dd yyyy");
        return fmtOut.format(date);
    }


    @Override
    public int getItemCount() {
        return reviewList == null ? 0 : reviewList.size();
    }
}
