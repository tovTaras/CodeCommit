package com.tov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tov.domain.OfficeEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.OfficeRepository;
import com.tov.service.OfficeService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public List<OfficeEntity> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public OfficeEntity findById(Integer id) {
        return officeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Office", String.valueOf(id)));
    }

    @Override
    @Transactional
    public OfficeEntity create(OfficeEntity entity) {
        officeRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, OfficeEntity entity) {
        OfficeEntity officeEntity = findById(id);
        officeEntity.setStreetAddress(entity.getStreetAddress());
        officeEntity.setPhone(entity.getPhone());
        officeEntity.setOfficeHead(entity.getOfficeHead());
        officeEntity.setCity(entity.getCity());
        officeRepository.save(officeEntity);
    }

    @Override
    public void delete(Integer id) {
        OfficeEntity officeEntity = findById(id);
        officeRepository.delete(officeEntity);
    }

    @Override
    public List<OfficeEntity> getOfficeEntitiesByCityId(Integer cityId) {
        return officeRepository.getOfficeEntitiesByCityId(cityId);
    }
}