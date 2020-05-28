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
@Table(name = "HouseSummary")
public class HouseSummaryEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "HOUSE_ID")
    Long houseId;

    @Column(name = "PRICE_MEMBERSHIP")
    double priceMembership;

    @Column(name = "PRICE_DESCRIPTION")
    String priceDescription;

    @Column(name = "BEDROOM_MEMBERSHIP")
    double bedroomsMembership;

    @Column(name = "BEDROOM_DESCRIPTION")
    String bedroomDescription;

    @Column(name = "BATHROOMS_MEMBERSHIP")
    double bathroomMembership;

    @Column(name = "BATHROOMS_DESCRIPTION")
    String bathroomDescription;

    @Column(name = "RESIDENT_AREA_MEMBERSHIP")
    double residentAreaMembership;

    @Column(name = "RESIDENT_AREA_DESCRIPTION")
    String residentAreaDescription;

    @Column(name = "FLOOR_MEMBERSHIP")
    double floorMembership;

    @Column(name = "FLOOR_DESCRIPTION")
    String floorDescription;

    @Column(name = "VIEW_MEMBERSHIP")
    double viewMembership;

    @Column(name = "VIEW_DESCRIPTION")
    String viewDescription;

    @Column(name = "STATE_MEMBERSHIP")
    double stateMembership;

    @Column(name = "STATE_DESCRIPTION")
    String stateDescription;

    @Column(name = "PLOT_AREA_MEMBERSHIP")
    double plotAreaMembership;

    @Column(name = "PLOT_AREA_DESCRIPTION")
    String plotAreaDescription;

    @Column(name = "ATTIC_AREA_MEMBERSHIP")
    double atticAreaMembership;

    @Column(name = "ATTIC_AREA_DESCRIPTION")
    String atticAreaDescription;

    @Column(name = "BASEMENT_AREA_MEMBERSHIP")
    double basementAreaMembership;

    @Column(name = "BASEMENT_AREA_DESCRIPTION")
    String basementAreaDescription;

    @Column(name = "BUILT_MEMBERSHIP")
    double builtMembership;

    @Column(name = "BUILT_DESCRIPTION")
    String builtDescription;

    @Column(name = "RENOVATION_MEMBERSHIP")
    double renovationMembership;

    @Column(name = "RENOVATION_DESCRIPTION")
    String renovationDescription;


}
