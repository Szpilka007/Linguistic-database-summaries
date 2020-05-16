package ksr.project.project.service;

import ksr.project.project.model.entity.HouseEntity;
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
                        HouseEntity.builder()
                                .price(Long.valueOf(houses[2]))
                                .bedrooms(Long.valueOf(houses[3]))
                                .bathrooms(Double.parseDouble(houses[4]))
                                .sqft_living(Double.valueOf(houses[5]))
                                .sqft_loft(Double.valueOf(houses[6]))
                                .floors(Double.valueOf(houses[7]))
                                .waterfront(Double.valueOf(houses[8]))
                                .view(Long.valueOf(houses[9]))
                                .conditions(Long.valueOf(houses[10]))
                                .grade(Long.valueOf(houses[11]))
                                .sqft_above(Double.valueOf(houses[12]))
                                .sqft_basement(Double.valueOf(houses[13]))
                                .yr_built(Double.valueOf(houses[14]))
                                .yr_renovation(Double.valueOf(houses[15]))
                                .zipcode(Double.valueOf(houses[16]))
                                .lat(Double.valueOf(houses[17]))
                                .lon(Double.valueOf(houses[18]))
                                .sqft_living15(Double.valueOf(houses[19]))
                                .sqft_lot15(Double.valueOf(houses[20]))
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
