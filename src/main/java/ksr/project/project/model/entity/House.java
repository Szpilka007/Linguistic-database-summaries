package ksr.project.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HOUSES")
public class House {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "PRICE")
    Double price;

    @Column(name = "BEDROOMS")
    Double bedrooms;

    @Column(name = "BATHROOMS")
    Double bathrooms;

    @Column(name = "RESIDENTIAL_AREA")
    Double residentialArea;

    @Column(name = "ATTIC_AREA")
    Double atticArea;

    @Column(name = "FLOORS")
    Double floors;

    @Column(name = "VIEW")
    Double view;

    @Column(name = "STATE")
    Double state;

    @Column(name = "GARDEN_AREA")
    Double gardenArea;

    @Column(name = "BASEMENT_AREA")
    Double basementArea;

    @Column(name = "BUILDING_AGE")
    Double buildingAge;

    @Column(name = "RENOVATION_AGE")
    Double renovationAge;

}
