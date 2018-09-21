package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "Logged_History")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logged_History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date logged_time;
    private long id_logged;
    private String ip_address;
}
