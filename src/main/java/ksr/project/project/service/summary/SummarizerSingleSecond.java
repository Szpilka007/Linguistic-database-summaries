package ksr.project.project.service.summary;

import ksr.project.project.model.Summary;
import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.entity.Qualifier;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.model.enums.Conjunction;
import ksr.project.project.model.enums.SummaryType;
import ksr.project.project.service.fuzzy.AttributeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummarizerSingleSecond implements Summarizer {

    @Autowired
    private AttributeSummaryService attributeSummaryService;

    @Override
    public String getLinguisticSummary(Summary summary) {

        StringBuilder sb = new StringBuilder();
        String quantifier = summary.getQuantifier().getName();
        String summarySubject = summary.getAttributeSummary().get(0).getAttribute().getLabel();
        String summaryValue = summary.getAttributeSummary().get(0).getName();
        String qualifier =  summary.getQualifier().getAttributeSummary().getAttribute().toString();

        sb.append(quantifier).append(" ");
        sb.append("houses").append(" "); // subject
        sb.append("being/having").append(" "); // predicate for qualifier
        sb.append(qualifier).append(" ");
        sb.append("are/have").append(" "); // predicate for summary object
        sb.append(summarySubject).append(" "); // summary subject - attribute
        sb.append(summaryValue).append(".");

        return sb.toString();
    }

    @Override
    public Summary generateSummary(SummaryType summaryType, List<AttributeSummary> attributeSummary,
                                   Quantifier quantifier, Qualifier qualifier, Conjunction conjunction) {

        return Summary.builder()
                .summaryType(summaryType)
                .attributeSummary(attributeSummary)
                .quantifier(quantifier)
                .qualifier(qualifier)
                .conjunction(conjunction)
                .build();
    }

}
