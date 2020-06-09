package ksr.project.project.model;

import ksr.project.project.model.entity.AttributeSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qualifier {

    private AttributeSummary attributeSummary;

}
