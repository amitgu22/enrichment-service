package com.data.enrichment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Population {

    public String city;
    public String population;
    public String country;



    @Override public String toString() {
        return "Population(" + city + ", " + population + "," + country + ")";
    }

}
