package io.xstefank;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logmanager.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.UUID;

@Path("/")
public class Resource {

    private final Logger logger = LoggerFactory.getLogger("SLF4JLogger");

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
//        MDC.put("requestId", UUID.randomUUID().toString());
        // doesn't work
        Log.error("Test log error message");

//        org.slf4j.MDC.put("test-mdc-slf4j", UUID.randomUUID().toString());
        logger.error("Test log message from SLF4J");

        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/config-reload")
    public Response reloadConfig() throws JoranException {
        Log.info("Reloading configuration...");
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        ContextInitializer ci = new ContextInitializer(loggerContext);
        ci.autoConfig();

        return Response.ok("Configuration reloaded").build();
    }

}
