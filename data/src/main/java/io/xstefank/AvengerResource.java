package io.xstefank;

import io.xstefank.model.Avenger;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.List;

@Path("/avenger")
public class AvengerResource {

    @Inject
    AvengerRepository avengerRepository;

    @GET
    public List<Avenger> getAll() {
        return avengerRepository.getAll();
    }

    @GET
    @Path("/name")
    public List<Avenger> findByName(@QueryParam("name") String name) {
        return avengerRepository.findByName("%" + name + "%");
    }

    @GET
    @Path("/findByNameAnnotation")
    public List<Avenger> findByNameAnnotation(@QueryParam("name") String name) {
        return avengerRepository.findByNameAnnotation(name);
    }

    @POST
    public Avenger save(Avenger avenger) {
        return avengerRepository.save(avenger);
    }

    @POST
    @Path("/saveWithSQL")
    public int saveWithSQL(Avenger avenger) {
        return avengerRepository.saveWithSQL(avenger.name, avenger.civilName, avenger.snapped);
    }

    @POST
    @Path("/insert")
    public Avenger insert(Avenger avenger) {
        return avengerRepository.insert(avenger);
    }
}
