package ksr.project.project.model.enums;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
public enum Attribute {

    PRICE("price"),
    BEDROOM("bedroom"),
    BATHROOM("bathroom"),

    FLOOR("floor"),
    STATE("state"),
    VIEW("view"),

    RESIDENTIAL_AREA("residential area"),
    GARDEN_AREA("garden area"),
    ATTIC_AREA("attic area"),
    BASEMENT_AREA("basement area"),
    BUILDING_AGE("building age"),
    RENOVATION_AGE("renovation age");

    @Setter
    @Getter
    public String label;

}
