package ksr.project.project.model;

import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Qualifier;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.model.enums.Conjunction;
import ksr.project.project.model.enums.SummaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Summary {

    private List<AttributeSummary> attributeSummary; // only one element for single summaries or more for multi
    private Quantifier quantifier;
    private Qualifier qualifier;
    private SummaryType summaryType;
    private Conjunction conjunction;

}
