package com.tov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tov.domain.OfficeEntity;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Integer> {
    List<OfficeEntity> getOfficeEntitiesByCityId(Integer cityId);
}