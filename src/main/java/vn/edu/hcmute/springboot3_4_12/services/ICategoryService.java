package vn.edu.hcmute.springboot3_4_12.services;

import org.springframework.data.jpa.repository.Query;
import vn.edu.hcmute.springboot3_4_12.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    <S extends Category> S save(S entity);

    Optional<Category> findById(Integer integer);

    void deleteById(Integer integer);

    <S extends Category> List<S> findAll();

    @Query("select categoryName from Category ")
    List<Category> findByCategoryNameContaining(String name);


}
