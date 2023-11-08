package com.tov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tov.domain.RegionEntity;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {
}
