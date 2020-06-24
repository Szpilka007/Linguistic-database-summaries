package ksr.project.project.service.fuzzy;

import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Qualifier;
import ksr.project.project.repository.QualifierRepository;
import ksr.project.project.service.memberships.MembershipFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QualifierService {

    private final QualifierRepository qualifierRepository;
    private final AttributeSummaryService attributeSummaryService;

    @Autowired
    public QualifierService(QualifierRepository qualifierRepository, AttributeSummaryService attributeSummaryService) {
        this.qualifierRepository = qualifierRepository;
        this.attributeSummaryService = attributeSummaryService;
    }

    public Qualifier addQualifier(Qualifier qualifier){
        return this.qualifierRepository.save(qualifier);
    }

    public MembershipFunction getMembershipFunction(Qualifier qualifier){
        AttributeSummary attributeSummary = attributeSummaryService.getAttributeSummary(qualifier.getId()).get();
        return attributeSummary.getMembershipFunType().getMembershipFunction(
                attributeSummary.getPointA(),
                attributeSummary.getPointB(),
                attributeSummary.getPointC(),
                attributeSummary.getPointD()
        );
    }




}
