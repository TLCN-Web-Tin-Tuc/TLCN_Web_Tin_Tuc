package hcmute.edu.vn.adminservice.api.v1.mapper;


import hcmute.edu.vn.adminservice.api.v1.dto.UserDto;
import hcmute.edu.vn.adminservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

}
