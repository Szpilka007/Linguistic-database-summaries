package ksr.project.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ksr.project.project.gui.models.tabs.SummaryFirstType;
import ksr.project.project.model.Summary;
import ksr.project.project.service.summary.SummarizerSingleFirst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "Summary Controller")
@RequestMapping("/summary")
public class SummaryController {

    private final SummarizerSingleFirst summarizerSingleFirst;

    @Autowired
    public SummaryController(SummarizerSingleFirst summarizerSingleFirst) {
        this.summarizerSingleFirst = summarizerSingleFirst;
    }

    @PostMapping("/generate-s1")
    @ApiOperation(value = "Generate single subject summary of the first type")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addAttributeSummary(@RequestBody Summary summary) {
        String response = summarizerSingleFirst.getLinguisticSummary(summary);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
