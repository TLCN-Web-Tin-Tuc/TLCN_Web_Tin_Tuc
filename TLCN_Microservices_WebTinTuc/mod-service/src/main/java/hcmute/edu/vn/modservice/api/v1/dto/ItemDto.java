package hcmute.edu.vn.modservice.api.v1.dto;

import lombok.Data;

@Data
public class ItemDto {
    private Long id;
    private String fileName;
    private String fileExtension;
    private String image;
    private Long status;
    private String title;
    private String shortDesc;
    private String fullDesc;
    private String author;
    private Long views;
    private Long likes;
    private Long download;
    private Long comment;

}
