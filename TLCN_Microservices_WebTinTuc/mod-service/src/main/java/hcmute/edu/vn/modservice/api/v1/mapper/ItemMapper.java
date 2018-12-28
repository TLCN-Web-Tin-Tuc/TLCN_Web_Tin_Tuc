package hcmute.edu.vn.modservice.api.v1.mapper;

import hcmute.edu.vn.modservice.api.v1.dto.ItemDto;
import hcmute.edu.vn.modservice.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

   // List<ItemDto> listitemTolistItemDto(Iterable<Items> listItem);

    ItemDto itemToItemDto(Item items);
}
