package ksr.project.project.service.FuzzyService.Labels;

import ksr.project.project.model.entity.LabelEntity;
import ksr.project.project.model.enums.Attributes;
import ksr.project.project.repository.LabelRepository;
import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;
import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunction;
import ksr.project.project.service.FuzzyService.MembershipFunctions.TrapezoidalFunction;
import ksr.project.project.service.FuzzyService.MembershipFunctions.TriangularFunction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {

    private final LabelRepository labelRepository;

    @Autowired
    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public void addLabel(String quantifierName, String description,
                         boolean absolute, Attributes attribute, MembershipFunType membershipFunType,
                         double A, double B, double C, double D ) {

        labelRepository.save(LabelEntity.builder().quantifierName(quantifierName)
                .description(description)
                .absolute(absolute)
                .attribute(attribute)
                .membershipFunType(membershipFunType)
                .functionPointA(A)
                .functionPointB(B)
                .functionPointC(C)
                .functionPointD(D).build());
    }

    public List<Label> getAllLabels(){
        ArrayList<Label> labels = new ArrayList<>();

        List<LabelEntity> labelsFromDatabase = labelRepository.findAll();

        labelsFromDatabase.forEach(labelEntity -> {

            MembershipFunction membershipFunction;

            if(labelEntity.getMembershipFunType() == MembershipFunType.TRAPEZOIDAL)
                membershipFunction = new TrapezoidalFunction(labelEntity.getFunctionPointA(), labelEntity.getFunctionPointB(),
                        labelEntity.getFunctionPointC(), labelEntity.getFunctionPointD());
            else
                membershipFunction = new TriangularFunction(labelEntity.getFunctionPointA(),
                        labelEntity.getFunctionPointB(), labelEntity.getFunctionPointC());

            labels.add(Label.builder()
                    .description(labelEntity.getDescription())
                    .absolute(labelEntity.isAbsolute())
                    .quantifierName(labelEntity.getQuantifierName())
                    .attribute(labelEntity.getAttribute())
                    .membershipFunction(membershipFunction).build());
        });

        return labels;
    }

}
