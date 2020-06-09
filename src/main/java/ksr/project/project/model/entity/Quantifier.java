package ksr.project.project.model.entity;

import ksr.project.project.model.enums.MembershipFunType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "QUANTIFIERS")
public class Quantifier {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_QUANTIFIER")
    Long id_quantifier;

    @Column(name = "IS_ABSOLUTE")
    private boolean absolute;

    @Column(name = "NAME")
    private String name;

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
