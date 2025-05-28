package io.xstefank;

import io.agroal.api.AgroalDataSource;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.narayana.jta.TransactionRunnerOptions;
import io.xstefank.model.Avenger;
import jakarta.inject.Inject;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("/tx")
public class TxResource {

    @Inject
    AgroalDataSource agroalDataSource;

    @GET
    public Response requiringNew() {
        TransactionRunnerOptions requiringNew = QuarkusTransaction.requiringNew();
        return requiringNew != null ? Response.ok().build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/select")
    public List<Avenger> select() {
        return QuarkusTransaction.requiringNew().timeout(10).call(() -> getAvengers());
    }

    @Inject
    UserTransaction userTransaction;

    @GET
    @Path("/user-transaction")
    public Response userTransaction() {
        try {
            userTransaction.begin();
            List<Avenger> avengers = getAvengers();
            userTransaction.commit();
            return Response.ok(avengers).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/insert")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insert() {
        int result = QuarkusTransaction.requiringNew().timeout(10).call(() -> createAvenger());

        return result > 0 ? Response.ok(result).build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete() {
        int result = QuarkusTransaction.requiringNew().timeout(10).call(() -> deleteLastAvenger());

        return result > 0 ? Response.ok(result).build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    private List<Avenger> getAvengers() {
        List<Avenger> result = new ArrayList<>();
        try (var connection = agroalDataSource.getConnection(); var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("select * from avenger");

            while (resultSet.next()) {
                Avenger avenger = new Avenger();
                avenger.id = resultSet.getLong("id");
                avenger.name = resultSet.getString("name");
                avenger.civilName = resultSet.getString("civilname");
                avenger.snapped = resultSet.getBoolean("snapped");
                result.add(avenger);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
        return result;
    }

    private final Random random = new Random();

    private int createAvenger() {
        try (var connection = agroalDataSource.getConnection(); var statement = connection.createStatement()) {
            return statement.executeUpdate("insert into avenger (id, name, civilname, snapped) values (nextval('Avenger_SEQ'), '"
                + random.nextInt() + "', '" + random.nextInt() + "', true);");
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int deleteLastAvenger() {
        try (var connection = agroalDataSource.getConnection(); var statement = connection.createStatement()) {
            return statement.executeUpdate("delete from avenger where id = currval('Avenger_SEQ');");
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
