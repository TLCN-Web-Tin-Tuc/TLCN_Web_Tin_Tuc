package hcmute.edu.vn.nuservice.api.v1.mapper;

import hcmute.edu.vn.nuservice.api.v1.dto.CatOfItemDto;
import hcmute.edu.vn.nuservice.model.Cat_Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatOfItemMapper {
    CatOfItemMapper INSTANCE = Mappers.getMapper(CatOfItemMapper.class);



    @Mappings({
            @Mapping(source = "id.cat.id", target = "catId"),
            @Mapping(source = "id.cat.name", target = "catName"),
            @Mapping(source = "id.item.id", target = "itemId")

    })

    CatOfItemDto listcatitemTolistCatItemDto(Cat_Item listCatItem);

}
