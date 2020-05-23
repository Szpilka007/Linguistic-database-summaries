package ksr.project.project.model;

import ksr.project.project.model.enums.Attributes;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AttributeSummary {

    private Attributes attributes;
    private double membership;
    private String description;

}
