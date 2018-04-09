package org.cjkriesesf.opentablecoding;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TextView displayTitle;
    public TextView mpaaRating;
    public TextView byline;
    public TextView headline;
    public TextView summaryShort;
    public TextView publicationDate;
    public ImageView multimedia;

    public ReviewViewHolder(View view) {
        super(view);
        this.view = view;
        displayTitle = itemView.findViewById(R.id.display_title);
        mpaaRating = itemView.findViewById(R.id.mpaa_rating);
        byline = itemView.findViewById(R.id.byline);
        headline = itemView.findViewById(R.id.headline);
        summaryShort = itemView.findViewById(R.id.summary_short);
        publicationDate = itemView.findViewById(R.id.publication_date);
        multimedia = itemView.findViewById(R.id.multimedia);
    }
}
