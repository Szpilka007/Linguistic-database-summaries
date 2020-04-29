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
@Table(name = "Houses")
public class HouseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "PRICE")
    Long price;

    @Column(name = "BEDROOMS")
    Long bedrooms;

    @Column(name = "BATHROOMS")
    Double bathrooms;

    @Column(name = "SQFT_LIVING")
    Double sqft_living;

    @Column(name = "SQFT_LOFT")
    Double sqft_loft;

    @Column(name = "FLOORS")
    Double floors;

    @Column(name = "WATERFRONT")
    Double waterfront;

    @Column(name = "VIEW")
    Long view;

    @Column(name = "CONDITIONS")
    Long conditions;

    @Column(name = "GRADE")
    Long grade;

    @Column(name = "SQFT_ABOVE")
    Double sqft_above;

    @Column(name = "SQFT_BASEMENT")
    Double sqft_basement;

    @Column(name = "YR_BUILT")
    Double yr_built;

    @Column(name = "YR_RENOVATION")
    Double yr_renovation;

    @Column(name = "ZIPCODE")
    Double zipcode;

    @Column(name = "LAT")
    Double lat;

    @Column(name = "LONG")
    Double lon;

    @Column(name = "SQFT_LIVING15")
    Double sqft_living15;

    @Column(name = "SQFT_LOT15")
    Double sqft_lot15;

}
