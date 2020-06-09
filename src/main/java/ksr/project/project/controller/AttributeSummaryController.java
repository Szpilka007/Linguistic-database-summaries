package ksr.project.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.House;
import ksr.project.project.model.enums.Attribute;
import ksr.project.project.model.enums.MembershipFunType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import ksr.project.project.service.utils.CsvParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Attribute Summary Controller")
@RequestMapping("/attr-summary")
public class AttributeSummaryController {

    private final AttributeSummaryService attributeSummaryService;

    @Autowired
    public AttributeSummaryController(AttributeSummaryService attributeSummaryService) {
        this.attributeSummaryService = attributeSummaryService;
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add attributeSummary")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addAttributeSummary(@RequestBody AttributeSummary attributeSummary){

        attributeSummaryService.addAttributeSummary(attributeSummary);
        String response = "Added attribute summary!\n";
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all attribute summaries")
    public ResponseEntity<List<AttributeSummary>> getAllAttributeSummaries(){
        return ResponseEntity.status(HttpStatus.OK).body(this.attributeSummaryService.getAllAttributeSummary());
    }

}
