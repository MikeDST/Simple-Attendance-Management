package com.attendance.ServiceInterface;

import com.attendance.DTO.BeanDTO;
import com.attendance.Exception.ResourceNotFoundException;

import java.util.Collection;
import java.util.UUID;


public interface BeanService {
    void createBean(BeanDTO beanDTO);
    void updateBean(UUID beanId, BeanDTO beanDTO) throws ResourceNotFoundException;
    void deleteBean(UUID beanId) throws ResourceNotFoundException;
    BeanDTO getBean(UUID beanId) throws ResourceNotFoundException;
    Collection<BeanDTO> getBeans() throws ResourceNotFoundException;
    Collection<BeanDTO> getSoldBeans() throws ResourceNotFoundException;
}
