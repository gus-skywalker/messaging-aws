package com.lesmonades.messaging.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.Topic;
import com.lesmonades.messaging.domain.DTO.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsSnsService {

    private final AmazonSNS snsClient;

    @Qualifier("accountEventsTopic")
    private final Topic accountTopic;

    public void publish(MessageDTO message) {
        // FIFO Request
        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(accountTopic.getTopicArn())
                .withMessage(message.message())
                .withMessageGroupId("messageGroup2")
                .withMessageDeduplicationId("deduplicateGroup2");

        PublishResult result = snsClient.publish(publishRequest);
        System.out.println(result);

    }


}
