package io.xstefank;

import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaffeineCache {

    @CacheResult(cacheName = "caffeine-cache")
    public String getCache(int i) {
        Log.error("Computing value for id: " + i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Cache value for " + i;
    }

    @CacheInvalidateAll(cacheName = "caffeine-cache")
    public void invalidateCache() {
    }
}
