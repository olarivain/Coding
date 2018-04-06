package org.cjkriesesf.opentablecoding;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Field;

class MovieReview {

    @SerializedName(value="display_title")
    public String displayTitle;

    @SerializedName(value="mpaa_rating")
    public String mpaaRating;

    public String byline;
    public String headline;

    @SerializedName(value="summary_short")
    public String summaryShort;

    @SerializedName(value="publication_date")
    public String publicationDate;

    public Multimedia multimedia;
}
