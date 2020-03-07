package com.jiaqi.torino.datastore.service.message;

import com.jiaqi.torino.datastore.model.domain.NewsArticle;

public interface NewsMessageService {
    NewsArticle consumeNewsArticle(String message);
}