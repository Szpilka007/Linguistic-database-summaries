package ksr.project.project.service.utils;

import ksr.project.project.model.entity.House;
import ksr.project.project.model.enums.Attribute;
import ksr.project.project.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public long countHouses() {
        return this.houseRepository.count();
    }

    public void removeAll() {
        this.houseRepository.deleteAll();
    }

    public List<House> getAllHouses() {
        return this.houseRepository.findAll();
    }

    public House getHouse(Long id) {
        return this.houseRepository.getOne(id);
    }

    public Double getAttributeHouseValue(House house, Attribute attribute) {
        switch (attribute) {
            case PRICE:
                return house.getPrice();
            case BEDROOM:
                return house.getBedrooms();
            case BATHROOM:
                return house.getBathrooms();
            case FLOOR:
                return house.getFloors();
            case STATE:
                return house.getState();
            case VIEW:
                return house.getView();
            case RESIDENTIAL_AREA:
                return house.getResidentialArea();
            case GARDEN_AREA:
                return house.getGardenArea();
            case ATTIC_AREA:
                return house.getAtticArea();
            case BASEMENT_AREA:
                return house.getBasementArea();
            case BUILDING_AGE:
                return house.getBuildingAge();
            case RENOVATION_AGE:
                return house.getRenovationAge();
        }
        return null;
    }
}
