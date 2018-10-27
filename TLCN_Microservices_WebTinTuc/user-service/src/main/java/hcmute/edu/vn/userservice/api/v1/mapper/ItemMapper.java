package hcmute.edu.vn.userservice.api.v1.mapper;

import hcmute.edu.vn.userservice.api.v1.dto.ItemDto;
import hcmute.edu.vn.userservice.model.Items;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    List<ItemDto> listitemTolistItemDto(Iterable<Items> listItem);
}
