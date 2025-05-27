package io.xstefank;

import io.xstefank.model.Avenger;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.Repository;
import jakarta.data.repository.Save;
import org.hibernate.StatelessSession;
import org.hibernate.annotations.processing.SQL;

import java.util.List;

@Repository
public interface AvengerRepository {

    @SQL("select * from avenger")
    List<Avenger> getAll();

    @SQL("select * from avenger where name like :name")
    List<Avenger> findByName(String name);

    @Find
    List<Avenger> findByNameAnnotation(String name);

    @Save
    Avenger save(Avenger avenger);


    @SQL("insert into avenger (name, civilname, snapped) values (:name, :civilName, :snapped)")
    int saveWithSQL(String name, String civilName, boolean snapped);

    @Insert
    Avenger insert(Avenger avenger);

    StatelessSession session();

    default void upsert(Avenger avenger) {
        session().upsert(avenger);
    }
}
