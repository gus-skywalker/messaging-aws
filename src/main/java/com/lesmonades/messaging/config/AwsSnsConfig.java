package com.lesmonades.messaging.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.Region;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSnsConfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKeyId}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.sns.topic.accounts.arn}")
    private String accountSnsTopic;

    @Bean
    public AmazonSNS build() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSNSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(String.valueOf(Region.SA_SaoPaulo))
                .build();
    }

    @Bean(name = "accountEventsTopic")
    public Topic snsAccountTopicBuilder() {
        return new Topic().withTopicArn(accountSnsTopic);
    }
}
