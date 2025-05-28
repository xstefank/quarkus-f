package io.xstefank;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    @Named("KafkaMessageSenderImpl1")
    KafkaMessageSender kafkaMessageSenderImpl1;

    @Inject
    @Named("KafkaMessageSenderImpl2")
    KafkaMessageSender kafkaMessageSenderImpl2;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Log.error("KafkaMessageSenderImpl1: " + kafkaMessageSenderImpl1.name());
        Log.error("KafkaMessageSenderImpl2: " + kafkaMessageSenderImpl2.name());


        return "Hello from Quarkus REST";
    }
}
