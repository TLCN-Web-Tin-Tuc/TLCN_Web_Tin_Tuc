package hcmute.edu.vn.adminservice.api.v1.mapper;

import hcmute.edu.vn.adminservice.api.v1.dto.RoleDto;
import hcmute.edu.vn.adminservice.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto roleToRoleDto(Role role);
}
