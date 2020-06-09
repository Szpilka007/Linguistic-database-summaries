package ksr.project.project.repository;

import ksr.project.project.model.entity.Quantifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuantifierRepository extends JpaRepository<Quantifier, Long> {
}