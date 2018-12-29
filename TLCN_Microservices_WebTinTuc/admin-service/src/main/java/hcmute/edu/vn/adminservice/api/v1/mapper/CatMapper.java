package hcmute.edu.vn.adminservice.api.v1.mapper;

import hcmute.edu.vn.adminservice.api.v1.dto.CatDto;
import hcmute.edu.vn.adminservice.model.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatMapper {

    CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);

    CatDto listcatTolistCatDto(Cat listcat);
}
