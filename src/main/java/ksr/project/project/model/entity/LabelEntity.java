package ksr.project.project.model.entity;

import ksr.project.project.model.enums.Attributes;
import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Labels")
public class LabelEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(name = "QUANTIFIER_NAME")
    String quantifierName;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "ABSOLUTE")
    boolean absolute;

    @Column(name = "ATTRIBUTE")
    Attributes attribute;

    @Column(name = "MEMBERSHIP_FUNCTION_TYPE")
    MembershipFunType membershipFunType;

    @Column(name = "FUNCTION_POINT_A")
    double functionPointA;

    @Column(name = "FUNCTION_POINT_B")
    double functionPointB;

    @Column(name = "FUNCTION_POINT_C")
    double functionPointC;

    @Column(name = "FUNCTION_POINT_D")
    double functionPointD;

}
