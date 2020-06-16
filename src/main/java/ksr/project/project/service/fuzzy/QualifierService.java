package ksr.project.project.service.fuzzy;

import ksr.project.project.model.Qualifier;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.service.memberships.MembershipFunction;
import org.springframework.stereotype.Service;

@Service
public class QualifierService {

    public MembershipFunction getMembershipFunction(Qualifier qualifier){
        return qualifier.getAttributeSummary().getMembershipFunType().getMembershipFunction(
                qualifier.getAttributeSummary().getPointA(),
                qualifier.getAttributeSummary().getPointB(),
                qualifier.getAttributeSummary().getPointC(),
                qualifier.getAttributeSummary().getPointD()
        );
    }


}
