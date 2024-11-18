package com.example.demo_spring_data.repository;

import com.example.demo_spring_data.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("select b from Blog b where upper(b.title) like upper(concat('%', :title, '%'))")
    Page<Blog> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("select b from Blog b where b.category.id = :categoryId")
    Page<Blog> findByCategory_Id(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query("select b from Blog b order by b.createdAt desc")
    Page<Blog> findAllByOrderByCreatedAtDesc(Pageable pageable);

    @Query("select count(b) from Blog b where upper(b.title) like upper(concat('%', :title, '%'))")
    long countByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("select b from Blog b where b.category.id = :categoryId and upper(b.title) like upper(concat('%', :searchName, '%'))")
    Page<Blog> findByCategoryIdAndTitleContaining(@Param("categoryId") Long categoryId,
                                                  @Param("searchName") String searchName, Pageable pageable);

}
