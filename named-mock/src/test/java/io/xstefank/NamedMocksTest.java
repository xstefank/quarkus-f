package io.xstefank;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Named;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class NamedMocksTest {

    @InjectMock
    @Named("KafkaMessageSenderImpl1")
    KafkaMessageSender kafkaMessageSender1;

    @InjectMock
    @Named("KafkaMessageSenderImpl2")
    KafkaMessageSender kafkaMessageSender2;

    @BeforeEach
    void setUp() {
        Mockito.when(kafkaMessageSender1.name()).thenReturn("mocked1");
        Mockito.when(kafkaMessageSender2.name()).thenReturn("mocked2");
    }

    @Test
    void testInjectMocks() {
        assertNotNull(kafkaMessageSender1);
        assertEquals("mocked1", kafkaMessageSender1.name());

        assertNotNull(kafkaMessageSender2);
        assertEquals("mocked2", kafkaMessageSender2.name());
    }

}