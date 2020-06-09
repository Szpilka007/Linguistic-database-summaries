package ksr.project.project.service.summary;

import ksr.project.project.model.entity.AttributeSummary;
import ksr.project.project.model.Qualifier;
import ksr.project.project.model.entity.Quantifier;
import ksr.project.project.model.Summary;
import ksr.project.project.model.enums.SummaryType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummarizerSingleFirst implements Summarizer {

    @Override
    public String getLinguisticSummary(Summary summary) {

        StringBuilder sb = new StringBuilder();
        String quantifier = summary.getQuantifier().getName();
        String summarySubject = summary.getAttributeSummary().get(0).getAttribute().getLabel();
        String summaryValue = summary.getAttributeSummary().get(0).getName();

        sb.append(quantifier).append(" ");
        sb.append("houses").append(" "); // subject
        sb.append("are/have").append(" "); // predicate
        sb.append(summarySubject).append(" "); // summary subject - attribute
        sb.append(summaryValue).append(".");

        return sb.toString();
    }

    @Override
    public Summary generateSummary(SummaryType summaryType, List<AttributeSummary> attributeSummary,
                                   Quantifier quantifier, Qualifier qualifier) {

        Summary summary = Summary.builder()
                .summaryType(summaryType)
                .attributeSummary(attributeSummary)
                .quantifier(quantifier)
                .qualifier(qualifier)
                .build();

        return summary;
    }

}
