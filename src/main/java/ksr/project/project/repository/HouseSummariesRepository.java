package ksr.project.project.repository;

import ksr.project.project.model.entity.HouseSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseSummariesRepository extends JpaRepository<HouseSummary,Long> {
}
