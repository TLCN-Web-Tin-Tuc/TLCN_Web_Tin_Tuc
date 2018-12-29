package hcmute.edu.vn.nuservice.api.v1.mapper;

import hcmute.edu.vn.nuservice.api.v1.dto.ItemDto;
import hcmute.edu.vn.nuservice.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDto listitemTolistItemDto(Item listItem);
}
