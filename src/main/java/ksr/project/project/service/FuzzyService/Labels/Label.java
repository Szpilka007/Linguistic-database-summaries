package ksr.project.project.service.FuzzyService.Labels;

import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.model.enums.Attributes;
import ksr.project.project.service.FuzzyService.MembershipFunctions.MembershipFunction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Label {

    public String quantifierName;
    public String description;
    public boolean absolute;
    public MembershipFunction membershipFunction;
    public Attributes attribute;

    public double getMemberShip(double value){
        return this.membershipFunction.getMembership(value);
    }

    public double getCardinality(){
        return this.membershipFunction.getCardinality();
    }

    public double getAttribute(HouseEntity houseEntity) throws NoSuchFieldException, IllegalAccessException {
        Field field = HouseEntity.class.getDeclaredField(attribute.name());
        field.setAccessible(true);
        return Double.parseDouble(field.get(houseEntity).toString());
    }

    public List<HouseEntity> getSupport(List<HouseEntity> houseEntityList){
        ArrayList<HouseEntity> houseEntities = new ArrayList<>();

        houseEntityList.forEach(houseEntity -> {
            try {
                if(this.getMemberShip(this.getAttribute(houseEntity)) > 0 )
                    houseEntities.add(houseEntity);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return houseEntities;
    }

    public double getDegreeOfFuzziness(List<HouseEntity> houseEntityList){
        return (double) this.getSupport(houseEntityList).size() / (double) houseEntityList.size();
    }

    @Override
    public String toString() {
        return quantifierName + " " + description;
    }
}
