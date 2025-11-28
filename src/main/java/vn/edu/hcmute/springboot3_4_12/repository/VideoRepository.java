package vn.edu.hcmute.springboot3_4_12.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmute.springboot3_4_12.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findByTitleContainingIgnoreCase(String q, Pageable pageable);
}