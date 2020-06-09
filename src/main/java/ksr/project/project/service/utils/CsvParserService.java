package ksr.project.project.service.utils;

import ksr.project.project.model.entity.House;
import ksr.project.project.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvParserService {

    private final HouseRepository housesRepository;

    @Autowired
    public CsvParserService(HouseRepository houseRepository) {
        this.housesRepository = houseRepository;
    }

    @Transactional
    public String readCsvFile() {
        String csvFile = "src/main/resources/houses.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] houses = line.split(cvsSplitBy);
                this.housesRepository.save(
                        House.builder()
                                .price(Double.valueOf(houses[2]))
                                .bedrooms(Double.valueOf(houses[3]))
                                .bathrooms(Double.parseDouble(houses[4]))
                                .residentialArea(Double.valueOf(houses[5]))
                                .atticArea(Double.valueOf(houses[6]))
                                .floors(Double.valueOf(houses[7]))
                                .view(Double.valueOf(houses[9]))
                                .state(Double.valueOf(houses[10]))
                                .gardenArea(Double.valueOf(houses[12]))
                                .basementArea(Double.valueOf(houses[13]))
                                .buildingAge(Double.valueOf(houses[14]))
                                .renovationAge(Double.valueOf(houses[15]))
                                .build());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getStackTrace().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getStackTrace().toString();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getStackTrace().toString();
                }
            }
        }
        return "Data created.";
    }
}
