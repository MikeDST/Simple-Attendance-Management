package com.attendance.ServiceImpl;

import com.attendance.Common.Message;
import com.attendance.DTO.BeanDTO;
import com.attendance.Entity.Bean;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.Mapper.MapStructMapper;
import com.attendance.Repository.BeanRepository;
import com.attendance.ServiceInterface.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BeanServiceImpl implements BeanService {
    @Autowired
    private BeanRepository beanRepository;
    private MapStructMapper mapStructMapper;
    private final Message message = new Message();


    @Override
    public void createBean(BeanDTO beanDTO) {

        beanDTO.setId(null);
        if(beanDTO.getPublishDate() == null) {beanDTO.setPublishDate(LocalDateTime.now());}
        beanRepository.saveAndFlush(mapStructMapper.MAPPER.toEntity(beanDTO));
    }

    @Override
    public void updateBean(UUID beanId, BeanDTO beanDTO) throws ResourceNotFoundException {
        // System.out.println(bean);
        Bean foundBean = beanRepository.findById(beanId)
                .orElseThrow(() -> new ResourceNotFoundException(message.BEAN_NOT_FOUND + beanId));

        Bean bean = mapStructMapper.MAPPER.toEntity(beanDTO);
        foundBean.setName(bean.getName());
        foundBean.setPrice(bean.getPrice());
        foundBean.setInStock(bean.isInStock());
        foundBean.setSold(bean.getSold());
        beanRepository.saveAndFlush(foundBean);
    }

    @Override
    public void deleteBean(UUID beanId) throws ResourceNotFoundException {
//        beanRepository.findById(beanId).ifPresentOrElse(
//                foundBean -> beanRepository.delete(foundBean),
//                () -> System.out.println("Bean with ID " + beanId + " not found")
//        );

        Bean foundBean = beanRepository.findById(beanId)
                .orElseThrow(() -> new ResourceNotFoundException(message.BEAN_NOT_FOUND + beanId));
        beanRepository.delete(foundBean);
    }

    @Override
    public BeanDTO getBean(UUID beanId) throws ResourceNotFoundException {
        System.out.println(message.BEAN_NOT_FOUND);
        Bean bean = beanRepository.findById(beanId)
                .orElseThrow(() -> new ResourceNotFoundException(message.BEAN_NOT_FOUND + beanId));
        return mapStructMapper.MAPPER.toDto(bean);
    }

    @Override
    public Collection<BeanDTO> getBeans() throws ResourceNotFoundException{
        Collection<Bean> beans = beanRepository.findAll();

        if (beans.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_BEAN_FOUND);
        }

        return beans.stream().map(mapStructMapper.MAPPER::toDto).collect(Collectors.toList());

    }

    @Override
    public Collection<BeanDTO> getSoldBeans() throws ResourceNotFoundException{
        Collection<Bean> beans = beanRepository.findSoldBeans();

        if (beans.isEmpty()) {
            throw new ResourceNotFoundException(message.NO_BEAN_FOUND);
        }

        return beans.stream().map(mapStructMapper.MAPPER::toDto).collect(Collectors.toList());

    }
}
