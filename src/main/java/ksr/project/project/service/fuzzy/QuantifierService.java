package ksr.project.project.service.fuzzy;

import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.repository.QuantifierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantifierService {

    private final QuantifierRepository quantifierRepository;

    @Autowired
    public QuantifierService(QuantifierRepository quantifierRepository) {
        this.quantifierRepository = quantifierRepository;
    }

    public Quantifier addQuantifier(Quantifier quantifier){
        return quantifierRepository.save(quantifier);
    }
}
