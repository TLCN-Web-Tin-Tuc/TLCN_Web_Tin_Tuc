package hcmute.edu.vn.adminservice.api.v1.dto;

import lombok.Data;

@Data
public class CatDto {
    private Long id;
    private String name;
    private Long parent_id;
    private String checkCat;
}
