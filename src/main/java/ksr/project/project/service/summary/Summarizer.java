package ksr.project.project.service.summary;

import ksr.project.project.model.Qualifier;
import ksr.project.project.model.Summary;
import ksr.project.project.model.entity.*;
import ksr.project.project.model.enums.SummaryType;

import java.util.List;

public interface Summarizer {

    String getLinguisticSummary(Summary summary);
    Summary generateSummary(SummaryType summaryType, List<AttributeSummary> attributeSummary,
                            Quantifier quantifier, Qualifier qualifier);


}
