package hcmute.edu.vn.adminservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "ne_web")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Web {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    private String classContent;

    private String classTitle;

    private String classAvatar;

    private String classShortDesc;

    @OneToMany(mappedBy = "id.web")
    private Set<CatWeb> catWeb;
}
