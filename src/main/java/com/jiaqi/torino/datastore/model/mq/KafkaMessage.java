package com.jiaqi.torino.datastore.model.mq;

import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessage {

    private String message;

    private String hashKey;

    private String topic;

    private Boolean success;
}