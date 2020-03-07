package com.jiaqi.torino.datastore.model.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Integer id;

    private String fetcherSource;

    private String title;

    private String url;

    private LocalDateTime publishTime;

    private LocalDateTime createTime;

    private String urlHash;

    private byte[] extraInfo;
}