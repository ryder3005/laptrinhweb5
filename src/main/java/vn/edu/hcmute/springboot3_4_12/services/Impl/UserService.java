package vn.edu.hcmute.springboot3_4_12.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.hcmute.springboot3_4_12.entity.User;
import vn.edu.hcmute.springboot3_4_12.repository.UserRepository;
import vn.edu.hcmute.springboot3_4_12.services.IUserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired private UserRepository userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(String s) {
        return userRepo.findById(s);
    }

    @Override
    public Page<User> search(String q, int page, int size) {
        Pageable p = PageRequest.of(page, size, Sort.by("id").descending());
        if (q == null || q.isBlank()) return userRepo.findAll(p);
        return userRepo.findByUsernameContaining(q, p);
    }

    @Override
    public void deleteById(String s) {
        userRepo.deleteById(s);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userRepo.save(entity);
    }
}
