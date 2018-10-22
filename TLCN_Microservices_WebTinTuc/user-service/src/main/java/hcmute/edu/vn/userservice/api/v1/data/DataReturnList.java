package hcmute.edu.vn.userservice.api.v1.data;

import lombok.Data;

import java.util.List;

@Data
public class DataReturnList<T> {
    private String message;
    private String success="true";
    private List<T> data;
}
