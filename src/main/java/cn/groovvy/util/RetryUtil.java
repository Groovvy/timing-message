package cn.groovvy.util;

import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author wanghuaan
 * @date 2022/10/17
 */
public class RetryUtil {

    private static RetryTemplate retryTemplate;

    public static RetryTemplate getRetryTemplate(){
        if(retryTemplate == null){
            retryTemplate = new RetryTemplate();
            retryTemplate.setRetryPolicy(new SimpleRetryPolicy());
            ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
            backOffPolicy.setInitialInterval(3000);
            backOffPolicy.setMultiplier(2);
            backOffPolicy.setMaxInterval(15000);
            retryTemplate.setBackOffPolicy(backOffPolicy);
            return retryTemplate;
        }
        return retryTemplate;
    }
}
