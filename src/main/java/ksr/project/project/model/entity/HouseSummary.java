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
public class HouseSummary {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "HOUSE_ID")
    Long houseId;

    @Column(name = "ATTRIBUTES_SUMMARY")
    String attributeSummaryList;

}
