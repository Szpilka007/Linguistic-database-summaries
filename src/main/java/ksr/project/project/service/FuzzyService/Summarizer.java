package ksr.project.project.service.FuzzyService;

import ksr.project.project.model.AttributeSummary;
import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.model.entity.HouseSummaryEntity;
import ksr.project.project.model.enums.Attributes;
import ksr.project.project.service.FuzzyService.Labels.Label;
import ksr.project.project.service.FuzzyService.Labels.LabelService;
import ksr.project.project.service.HouseService;
import ksr.project.project.service.HouseSummariesService;
import org.springframework.beans.factory.annotation.Autowired;
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

            houseSummariesService.addHouseSummaries(HouseSummaryEntity.builder()
                    .houseId(houseEntity.getId())
                    .priceDescription(attributeSummaryList
                            .stream()
                            .filter(attributeSummary ->
                                    attributeSummary.getAttributes().name().equals(Attributes.price.name()))
                            .findFirst().get().getDescription())
                    .priceMembership(attributeSummaryList
                            .stream()
                            .filter(attributeSummary ->
                                    attributeSummary.getAttributes().name().equals(Attributes.price.name()))
                            .findFirst().get().getMembership())
//                    .bedroomDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.bedroom.name()))
//                            .findFirst().get().getDescription())
//                    .bedroomsMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.bedroom.name()))
//                            .findFirst().get().getMembership())
//                    .bathroomDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.bathroom.name()))
//                            .findFirst().get().getDescription())
//                    .bathroomMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.bathroom.name()))
//                            .findFirst().get().getMembership())
//                    .residentAreaDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.residentArea.name()))
//                            .findFirst().get().getDescription())
//                    .residentAreaMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.residentArea.name()))
//                            .findFirst().get().getMembership())
//                    .viewDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.view.name()))
//                            .findFirst().get().getDescription())
//                    .viewMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.view.name()))
//                            .findFirst().get().getMembership())
//                    .floorDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.floors.name()))
//                            .findFirst().get().getDescription())
//                    .floorMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.floors.name()))
//                            .findFirst().get().getMembership())
//                    .viewDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.view.name()))
//                            .findFirst().get().getDescription())
//                    .viewMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.view.name()))
//                            .findFirst().get().getMembership())
//                    .stateDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.state.name()))
//                            .findFirst().get().getDescription())
//                    .stateMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.state.name()))
//                            .findFirst().get().getMembership())
//                    .plotAreaDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.plotArea.name()))
//                            .findFirst().get().getDescription())
//                    .plotAreaMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.plotArea.name()))
//                            .findFirst().get().getMembership())
//                    .atticAreaDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.atticArea.name()))
//                            .findFirst().get().getDescription())
//                    .atticAreaMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.atticArea.name()))
//                            .findFirst().get().getMembership())
//                    .basementAreaDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.basementArea.name()))
//                            .findFirst().get().getDescription())
//                    .basementAreaMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.basementArea.name()))
//                            .findFirst().get().getMembership())
//                    .builtDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.built.name()))
//                            .findFirst().get().getDescription())
//                    .builtMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.built.name()))
//                            .findFirst().get().getMembership())
//                    .renovationDescription(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.renovation.name()))
//                            .findFirst().get().getDescription())
//                    .renovationMembership(attributeSummaryList
//                            .stream()
//                            .filter(attributeSummary ->
//                                    attributeSummary.getAttributes().name().equals(Attributes.renovation.name()))
//                            .findFirst().get().getMembership())
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
