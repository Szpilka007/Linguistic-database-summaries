package ksr.project.project.service;

import ksr.project.project.model.entity.HouseEntity;
import ksr.project.project.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public long countHouses(){
        return this.houseRepository.count();
    }

    public void removeAll(){
        this.houseRepository.deleteAll();
    }

    public List<HouseEntity> getAllHouses(){
        return this.houseRepository.findAll();
    }

    public HouseEntity getHouse(Long id){
        return this.houseRepository.getOne(id);
    }
}
