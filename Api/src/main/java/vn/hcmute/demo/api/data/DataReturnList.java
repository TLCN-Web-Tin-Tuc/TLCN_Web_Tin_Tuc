package vn.hcmute.demo.api.data;

import lombok.Data;

import java.util.List;

@Data
public class DataReturnList<T> {
    private String message;
    private String success="true";
    private List<T> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
