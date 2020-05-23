package ksr.project.project.service.FuzzyService;

import ksr.project.project.model.AttributeSummary;
import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.model.entity.HouseSummary;
import ksr.project.project.model.enums.Attributes;
import ksr.project.project.service.FuzzyService.Labels.Label;
import ksr.project.project.service.FuzzyService.Labels.LabelService;
import ksr.project.project.service.HouseService;
import ksr.project.project.service.HouseSummariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class Summarizer {

    private final HouseService houseService;
    private final HouseSummariesService houseSummariesService;
    private final LabelService labelService;

    @Autowired
    public Summarizer(HouseService houseService, HouseSummariesService houseSummariesService, LabelService labelService) {
        this.houseService = houseService;
        this.houseSummariesService = houseSummariesService;
        this.labelService = labelService;
    }

    public void generateSummaries() {

        List<HouseEntity> houseEntities = houseService.getAllHouses();
        List<Label> labels = labelService.getAllLabels();


        houseEntities.forEach(houseEntity -> {

            ArrayList<AttributeSummary> attributeSummaryList = new ArrayList<>();
            Stream.of(Attributes.values()).forEach(attribute -> {

                ArrayList<AttributeSummary> attributeSummaries = new ArrayList<>();
                labels.forEach(label -> {

                    if (attribute.name().equals(label.getAttribute().name())) {
                        try {
                            Field field = HouseEntity.class.getDeclaredField(attribute.name());
                            field.setAccessible(true);
                            attributeSummaries.add(AttributeSummary
                                    .builder()
                                    .attributes(attribute)
                                    .description(label.getDescription())
                                    .membership(label.getMembershipFunction()
                                            .getMembership(Double.parseDouble(field.get(houseEntity).toString()))).build());
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });

                attributeSummaries.stream()
                        .max(Comparator.comparing(AttributeSummary::getMembership))
                        .ifPresent(attributeSummaryList::add);
            });

            houseSummariesService.addHouseSummaries(HouseSummary.builder()
                    .houseId(houseEntity.getId())
                    .attributeSummaryList(attributeSummaryList.toString())
                    .build());
        });

}

//    public DatabaseSummaries generateDatabaseSummaries() {
//        List<HouseSummaries> houseSummaries = this.houseSummariesService.getAllHouseSummaries();
//        return DatabaseSummaries.builder()
//                .attic_area(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getAttic_area)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .basement_area(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBasement_area)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .bathrooms(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBathrooms)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .bedrooms(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBedrooms)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .built(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBuilt)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .floors(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getFloors)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .plot_area(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getPlot_area)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .price(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getPrice)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .renovation(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getRenovation)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .residential_area(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getResidential_area)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .state(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getState)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .view(houseSummaries
//                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getView)
//                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                        .entrySet().stream().max(Map.Entry.comparingByValue())
//                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
//                .build();
//
//    }
}
