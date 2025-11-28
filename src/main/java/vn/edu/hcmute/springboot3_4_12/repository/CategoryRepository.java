package vn.edu.hcmute.springboot3_4_12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmute.springboot3_4_12.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer > {



    List<Category> findCategoriesByCategoryNameContaining(String categoryName);


    List<Category> findByCategoryNameContaining(String name);
}