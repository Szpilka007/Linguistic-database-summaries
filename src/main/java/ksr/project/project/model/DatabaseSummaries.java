package ksr.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseSummaries {

    String price;
    String bedrooms;
    String bathrooms;
    String residential_area;
    String floors;
    String view;
    String state;
    String plot_area;
    String attic_area;
    String basement_area;
    String built;
    String renovation;
}
