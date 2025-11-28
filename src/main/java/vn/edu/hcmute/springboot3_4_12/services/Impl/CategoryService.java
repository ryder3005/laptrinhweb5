package vn.edu.hcmute.springboot3_4_12.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.edu.hcmute.springboot3_4_12.entity.Category;
import vn.edu.hcmute.springboot3_4_12.repository.CategoryRepository;
import vn.edu.hcmute.springboot3_4_12.services.ICategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Optional<Category> findById(Integer integer) {
        return categoryRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        categoryRepository.deleteById(integer);
    }

    @Override
    public <S extends Category> List<S> findAll() {
        return (List<S>) categoryRepository.findAll();
    }

    @Query("select categoryName from Category ")
    @Override
    public List<Category> findByCategoryNameContaining(String name) {
        return categoryRepository.findCategoriesByCategoryNameContaining(name);
    }

}
