package hcmute.edu.vn.modservice.api.v1.dto;

import hcmute.edu.vn.modservice.model.Cat;
import hcmute.edu.vn.modservice.model.ItemAccess;
import lombok.Data;

import java.util.Set;

@Data
public class ItemDto {
    private Long id;
    private String fileName;
    private String fileExtension;
    private String image;
    private String title;
    private String ShortDesc;
    private String FullDesc;
    private String Author;
    private Long Views;
    private Long Likes;
    private Long Download;
    private Long Comment;
    private Set<Cat> cats;
    private Set<ItemAccess> itemAccesses;
}
