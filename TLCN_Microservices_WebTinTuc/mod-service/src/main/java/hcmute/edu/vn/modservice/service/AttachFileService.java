package hcmute.edu.vn.modservice.service;

import hcmute.edu.vn.modservice.model.Attach_File;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttachFileService {
    CrudRepository<Attach_File,Long> getRepo();
    Attach_File getAttach_FileById(long id);
    List<Attach_File> getAllAttachFile();
    List<Attach_File> getAllAttachFileByItem(long id);
    Attach_File InsertAttachFile(Attach_File attach_file);
    void DeleteAttachFile(long id);
}
