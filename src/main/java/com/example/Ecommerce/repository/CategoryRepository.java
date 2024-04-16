package com.example.Ecommerce.repository;


import com.example.Ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByName(String name);


    @Query(value = "SELECT c.* FROM category as c WHERE c.name LIKE %?1%", nativeQuery = true)
    List<Category> findCategoriesByNameKeyword(String keyword);

}
