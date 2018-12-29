package hcmute.edu.vn.adminservice.api.v1.mapper;

import hcmute.edu.vn.adminservice.api.v1.dto.RoleDto;
import hcmute.edu.vn.adminservice.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mappings({
            @Mapping(source = "catRole.id", target = "catId"),
            @Mapping(source = "catRole.name", target = "catName")

    })

    RoleDto roleToRoleDto(Role role);
}
