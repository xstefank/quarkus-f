package io.xstefank;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
//@TestProfile(ProfilePersistenceTest.PostgresConfigOverrideProfile.class)
public class ProfilePersistenceTest {

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

    public static class PostgresConfigOverrideProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Collections.singletonMap("quarkus.datasource.jdbc.url", "jdbc:postgresql://localhost:5433/quarkus");
        }
    }
}
