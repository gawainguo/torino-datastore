package com.jiaqi.torino.datastore.service.news;

import com.jiaqi.torino.datastore.model.domain.NewsArticle;
import com.jiaqi.torino.datastore.model.entity.News;

public interface NewsService {

    News createNews(NewsArticle article);

}