package ksr.project.project.model.entity;

import ksr.project.project.model.enums.Attribute;
import ksr.project.project.model.enums.MembershipFunType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ATTRIBUTE_SUMMARY")
public class AttributeSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATTRIBUTE_SUMMARY")
    Long id_attribute_summary;

    @Column(name = "ATTRIBUTE")
    @Enumerated(EnumType.STRING)
    private Attribute attribute; // in example price

    @Column(name = "NAME")
    private String name; // in example low or high

    @Column(name = "MEMBERSHIP_TYPE")
    @Enumerated(EnumType.STRING)
    private MembershipFunType membershipFunType;

    @Column(name = "POINT_A")
    private double pointA;

    @Column(name = "POINT_B")
    private double pointB;

    @Column(name = "POINT_C")
    private double pointC;

    @Column(name = "POINT_D")
    private double pointD;

}
