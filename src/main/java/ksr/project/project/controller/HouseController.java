package ksr.project.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "House Controller")
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get house with id")
    public ResponseEntity<HouseEntity> getHouse(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.houseService.getHouse(id));
    }

    @GetMapping("/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all houses")
    public ResponseEntity<List<HouseEntity>> getAllHouse(){
        return ResponseEntity.status(HttpStatus.OK).body(this.houseService.getAllHouses());
    }

    @GetMapping("/count")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Count all records in house table")
    public ResponseEntity<Long> amountOfHouses(){
        return ResponseEntity.status(HttpStatus.OK).body(this.houseService.countHouses());
    }

    @GetMapping("/removeAll")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Remove all data from house table")
    public ResponseEntity<String> removeAllHouses(){
        this.houseService.removeAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
