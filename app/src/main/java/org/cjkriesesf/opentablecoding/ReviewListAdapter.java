package org.cjkriesesf.opentablecoding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewViewHolder>{

    private final Context context;
    List<MovieReview> reviewList;

    public ReviewListAdapter(Context context) {
        this.context = context;
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
        final MovieReview review = reviewList.get(position);
        holder.displayTitle.setText(review.displayTitle);
        holder.mpaaRating.setText(review.mpaaRating);
        holder.byline.setText(review.byline);
        holder.headline.setText(review.headline);
        holder.summaryShort.setText(review.summaryShort);
        holder.publicationDate.setText(review.publicationDate);
        Glide
            .with(context)
            .load(review.multimedia.src)
            .apply(new RequestOptions()
                    .override(review.multimedia.width, review.multimedia.height)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground))
            .into(holder.multimedia);
    }

    @Override
    public int getItemCount() {
        return reviewList == null ? 0 : reviewList.size();
    }
}
