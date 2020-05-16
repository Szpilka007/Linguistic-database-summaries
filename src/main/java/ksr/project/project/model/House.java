package ksr.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class House {

    Long price;
    Long bedrooms;
    Double bathrooms;
    Double sqft_living;
    Double sqft_loft;
    Long floors;
    Long conditions;
    Long GRADE;
    Double view;
    Double sqft_above;
    Double sqft_basement;
    Double yr_built;
    Double yr_renovation;
    Double sqft_living15;
    Double sqft_lot15;

}
