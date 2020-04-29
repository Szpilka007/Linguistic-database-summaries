package ksr.project.project.repository;

import ksr.project.project.model.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {
}
