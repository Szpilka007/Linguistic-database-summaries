package ksr.project.project.service.fuzzy;

import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.House;
import ksr.project.project.model.enums.Attribute;
import ksr.project.project.model.enums.MembershipFunType;
import ksr.project.project.repository.AttributeSummaryRepository;
import ksr.project.project.repository.HouseRepository;
import ksr.project.project.service.memberships.MembershipFunction;
import ksr.project.project.service.utils.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttributeSummaryService {

    private final HouseService houseService;
    private final AttributeSummaryRepository attributeSummaryRepository;

    @Autowired
    public AttributeSummaryService(AttributeSummaryRepository attributeSummaryRepository,
                                   HouseService houseService) {
        this.attributeSummaryRepository = attributeSummaryRepository;
        this.houseService = houseService;
    }

    @Transactional
    public void addAttributeSummary(Attribute attribute, String name, MembershipFunType membershipFunType,
                                    double a, double b, double c, double d) {
        attributeSummaryRepository.save(
                AttributeSummary.builder()
                        .attribute(attribute)
                        .name(name)
                        .membershipFunType(membershipFunType)
                        .pointA(a)
                        .pointB(b)
                        .pointC(c)
                        .pointD(d)
                        .build()
        );
    }

    @Transactional
    public AttributeSummary addAttributeSummary(AttributeSummary attributeSummary){
        return attributeSummaryRepository.save(attributeSummary);
    }

    public List<AttributeSummary> getAllAttributeSummary(){
        return attributeSummaryRepository.findAll();
    }

    public Optional<AttributeSummary> getAttributeSummary(Long id) {
        return attributeSummaryRepository.findById(id);
    }

    public AttributeSummary returnAttributeSummaryByName(String name){
        return attributeSummaryRepository.findAll()
                .stream()
                .filter(quantifier -> quantifier.getName().equals(name))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Quantifier not found"));
    }

    public List<House> getSupport(AttributeSummary attributeSummary) {

        MembershipFunction membershipFunction = attributeSummary.getMembershipFunType()
                .getMembershipFunction(
                        attributeSummary.getPointA(), attributeSummary.getPointB(),
                        attributeSummary.getPointC(), attributeSummary.getPointD()
                );

        List<House> support = new ArrayList<>();

        for (House house : houseService.getAllHouses()) {
            double attrValue = houseService.getAttributeHouseValue(house, attributeSummary.getAttribute());
            if (membershipFunction.getMembership(attrValue) > 0) {
                support.add(house);
            }
        }

        return support;
    }

    public MembershipFunction getMembershipFunction(AttributeSummary attributeSummary){
        return attributeSummary.getMembershipFunType().getMembershipFunction(
                attributeSummary.getPointA(),
                attributeSummary.getPointB(),
                attributeSummary.getPointC(),
                attributeSummary.getPointD()
        );
    }

    // in(S)
    public double getDegreeOfFuzzines(AttributeSummary attributeSummary){
        return getMembershipFunction(attributeSummary).getSupport() / houseService.countHouses();
    }

}
