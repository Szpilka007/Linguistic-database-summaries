package ksr.project.project.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public enum SummaryType {

    SINGLE_SUBJECT_FIRST("SINGLE SUBJECT SUMMARY FIRST TYPE"),
    SINGLE_SUBJECT_SECOND("SINGLE SUBJECT SUMMARY SECOND TYPE"),
    MULTI_SUBJECT("MULTI SUBJECT SUMMARY");

    @Setter
    @Getter
    public String label;

}
