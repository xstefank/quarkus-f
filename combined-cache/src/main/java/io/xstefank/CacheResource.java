package io.xstefank;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class CacheResource {

    @Inject
    CaffeineCache caffeineCache;

    @GET
    @Path("/caffeine/{id}")
    public String getCaffeineCache(int id) {
        return caffeineCache.getCache(id);
    }

    @GET
    @Path("/caffeine/invalidate")
    public void invalidateCaffeine() {
        caffeineCache.invalidateCache();
    }


}
