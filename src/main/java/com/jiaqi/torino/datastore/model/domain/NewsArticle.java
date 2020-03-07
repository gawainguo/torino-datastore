package com.jiaqi.torino.datastore.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticle {

    private String title;

    private String url;

    private String fetcherSource;

    private String publishTime;

}