package com.jiaqi.torino.datastore.service.news;

import java.util.concurrent.TimeUnit;

import com.jiaqi.torino.datastore.constants.LockConstants;
import com.jiaqi.torino.datastore.dao.NewsDao;
import com.jiaqi.torino.datastore.dao.example.NewsExample;
import com.jiaqi.torino.datastore.exceptions.lock.LockFailedException;
import com.jiaqi.torino.datastore.exceptions.news.DuplicateNewsException;
import com.jiaqi.torino.datastore.model.domain.NewsArticle;
import com.jiaqi.torino.datastore.model.entity.News;
import com.jiaqi.torino.datastore.utils.IterableUtils;
import com.jiaqi.torino.datastore.utils.TimeUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class NewsServiceImpl implements NewsService {

    private Logger logger = LogManager.getLogger(getClass());

    private static Integer NEWS_LOCK_EXPIRE = 20000;

    private static Integer NEWS_LOCK_WAITING = 5000;

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private NewsDao newsDao;

    @Override
    public News createNews(NewsArticle article) {
        News entity = getNewsEntity(article);
        String lockKey = String.format(LockConstants.NEWS_LOCK_KEY_TEMPLATE, entity.getUrlHash());
        RLock lock = redisson.getLock(lockKey);
        try {
            boolean res = lock.tryLock(NEWS_LOCK_WAITING, NEWS_LOCK_EXPIRE, TimeUnit.MILLISECONDS);
            if (!res) {
                logger.error("Create news failed because cannot lock " + lockKey + " in " + NEWS_LOCK_WAITING);
                throw new LockFailedException("Cannot get lock in waiting time: " + NEWS_LOCK_WAITING);
            }
            News exist = IterableUtils.getFirst(
                    newsDao.selectByExample(new NewsExample().createCriteria().andUrlHashEqualTo(entity.getUrlHash()).example()),
                    null);
            if (exist != null) {
                logger.warn(String.format("Create news failed: Duplicated news with hash %s \n title: [%s]",
                        entity.getUrlHash(), entity.getTitle()));
                throw new DuplicateNewsException("Duplicate news found for " + entity.getUrlHash());
            }
            newsDao.insert(entity);
            logger.info("Create news success: " + entity.getUrlHash());
            return entity;
        } catch (InterruptedException e) {
            logger.error("Interrupted during waiting for news lock: " + lockKey);
            throw new LockFailedException("Interrupted during waiting for news lock" + lockKey);
        } finally {
            try {
                lock.unlock();
            } catch (IllegalMonitorStateException e) {
                logger.warn("Unlock redis lock failed. Current lock in illegal state, maybe auto expired: " + lockKey);
            }
        }
    }

    private News getNewsEntity(NewsArticle article) {
        String urlHash = DigestUtils.md5DigestAsHex(article.getUrl().getBytes()).toString();
        return News.builder()
                .title(article.getTitle())
                .url(article.getUrl())
                .fetcherSource(article.getFetcherSource())
                .publishTime(TimeUtils.parse(article.getPublishTime()))
                .createTime(TimeUtils.now())
                .urlHash(urlHash)
                .build();
    }
}
