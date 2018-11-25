package hcmute.edu.vn.modservice.service.impl;

import hcmute.edu.vn.modservice.model.Attach_File;
import hcmute.edu.vn.modservice.repository.AttachFileRepository;
import hcmute.edu.vn.modservice.service.AttachFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachFileServiceImpl implements AttachFileService {

    @Autowired
    AttachFileRepository attachFileRepository;

    @Override
    public CrudRepository<Attach_File, Long> getRepo() {
        return attachFileRepository;
    }

    @Override
    public Attach_File getAttach_FileById(long id) {
        return attachFileRepository.findById(id).get();
    }

    @Override
    public List<Attach_File> getAllAttachFile() {
        return attachFileRepository.findAll();
    }

    @Override
    public List<Attach_File> getAllAttachFileByItem(long id) {
        return attachFileRepository.findAllByItem(id);
    }

    @Override
    public Attach_File InsertAttachFile(Attach_File attach_file) {
        return attachFileRepository.save(attach_file);
    }

    @Override
    public void DeleteAttachFile(long id) {
        attachFileRepository.delete(attachFileRepository.findById(id).get());
    }
}
