package com.data.enrichment.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @Column(name = "ID")
    private Integer countryId;

    private String countryName;

    private String cityName;
}
