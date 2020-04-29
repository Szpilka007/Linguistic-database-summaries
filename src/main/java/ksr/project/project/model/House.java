package ksr.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class House {

    Long price;
    int bedrooms;
    Double sqft_living;
    Double sqft_loft;
    int floors;
    int conditions;
    int GRADE;
    Double sqft_above;
    Double sqft_basement;
    Double yr_built;
    Double sqft_living15;
    Double sqft_lot15;

}
