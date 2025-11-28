package vn.edu.hcmute.springboot3_4_12.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.hcmute.springboot3_4_12.entity.Video;
import vn.edu.hcmute.springboot3_4_12.repository.VideoRepository;
import vn.edu.hcmute.springboot3_4_12.services.IVideoService;

import java.util.Optional;

@Service
public class VideoService implements IVideoService {
    @Autowired
    private VideoRepository videoRepo;
    @Override
    public Page<Video> search(String q, int page, int size) {
        Pageable p = PageRequest.of(page, size, Sort.by("id").descending());
        if (q == null || q.isBlank()) return videoRepo.findAll(p);
        return videoRepo.findByTitleContainingIgnoreCase(q, p);
    }

    public Optional<Video> findById(Long aLong) {
        return videoRepo.findById(aLong);
    }

    public <S extends Video> S save(S entity) {
        return videoRepo.save(entity);
    }

    public void deleteById(Long aLong) {
        videoRepo.deleteById(aLong);
    }
}

