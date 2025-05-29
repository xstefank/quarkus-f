package io.xstefank;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestProfile(DevServiceProfilePersistenceTest.PostgresDevServicePortConfigOverrideProfile.class)
public class DevServiceProfilePersistenceTest {

    @Test
    @TestTransaction
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

    public static class PostgresDevServicePortConfigOverrideProfile implements QuarkusTestProfile {
        @Override
        public Set<String> tags() {
            return Set.of("different-dev-service");
        }
    }
}
