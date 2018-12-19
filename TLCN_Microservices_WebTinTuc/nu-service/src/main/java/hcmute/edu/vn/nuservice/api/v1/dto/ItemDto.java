package hcmute.edu.vn.nuservice.api.v1.dto;

import hcmute.edu.vn.nuservice.model.Attach_File;
import hcmute.edu.vn.nuservice.model.Cat;
import hcmute.edu.vn.nuservice.model.Item_Access;
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
    private Set<Item_Access> item_accesses;
    private Set<Attach_File> attach_files;
}
