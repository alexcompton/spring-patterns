package com.guitar.db.repository;

import com.guitar.db.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ManufacturerJpaRepository extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> getManufacturerByFoundedDateBefore(Date date);
    Manufacturer getFirstByNameStartingWith(String name);
    List<Manufacturer> getByActiveTrue();
    List<Manufacturer> getByActiveFalse();

    @Query(value = "SELECT " +
            "m.id, " +
            "m.name, " +
            "m.foundedDate, " +
            "m.averageYearlySales, " +
            "m.location_id as headquarters_id, " +
            "m.active "
            + "FROM Manufacturer m "
            + "LEFT JOIN Model mod ON (m.id = mod.manufacturer_id) "
            + "LEFT JOIN ModelType mt ON (mt.id = mod.modeltype_id) "
            + "WHERE mt.name = ?1", nativeQuery = true)
    List<Manufacturer> queryAllThatSellAcoustics(String name);

    // gets the named query form the Manufacturer model
    List<Manufacturer> getAllThatSellAcoustics(String name);
}
