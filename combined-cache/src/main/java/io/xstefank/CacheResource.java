package io.xstefank;

import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.quarkus.cache.CaffeineCache;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Set;

@Path("/caffeine")
public class CacheResource {

    @Inject
    MyCache caffeineCache;

    @GET
    @Path("/{id}")
    public String getCaffeineCache(int id) {
        return caffeineCache.getCache(id);
    }

    @GET
    @Path("/invalidate")
    public void invalidateCaffeine() {
        caffeineCache.invalidateCache();
    }


    @CacheName("caffeine-cache")
    Cache cache;

    @GET
    @Path("/all-keys")
    public Set<Object> getAllCacheKeys() {
        return cache.as(CaffeineCache.class).keySet();
    }
}
