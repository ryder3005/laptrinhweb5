package vn.edu.hcmute.springboot3_4_12.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmute.springboot3_4_12.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    Optional<User> findById(String s);
//    Page<User> findByUsernameContaining(String username, Pageable pageable);

    Page<User> search(String q, int page, int size);

    void deleteById(String s);

    <S extends User> S save(S entity);
}
