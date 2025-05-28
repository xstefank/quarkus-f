package io.xstefank;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("KafkaMessageSenderImpl2")
public class KafkaMessageSenderImpl2 implements KafkaMessageSender{
    @Override
    public String name() {
        return KafkaMessageSenderImpl2.class.getSimpleName();
    }
}
