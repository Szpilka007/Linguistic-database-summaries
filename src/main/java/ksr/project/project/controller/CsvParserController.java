package ksr.project.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ksr.project.project.service.CsvParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Csv File Controller")
@RequestMapping("/csv")
public class CsvParserController {

    private final CsvParserService csvParserService;

    @Autowired
    public CsvParserController(CsvParserService csvParserService) {
        this.csvParserService = csvParserService;
    }

    @GetMapping("/readFile")
    @ApiOperation(value = "Read data from csv file")
    public ResponseEntity<String> readCsvFile(){
        return ResponseEntity.status(HttpStatus.OK).body(csvParserService.readCsvFile());
    }

}
