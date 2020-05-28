package ksr.project.project.service;

import ksr.project.project.model.entity.HouseSummaryEntity;
import ksr.project.project.repository.HouseSummariesRepository;
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

    public HouseSummaryEntity addHouseSummaries(HouseSummaryEntity houseSummaries){
         return this.houseSummariesRepository.save(houseSummaries);
    }

    public List<HouseSummaryEntity> getAllHouseSummaries(){
        return this.houseSummariesRepository.findAll();
    }

    public HouseSummaryEntity getHouseSummaries(Long id){
        return this.houseSummariesRepository.getOne(id);
    }

    public void deleteAllSummaries(){
        this.houseSummariesRepository.deleteAll();
    }
}
