package ksr.project.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HouseSummaries")
public class HouseSummaries {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "PRICE")
    String price;

    @Column(name = "BEDROOMS")
    String bedrooms;

    @Column(name = "BATHROOMS")
    String bathrooms;

    @Column(name = "RESIDENTIAL_AREA")
    String residential_area;

    @Column(name = "FLOORS")
    String floors;

    @Column(name = "VIEW")
    String view;

    @Column(name = "STATE")
    String state;

    @Column(name = "PLOT_AREA")
    String plot_area;

    @Column(name = "ATTIC_AREA")
    String attic_area;

    @Column(name = "BASEMENT_AREA")
    String basement_area;

    @Column(name = "BUILT")
    String built;

    @Column(name = "RENOVATION")
    String renovation;

}
