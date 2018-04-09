package org.cjkriesesf.opentablecoding;

import com.google.gson.annotations.SerializedName;

public class Link {
    @SerializedName(value="type")
    public String link_type;
    public String url;
    @SerializedName(value="suggested_link_text")
    public String suggestedLinkText;
}
