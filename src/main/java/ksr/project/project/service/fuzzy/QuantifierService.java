package ksr.project.project.service.fuzzy;

import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.repository.QuantifierRepository;
import ksr.project.project.service.memberships.MembershipFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantifierService {

    private final QuantifierRepository quantifierRepository;

    @Autowired
    public QuantifierService(QuantifierRepository quantifierRepository) {
        this.quantifierRepository = quantifierRepository;
    }

    public Quantifier addQuantifier(Quantifier quantifier) {
        return quantifierRepository.save(quantifier);
    }

    public List<Quantifier> getAllQuantifiers() {
        return quantifierRepository.findAll();
    }

    public MembershipFunction getMembershipFunction(Quantifier quantifier){
        return quantifier.getMembershipFunType().getMembershipFunction(
                quantifier.getPointA(),
                quantifier.getPointB(),
                quantifier.getPointC(),
                quantifier.getPointD()
        );
    }
}
