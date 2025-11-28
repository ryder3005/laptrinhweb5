package vn.edu.hcmute.springboot3_4_12.services;

import org.springframework.data.domain.Page;
import vn.edu.hcmute.springboot3_4_12.entity.Video;

public interface IVideoService {
    public Page<Video> search(String q, int page, int size);
}
