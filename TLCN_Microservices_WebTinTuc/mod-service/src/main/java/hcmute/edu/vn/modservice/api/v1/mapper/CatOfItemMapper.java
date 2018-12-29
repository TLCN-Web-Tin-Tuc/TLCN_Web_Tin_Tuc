package hcmute.edu.vn.modservice.api.v1.mapper;

import hcmute.edu.vn.modservice.api.v1.dto.CatOfItemDto;
import hcmute.edu.vn.modservice.model.Cat_Item;
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
            @Mapping(source = "id.item.id", target = "itemId"),
            @Mapping(source = "id.item.image", target = "image"),
            @Mapping(source = "id.item.title", target = "title"),
            @Mapping(source = "id.item.status", target = "status"),
            @Mapping(source = "id.item.shortDesc", target = "shortDesc"),
            @Mapping(source = "id.item.fullDesc", target = "fullDesc"),
            @Mapping(source = "id.item.author", target = "author"),
            @Mapping(source = "id.item.views", target = "views"),
            @Mapping(source = "id.item.likes", target = "likes"),
            @Mapping(source = "id.item.download", target = "download"),
            @Mapping(source = "id.item.comment", target = "comment"),
            @Mapping(source = "id.item.dateCreated", target = "dateCreated"),
            @Mapping(source = "id.item.userCreated", target = "userCreated"),
            @Mapping(source = "id.item.dateUpdated", target = "dateUpdated"),
            @Mapping(source = "id.item.userUpdated", target = "userUpdated")


    })
    CatOfItemDto listcatitemTolistCatItemDto(Cat_Item listCatItem);

}
