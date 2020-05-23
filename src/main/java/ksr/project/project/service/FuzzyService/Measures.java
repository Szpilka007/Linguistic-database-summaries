package ksr.project.project.service.FuzzyService;

import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.service.FuzzyService.Labels.Label;
import org.springframework.stereotype.Service;

import java.util.List;


public class Measures {




    //T6 -  stopień nieprecyzyjności kwantyfikatora
    public double getDegreeOfQuantifierImprecision(Label quantifier, List<HouseEntity> houseEntityList){
        double result = quantifier.membershipFunction.getSupport();

        if (quantifier.isAbsolute()) {
            result /= (double) houseEntityList.size();
        }
        return 1 - result;
    }

    // T7 - stopień liczności kwantyfikatora
    public double getDegreeOfQuantifierCardinality(Label quantifier, List<HouseEntity> houseEntityList) {
        double result = quantifier.getCardinality();

        if (quantifier.isAbsolute()) {
            result /= (double) houseEntityList.size();
        }
        return 1 - result;
    }

    // T9  - stopień nieprecyzyjności kwalifikatora
    public double getDegreeOfQualifierImprecision(Label qualifier, List<HouseEntity> houseEntities) {
        return 1 - qualifier.getDegreeOfFuzziness(houseEntities);
    }

    // T10 -  stopień liczności kwalifikatora
    public double getDegreeOfQualiifierCardinality(Label qualifier, List<HouseEntity> houseEntityList) {
        return 1 - (qualifier.getCardinality() / houseEntityList.size());
    }
}
