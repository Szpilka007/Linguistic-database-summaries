package ksr.project.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.service.fuzzy.QuantifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Quantifier Controller")
@RequestMapping("/quantifier")
public class QuantifierController {

    private final QuantifierService quantifierService;

    @Autowired
    public QuantifierController(QuantifierService quantifierService) {
        this.quantifierService = quantifierService;
    }

    @PostMapping("/add")
    @ApiOperation(value = "Add quantifier")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addAttributeSummary(@RequestBody Quantifier quantifier){

        quantifierService.addQuantifier(quantifier);
        String response = "Added quantifier!\n";
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all quantifiers")
    public ResponseEntity<List<Quantifier>> getAllQuantifiers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.quantifierService.getAllQuantifiers());
    }

}
