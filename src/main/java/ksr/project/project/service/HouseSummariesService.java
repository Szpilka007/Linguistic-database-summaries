package ksr.project.project.service;

import ksr.project.project.model.entity.HouseSummaries;
import ksr.project.project.repository.HouseSummariesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseSummariesService {

    private final HouseSummariesRepository houseSummariesRepository;

    @Autowired
    public HouseSummariesService(HouseSummariesRepository houseSummariesRepository) {
        this.houseSummariesRepository = houseSummariesRepository;
    }

    public HouseSummaries addHouseSummaries(HouseSummaries houseSummaries){
         return this.houseSummariesRepository.save(houseSummaries);
    }

    public List<HouseSummaries> getAllHouseSummaries(){
        return this.houseSummariesRepository.findAll();
    }

    public HouseSummaries getHouseSummaries(Long id){
        return this.houseSummariesRepository.getOne(id);
    }

    public void deleteAllSummaries(){
        this.houseSummariesRepository.deleteAll();
    }
}
