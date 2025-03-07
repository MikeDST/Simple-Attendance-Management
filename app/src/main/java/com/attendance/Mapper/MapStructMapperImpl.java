package com.attendance.Mapper;//package com.example.Test_Project.Mapper;
//
//import com.example.Test_Project.DTO.BeanDTO;
//import com.example.Test_Project.Entity.Bean;
//
//import javax.annotation.processing.Generated;
//
//@Generated(
//        value = "org.mapstruct.ap.MappingProcessor"
//)
//@Component
//public class MapStructMapperImpl implements MapStructMapper{
//    @Override
//    public BeanDTO beanToBeanDTO(Bean bean)
//    {
//        if(bean == null){
//            return null;
//        }
//        BeanDTO beanDTO = new BeanDTO();
//
//        beanDTO.setId(bean.getId());
//        beanDTO.setName(bean.getName());
//        beanDTO.setInStock(bean.isInStock());
//        beanDTO.setPrice(bean.getPrice());
//        beanDTO.setSold(bean.getSold());
//        beanDTO.setPublishDate(bean.getPublishDate());
//
//        return beanDTO;
//    }
//
//    @Override
//    public Bean beanDTOToBean(BeanDTO beanDTO)
//    {
//        if(beanDTO == null)
//        {
//            return null;
//        }
//        Bean bean = new Bean();
//
//        bean.setId(beanDTO.getId());
//        bean.setName(beanDTO.getName());
//        bean.setInStock(beanDTO.isInStock());
//        bean.setPrice(beanDTO.getPrice());
//        bean.setSold(beanDTO.getSold());
//        bean.setPublishDate(beanDTO.getPublishDate());
//
//        return bean;
//    }
//}
