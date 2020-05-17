package ksr.project.project.service.FuzzyService;

import ksr.project.project.model.DatabaseSummaries;
import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.model.entity.HouseSummaries;
import ksr.project.project.service.FuzzyService.Labels.AtticArea.AtticArea;
import ksr.project.project.service.FuzzyService.Labels.BasementArea.BasementArea;
import ksr.project.project.service.FuzzyService.Labels.Bathroom.Bathroom;
import ksr.project.project.service.FuzzyService.Labels.Bedroom.Bedroom;
import ksr.project.project.service.FuzzyService.Labels.Built.Built;
import ksr.project.project.service.FuzzyService.Labels.Floors.Floors;
import ksr.project.project.service.FuzzyService.Labels.PlotArea.PlotArea;
import ksr.project.project.service.FuzzyService.Labels.Price.Price;
import ksr.project.project.service.FuzzyService.Labels.Renovation.Renovation;
import ksr.project.project.service.FuzzyService.Labels.ResidentialArea.ResidentialArea;
import ksr.project.project.service.FuzzyService.Labels.State.State;
import ksr.project.project.service.FuzzyService.Labels.View.View;
import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunType;
import ksr.project.project.service.HouseService;
import ksr.project.project.service.HouseSummariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class Summarizer {

    private final HouseService houseService;
    private final HouseSummariesService houseSummariesService;
    private final List<AtticArea> atticAreas;
    private final List<BasementArea> basementAreas;
    private final List<Bathroom> bathrooms;
    private final List<Bedroom> bedrooms;
    private final List<Built> builts;
    private final List<Floors> floors;
    private final List<PlotArea> plotAreas;
    private final List<Price> prices;
    private final List<Renovation> renovations;
    private final List<ResidentialArea> residentialAreas;
    private final List<State> states;
    private final List<View> views;

    @Autowired
    public Summarizer(HouseService houseService, HouseSummariesService houseSummariesService, List<AtticArea> atticAreas, List<BasementArea> basementAreas, List<Bathroom> bathrooms, List<Bedroom> bedrooms, List<Built> builts, List<Floors> floors, List<PlotArea> plotAreas, List<Price> prices, List<Renovation> renovations, List<ResidentialArea> residentialAreas, List<State> states, List<View> views) {
        this.houseService = houseService;
        this.houseSummariesService = houseSummariesService;
        this.atticAreas = atticAreas;
        this.basementAreas = basementAreas;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.builts = builts;
        this.floors = floors;
        this.plotAreas = plotAreas;
        this.prices = prices;
        this.renovations = renovations;
        this.residentialAreas = residentialAreas;
        this.states = states;
        this.views = views;
    }

    public void generateSummaries(MembershipFunType membershipFunType) {

        List<HouseEntity> houseEntities = houseService.getAllHouses();

        houseEntities.stream().forEach(houseEntity -> {
            this.houseSummariesService.addHouseSummaries(HouseSummaries.builder()
                    .attic_area(atticAreas.stream()
                            .max(Comparator.comparing(atticArea ->
                                    atticArea.getMembership(membershipFunType, houseEntity.getSqft_above())))
                            .get().getDescription())
                    .basement_area(basementAreas.stream()
                            .max(Comparator.comparing(basementArea ->
                                    basementArea.getMembership(membershipFunType, houseEntity.getSqft_basement())))
                            .get().getDescription())
                    .bathrooms(bathrooms.stream()
                            .max(Comparator.comparing(bathroom ->
                                    bathroom.getMembership(membershipFunType, houseEntity.getBathrooms())))
                            .get().getDescription())
                    .bedrooms(bedrooms.stream()
                            .max(Comparator.comparing(bedroom ->
                                    bedroom.getMembership(membershipFunType, houseEntity.getBedrooms())))
                            .get().getDescription())
                    .built(builts.stream()
                            .max(Comparator.comparing(built ->
                                    built.getMembership(membershipFunType, houseEntity.getYr_built())))
                            .get().getDescription())
                    .floors(floors.stream()
                            .max(Comparator.comparing(floor ->
                                    floor.getMembership(membershipFunType, houseEntity.getFloors())))
                            .get().getDescription())
                    .plot_area(plotAreas.stream()
                            .max(Comparator.comparing(plotArea ->
                                    plotArea.getMembership(membershipFunType, houseEntity.getSqft_living())))
                            .get().getDescription())
                    .price(prices.stream()
                            .max(Comparator.comparing(price ->
                                    price.getMembership(membershipFunType, houseEntity.getPrice())))
                            .get().getDescription())
                    .renovation(renovations.stream()
                            .max(Comparator.comparing(renovation ->
                                    renovation.getMembership(membershipFunType, houseEntity.getYr_renovation())))
                            .get().getDescription())
                    .residential_area(residentialAreas.stream()
                            .max(Comparator.comparing(residentialArea ->
                                    residentialArea.getMembership(membershipFunType, houseEntity.getSqft_loft())))
                            .get().getDescription())
                    .state(states.stream()
                            .max(Comparator.comparing(state ->
                                    state.getMembership(membershipFunType, houseEntity.getConditions())))
                            .get().getDescription())
                    .view(views.stream()
                            .max(Comparator.comparing(view->
                                    view.getMembership(membershipFunType, houseEntity.getView())))
                            .get().getDescription())
                    .build());
        });
    }

    public DatabaseSummaries generateDatabaseSummaries(){
        List<HouseSummaries> houseSummaries = this.houseSummariesService.getAllHouseSummaries();
        return DatabaseSummaries.builder()
                .attic_area(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getAttic_area)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .basement_area(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBasement_area)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .bathrooms(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBathrooms)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .bedrooms(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBedrooms)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .built(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getBuilt)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .floors(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getFloors)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .plot_area(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getPlot_area)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .price(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getPrice)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .renovation(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getRenovation)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .residential_area(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getResidential_area)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .state(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getState)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .view(houseSummaries
                        .stream().map((Function<HouseSummaries, Object>) HouseSummaries::getView)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream().max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey).map(Object::toString).orElse(null))
                .build();

    }
}
