//package com.example.demo.mapper;
//
//import com.example.demo.dto.CatWebDto;
//import com.example.demo.entity.CatWeb;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface CatWebMapper {
//    CatWebMapper INSTANCE = Mappers.getMapper(CatWebMapper.class);
//
//    @Mappings({
//            @Mapping(source = "id.cat.id", target = "catId"),
//            @Mapping(source = "id.cat.name", target = "catName")
//    })
//    CatWebDto catWebToCatWebDto(CatWeb catWeb);
//}
