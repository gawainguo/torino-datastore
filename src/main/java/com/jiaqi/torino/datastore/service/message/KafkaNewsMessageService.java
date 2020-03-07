package com.jiaqi.torino.datastore.service.message;

import com.google.gson.Gson;
import com.jiaqi.torino.datastore.exceptions.news.DuplicateNewsException;
import com.jiaqi.torino.datastore.model.domain.NewsArticle;
import com.jiaqi.torino.datastore.service.news.NewsService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaNewsMessageService implements NewsMessageService {

    private Logger logger = LogManager.getLogger(getClass());

    @Value("${app.mq.news-topic}")
    private String newsMessageTopic;

    @Autowired
    private NewsService newsService;

    @Override
    @KafkaListener(topics = "${app.mq.news-topic}")
    public NewsArticle consumeNewsArticle(String message) {
        NewsArticle news = new Gson().fromJson(message, NewsArticle.class);
        logger.info("Get news message from MQ: " + news.getTitle());
        try {
            newsService.createNews(news);
        } catch (DuplicateNewsException e) {
            logger.warn(e.getMessage() + ", skipped this news");
        }
        return news;
    }
}