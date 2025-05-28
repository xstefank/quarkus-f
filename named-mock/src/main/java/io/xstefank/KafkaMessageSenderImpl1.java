package io.xstefank;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("KafkaMessageSenderImpl1")
public class KafkaMessageSenderImpl1 implements KafkaMessageSender{
    @Override
    public String name() {
        return KafkaMessageSenderImpl1.class.getSimpleName();
    }
}
