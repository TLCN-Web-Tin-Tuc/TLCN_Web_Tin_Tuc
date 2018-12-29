package hcmute.edu.vn.modservice.api.v1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CatOfItemDto {
    private  Long catId;
    private String catName;
    private Long itemId;
    private String image;
    private String title;
    private int status;
    private String shortDesc;
    private String fullDesc;
    private String author;
    private Long views;
    private Long likes;
    private Long download;
    private Long comment;
    private Date dateCreated;
    private String userCreated;
    private Date dateUpdated;
    private String userUpdated;
}
