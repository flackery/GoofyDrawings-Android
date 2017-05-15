package com.appdirect.goofydrawings.api;

import java.util.List;

/**
 * Created by mehlert on 2017-05-12.
 */

public class ImageSearchResult {

    public List<Item> items;

    public static class Item {
        public String kind;
        public String title;
        public String htmlTitle;
        public String link;
        public String displayLink;
        public String snippet;
        public String htmlSnippet;
        public String mime;

        public Image image;
    }

    public static class Image {
        public String contextLink;
        public int height;
        public int width;
        public int byteSize;
        public String thumbnailLink;
        public int thumbnailHeight;
        public int thumbnailWidth;
    }

}
