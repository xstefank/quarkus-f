package io.xstefank;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class DefaultPersistenceTest {


    @Test
    @TestTransaction
    @Disabled
    public void test_avenger_persistence() {
        assertEquals(0, Avenger.count());

        Avenger avenger = new Avenger();
        avenger.name = "Iron Man";
        avenger.civilname = "Tony Stark";
        avenger.snapped = false;

        avenger.persistAndFlush();

        assertEquals(1, Avenger.count());

        avenger.delete();

        assertEquals(0, Avenger.count());
    }
}
