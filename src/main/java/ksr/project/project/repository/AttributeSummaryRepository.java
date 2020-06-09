package ksr.project.project.repository;

import ksr.project.project.model.entity.AttributeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeSummaryRepository extends JpaRepository<AttributeSummary, Long> {
}