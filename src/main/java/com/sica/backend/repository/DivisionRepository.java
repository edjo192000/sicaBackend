package com.sica.backend.repository;

import com.sica.backend.entity.Division;
import com.sica.backend.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division, String> {
    List<Division> findByLevel(Level level);
    List<Division> findByActiveTrue();
    List<Division> findByLevelAndActiveTrue(Level level);
}
