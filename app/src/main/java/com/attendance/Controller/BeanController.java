package com.attendance.Controller;

import com.attendance.Common.SuccessDetails;
import com.attendance.DTO.BeanDTO;
import com.attendance.Exception.ResourceNotFoundException;
import com.attendance.ServiceInterface.BeanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RestController
public class BeanController {
    @Autowired
    private BeanService beanService;
    // @Autowired
    // private KafkaService kafkaService;
    private final SuccessDetails successDetails = new SuccessDetails();

    @GetMapping("/beans")
    public ResponseEntity<SuccessDetails> getAllBeans() throws ResourceNotFoundException {
        Collection<BeanDTO> beans = beanService.getBeans();
        // kafkaService.sendMessage("example_topic", beans);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                "Operation successful",
                beans
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @GetMapping("/beans/sold")
    public Collection<BeanDTO> getAllSoldBeans() throws ResourceNotFoundException {

        return beanService.getSoldBeans();
    }

    @GetMapping("/beans/{id}")
    public ResponseEntity<SuccessDetails> getBean(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        BeanDTO bean = beanService.getBean(id);
        SuccessDetails successDetails = new SuccessDetails(
                new Date(),
                "Operation successful",
                bean
        );
        return new ResponseEntity<>(successDetails, HttpStatus.OK);
    }

    @PostMapping("/beans")
    public ResponseEntity<Object> createBean(@RequestBody @Valid BeanDTO beanDTO) {

        beanService.createBean(beanDTO);
        SuccessDetails successDetails = new SuccessDetails(new Date(),"Bean is created successfully", "/beans");
        return new ResponseEntity<>(successDetails, HttpStatus.CREATED);
    }

    @PutMapping(value = "/beans/{id}")
    public ResponseEntity<Object> updateBean(@PathVariable("id") UUID id, @RequestBody @Valid BeanDTO beanDTO) throws ResourceNotFoundException {

        beanService.updateBean(id, beanDTO);
        return new ResponseEntity<>("Bean is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/beans/{id}")
    public ResponseEntity<Object> deleteBean(@PathVariable("id") UUID id) throws ResourceNotFoundException {
        beanService.deleteBean(id);
        return new ResponseEntity<>("Bean is deleted successfully", HttpStatus.OK);
    }

}
