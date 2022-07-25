package com.data.enrichment.repository;

import com.data.enrichment.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrichmentRepository extends JpaRepository<Country,Integer> {

    @Query(value = "SELECT * FROM COUNTRY WHERE CITY_NAME=?1",nativeQuery = true)
    List<Country> findByCityName(String cityName);

}
