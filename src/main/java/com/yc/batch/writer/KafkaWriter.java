package com.yc.batch.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

/**
 * 描述:
 *
 * @author YC
 * @create 2018-10-07 11:55
 */
public class KafkaWriter <T> implements ItemWriter<T>{
    private Logger logger = LoggerFactory.getLogger(KafkaWriter.class);

    @Value("kafka.topic")
    private String topic ;

    @Autowired
    private KafkaTemplate<T, T> kafkaTemplate;
    /**
     * Process the supplied data element. Will not be called with any null items
     * in normal operation.
     *
     * @param items items to be written
     * @throws Exception if there are errors. The framework will catch the
     *                   exception and convert or rethrow it as appropriate.
     */
    @Override
    public void write(List<? extends T> items) throws Exception {
        if(items==null||items.size()<1){
            return ;
        }
        for(T item: items){
            ListenableFuture<SendResult<T, T>> future = kafkaTemplate.send(topic, item);
            future.addCallback(new ListenableFutureCallback<SendResult<T, T>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    logger.info("Produce: The message failed to be sent:" + throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<T, T> stringObjectSendResult) {
                    //TODO 业务处理
                    logger.info("Produce: 成功写入数据");
                    logger.info("Produce: _+_+_+_+_+_+_+ rsult: " + stringObjectSendResult.toString());
                }
            });
        }

    }

}
