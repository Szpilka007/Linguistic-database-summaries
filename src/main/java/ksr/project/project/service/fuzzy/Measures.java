package ksr.project.project.service.fuzzy;

import ksr.project.project.model.Summary;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.House;
import ksr.project.project.model.enums.Attribute;
import ksr.project.project.model.enums.Conjunction;
import ksr.project.project.model.enums.SummaryType;
import ksr.project.project.service.memberships.MembershipFunction;
import ksr.project.project.service.utils.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class Measures {

    @Autowired
    private HouseService houseService;

    @Autowired
    private QualifierService qualifierService;

    @Autowired
    private QuantifierService quantifierService;

    @Autowired
    private AttributeSummaryService attributeSummaryService;

    // T1
    public Double getDegreeOfTruth(Summary summary) {
        // uQ
        MembershipFunction quantifierFun = quantifierService.getMembershipFunction(summary.getQuantifier());

        // uW
        MembershipFunction qualifierFun = null;
        if (summary.getQualifier() != null) {
            qualifierFun = qualifierService.getMembershipFunction(summary.getQualifier());
        }

        // uS1
        MembershipFunction summaryFunOne = attributeSummaryService.getMembershipFunction(
                summary.getAttributeSummary().get(0)
        );

        // uS2
        MembershipFunction summaryFunTwo = null;
        if (summary.getSummaryType().equals(SummaryType.MULTI_SUBJECT) && summary.getAttributeSummary().size() > 1) {
            summaryFunTwo = attributeSummaryService.getMembershipFunction(summary.getAttributeSummary().get(1));
        }

        // SINGLE SUMMARY (FIRST)
        if (summary.getSummaryType().equals(SummaryType.SINGLE_SUBJECT_FIRST)) {
            double r = 0.0;
            Attribute attributeSingle = summary.getAttributeSummary().get(0).getAttribute();
            for (House house : houseService.getAllHouses()) {
                r += summaryFunOne.getMembership(houseService.getAttributeHouseValue(house, attributeSingle));
            }
            if (summary.getQuantifier().isAbsolute()) {
                return quantifierFun.getMembership(r);
            } else {
                return quantifierFun.getMembership(r / houseService.countHouses());
            }
        }

        // SINGLE SUMMARY (SECOND)
        if (summary.getSummaryType().equals(SummaryType.SINGLE_SUBJECT_SECOND)) {
            double rCounter = 0.0;
            double rDenominator = Double.MIN_NORMAL;
            Attribute attributeSingle = summary.getAttributeSummary().get(0).getAttribute();
            Attribute qualifierAttribute = attributeSummaryService.getAttributeSummary(summary.getQualifier().getId()).get().getAttribute();
            for (House house : houseService.getAllHouses()) {
                double uS1 = summaryFunOne.getMembership(houseService.getAttributeHouseValue(house, attributeSingle));
                double uW = qualifierFun.getMembership(houseService.getAttributeHouseValue(house, qualifierAttribute));
                rCounter += Math.min(uS1, uW);
                rDenominator += uW;
            }
            double r = rCounter / rDenominator;
            if (summary.getQuantifier().isAbsolute()) {
                return quantifierFun.getMembership(r);
            } else {
                return quantifierFun.getMembership(r / houseService.countHouses());
            }
        }

        // MULTI SUMMARY
        if (summary.getSummaryType().equals(SummaryType.MULTI_SUBJECT)) {
            Attribute attributeOne = summary.getAttributeSummary().get(0).getAttribute();
            Attribute attributeTwo = summary.getAttributeSummary().get(1).getAttribute();
            double r = 0.0;
            for (House house : houseService.getAllHouses()) {
                double uS1 = summaryFunOne.getMembership(houseService.getAttributeHouseValue(house, attributeOne));
                double uS2 = summaryFunTwo.getMembership(houseService.getAttributeHouseValue(house, attributeTwo));
                if (summary.getConjunction().equals(Conjunction.AND)) {
                    r += Math.min(uS1, uS2);
                }
                if (summary.getConjunction().equals(Conjunction.OR)) {
                    r += Math.max(uS1, uS2);
                }
            }
            if (summary.getQuantifier().isAbsolute()) {
                return quantifierFun.getMembership(r);
            } else {
                return quantifierFun.getMembership(r / houseService.countHouses());
            }
        }
        return null;
    }

    // T2
    public double getDegreeOfImprecision(Summary summary) {
        if (summary.getAttributeSummary().size() == 1) {
            return 1 - attributeSummaryService.getDegreeOfFuzzines(summary.getAttributeSummary().get(0));
        } else {
            double product = summary.getAttributeSummary().stream()
                    .map(i -> attributeSummaryService.getDegreeOfFuzzines(i)).reduce((a, b) -> a * b).get();
            return 1 - Math.pow(product, summary.getAttributeSummary().size() * (-1.0));
        }
    }

    // T3
    public Double getDegreeOfCovering(Summary summary) {
        // uW
        MembershipFunction qualifierFun = null;
        if (summary.getQualifier() != null) {
            qualifierFun = qualifierService.getMembershipFunction(summary.getQualifier());
        }

        // uS1
        MembershipFunction summaryFunOne = attributeSummaryService.getMembershipFunction(
                summary.getAttributeSummary().get(0)
        );

        // uS2
        MembershipFunction summaryFunTwo = null;
        if (summary.getSummaryType().equals(SummaryType.MULTI_SUBJECT) && summary.getAttributeSummary().size() > 1) {
            summaryFunTwo = attributeSummaryService.getMembershipFunction(summary.getAttributeSummary().get(1));
        }

        if (summary.getSummaryType().equals(SummaryType.SINGLE_SUBJECT_SECOND) && summary.getQualifier() != null) {
            double tSum = 0.0;
            double hSum = 0.0;
            Attribute attrQualifier = attributeSummaryService.getAttributeSummary(summary.getQualifier().getId()).get().getAttribute();
            Attribute attrSummary = summary.getAttributeSummary().get(0).getAttribute();
            for (House house : houseService.getAllHouses()) {
                if (qualifierFun.getMembership(houseService.getAttributeHouseValue(house, attrQualifier)) > 0
                        && summaryFunOne.getMembership(houseService.getAttributeHouseValue(house, attrSummary)) > 0) {
                    tSum += 1.0;
                }
                if (qualifierFun.getMembership(houseService.getAttributeHouseValue(house, attrQualifier)) > 0) {
                    hSum += 1.0;
                }
            }
            return tSum / hSum;
        }

        if (!summary.getSummaryType().equals(SummaryType.SINGLE_SUBJECT_SECOND)) {
            double tSum = 0.0;
            double hSum = 0.0;
            Attribute attrSummaryOne = summary.getAttributeSummary().get(0).getAttribute();
            Attribute attrSummaryTwo = null;
            if (summary.getSummaryType().equals(SummaryType.MULTI_SUBJECT)) {
                attrSummaryTwo = summary.getAttributeSummary().get(1).getAttribute();
            }

            for (House house : houseService.getAllHouses()) {
                hSum += 1.0;
                if (summaryFunOne.getMembership(houseService.getAttributeHouseValue(house, attrSummaryOne)) > 0
                        || summaryFunTwo.getMembership(houseService.getAttributeHouseValue(house, attrSummaryTwo)) > 0) {
                    tSum += 1.0;
                }
            }
            return tSum / hSum;
        }

        return null;
    }

    // T4
    public Double getDegreeOfAppropriateness(Summary summary) {
        double t3 = getDegreeOfCovering(summary);

        List<Double> rList = new ArrayList<>();

        for (AttributeSummary attributeSummary : summary.getAttributeSummary()) {
            double gSum = 0.0;
            for (House house : houseService.getAllHouses()) {
                double attValue = houseService.getAttributeHouseValue(house, attributeSummary.getAttribute());
                if (attributeSummaryService.getMembershipFunction(attributeSummary).getMembership(attValue) > 0) {
                    gSum += 1.0;
                }
            }
            rList.add(gSum / houseService.countHouses());
        }
        return Math.abs(rList.stream().reduce((a, b) -> a * b).get() - t3);
    }

    // T5
    public Double getLengthOfSummary(Summary summary) {
        return 2 * Math.pow(0.5, summary.getAttributeSummary().size());
    }

    // The optimal summary
    public Double getOptimalSummary(Summary summary) {
        // Sum of weights must be equal 1
        Double[] weights = {0.45, 0.25, 0.15, 0.10, 0.05};
        Double optimal = weights[0] * getDegreeOfTruth(summary) +
                weights[1] * getDegreeOfImprecision(summary) +
                weights[2] * getDegreeOfCovering(summary) +
                weights[3] * getDegreeOfAppropriateness(summary) +
                weights[4] * getLengthOfSummary(summary);
        return optimal;
    }

    // T6
    public double getDegreeOfQuantifierImprecision(Summary summary) {
        double support =
                Objects.requireNonNull(summary.getQuantifier().getMembershipFunType().getMembershipFunction(
                        summary.getQuantifier().getPointA(),
                        summary.getQuantifier().getPointB(),
                        summary.getQuantifier().getPointC(),
                        summary.getQuantifier().getPointD()
                )).getSupport();
        double quantifierUniverse = summary.getQuantifier().isAbsolute() ? (double) houseService.countHouses() : 1.0;
        return 1 - (support / quantifierUniverse);
    }

    // T7
    public Double getDegreeOfQuantifierCardinality(Summary summary) {
        double clmQ = quantifierService.getMembershipFunction(summary.getQuantifier()).getCardinality();
        if (summary.getQuantifier().isAbsolute()) {
            return 1 - (clmQ / houseService.countHouses());
        } else {
            return 1 - (clmQ / 1.0);
        }
    }

    // T8
    public Double getDegreeOfSummarizerCardinality(Summary summary) {
        Double cardS1 = attributeSummaryService.getMembershipFunction(summary.getAttributeSummary().get(0)).getCardinality();
        Double cardS2 = null;
        if (summary.getSummaryType().equals(SummaryType.MULTI_SUBJECT)) {
            cardS2 = attributeSummaryService.getMembershipFunction(summary.getAttributeSummary().get(1)).getCardinality();
        }
        if (summary.getSummaryType().equals(SummaryType.MULTI_SUBJECT)) {
            return 1.0 - Math.pow((cardS1 / houseService.countHouses()) * (cardS2 / houseService.countHouses()), 0.5);
        }
        return 1.0 - (cardS1 / houseService.countHouses());
    }

    // T9
    public Double getDegreeOfQualifierImprecision(Summary summary) {
        if (!summary.getSummaryType().equals(SummaryType.SINGLE_SUBJECT_SECOND)) {
            return 1.0;
        }

        // uW
        MembershipFunction qualifierFun = qualifierService.getMembershipFunction(summary.getQualifier());

        Double inW1 = qualifierFun.getSupport() / houseService.countHouses();
        return 1.0 - inW1;
    }

    // T10
    public Double getDegreeOfQualifierCardinality(Summary summary) {
        if (!summary.getSummaryType().equals(SummaryType.SINGLE_SUBJECT_SECOND)) {
            return 1.0;
        }

        // uW
        MembershipFunction qualifierFun = qualifierService.getMembershipFunction(summary.getQualifier());

        return 1.0 - (qualifierFun.getCardinality() / houseService.countHouses());
    }

    // T11
    public Double getLengthOfQualifier(Summary summary) {
        return 2 * Math.pow(0.5, 1); // always in app we have only one qualifier (multi only for S)
    }

    // Extended optimal summary
    public Double getExtendedOptimalSummary(Summary summary) {
        Double oneWeightForAll = getDegreeOfTruth(summary) +
                getDegreeOfImprecision(summary) +
                getDegreeOfCovering(summary) +
                getDegreeOfAppropriateness(summary) +
                getLengthOfSummary(summary) +
                getDegreeOfQuantifierImprecision(summary) +
                getDegreeOfQuantifierCardinality(summary) +
                getDegreeOfSummarizerCardinality(summary) +
                getDegreeOfQualifierImprecision(summary) +
                getDegreeOfQualifierCardinality(summary);
        return oneWeightForAll * 0.1; // without T11, because in app is always equal 1
    }

    public String allMeasuresToString(Summary summary) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("T1  = %5.5f", getDegreeOfTruth(summary))).append("\n");
        sb.append(String.format("T2  = %5.5f", getDegreeOfImprecision(summary))).append("\n");
        sb.append(String.format("T3  = %5.5f", getDegreeOfCovering(summary))).append("\n");
        sb.append(String.format("T4  = %5.5f", getDegreeOfAppropriateness(summary))).append("\n");
        sb.append(String.format("T5  = %5.5f", getLengthOfSummary(summary))).append("\n");
        sb.append(String.format("T6  = %5.5f", getDegreeOfQuantifierImprecision(summary))).append("\n");
        sb.append(String.format("T7  = %5.5f", getDegreeOfQuantifierCardinality(summary))).append("\n");
        sb.append(String.format("T8  = %5.5f", getDegreeOfSummarizerCardinality(summary))).append("\n");
        sb.append(String.format("T9  = %5.5f", getDegreeOfQualifierImprecision(summary))).append("\n");
        sb.append(String.format("T10 = %5.5f", getDegreeOfQualifierCardinality(summary))).append("\n");
        sb.append(String.format("T11 = %5.5f", getLengthOfQualifier(summary))).append("\n");
        sb.append("---").append("\n");
        sb.append(String.format("OPTIMAL (1 - 5)  = %5.5f", getOptimalSummary(summary))).append("\n");
        sb.append(String.format("OPTIMAL (1 - 10) = %5.5f", getExtendedOptimalSummary(summary))).append("\n");
        return sb.toString();
    }
}
