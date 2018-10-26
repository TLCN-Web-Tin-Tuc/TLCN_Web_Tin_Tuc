package hcmute.edu.vn.userservice.api.v1.mapper;

import hcmute.edu.vn.nuservice.api.v1.dto.CatDto;
import hcmute.edu.vn.nuservice.model.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatMapper {

    CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);

    List<CatDto> listcatTolistCatDto(Iterable<Cat> listcat);
}
