package com.guitar.db.repository;

import com.guitar.db.model.Model;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ModelJpaRepository extends JpaRepository<Model, Long> {
    List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal lowest, BigDecimal highest);
    List<Model> findByModelTypeNameIn(List<String> modelTypeNames);

    @Query("select m " +
            "from Model m " +
            "where m.price >= :lowest " +
            "and m.price <= :highest " +
            "and m.woodType " +
            "like :wood")
    List<Model> queryByPriceRangeAndWoodType(@Param("lowest") BigDecimal lowest,
                                             @Param("highest") BigDecimal highest,
                                             @Param("wood") String wood);

    @Query("select m " +
            "from Model m " +
            "where m.price >= :lowest " +
            "and m.price <= :highest " +
            "and m.woodType " +
            "like :wood")
    Page<Model> queryByPriceRangeAndWoodTypePageable(@Param("lowest") BigDecimal lowest,
                                                     @Param("highest") BigDecimal highest,
                                                     @Param("wood") String wood,
                                                     Pageable page);

    List<Model> namedFindAllModelsByType(@Param("name") String type);
}
