package com.example.demo.sercive;

import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Override
    public void createBlog(Blog blog) {
        blogRepository.save(blog);


    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void updateBlog(Long id, Blog blogDetails) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            Blog blog = existingBlog.get();
            blog.setTitle(blogDetails.getTitle());
            blog.setContent(blogDetails.getContent());
            blogRepository.save(blog);
        } else {
            throw new RuntimeException("Blog with ID " + id + " not found");
        }

    }

    @Override
    public void deleteBlog(Long id) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            blogRepository.deleteById(id);
        } else {
            throw new RuntimeException("Blog with ID " + id + " not found");
        }

    }

    @Override
    public Page<Blog> getBlogsByCategory(Long categoryId, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(categoryId, pageable);
    }



    @Override
    public Page<Blog> getBlogsByCategoryAndId(Long categoryId, String searchName, Pageable pageable) {
        return blogRepository.findByCategoryIdAndTitleContaining(categoryId,searchName,pageable);
    }

    @Override
    public Page<Blog> searchBlogsByTitle(String title, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(Long.valueOf(title),pageable);
    }

    @Override
    public Page<Blog> getBlogsBySearchName(String searchName, Pageable pageable) {
        return blogRepository.findBySearchNameContainingIgnoreCase(searchName,pageable);
    }
}
