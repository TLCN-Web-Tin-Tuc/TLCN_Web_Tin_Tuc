package hcmute.edu.vn.userservice.api.v1.dto;

import lombok.Data;

@Data
public class CatDto {
    private Long id;
    private String name;
    private Long parentId;
    private String checkCat;
}
