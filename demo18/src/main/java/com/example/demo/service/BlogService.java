package com.example.demo.service;

import com.example.demo.model.Blog;
import com.example.demo.reporitory.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Blog createBlog(Blog blog) {
        if (blog == null || blog.getTitle() == null || blog.getContent() == null) {
            throw new IllegalArgumentException("Blog data is invalid");
        }
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(Math.toIntExact(id));
    }

    @Override
    public Blog updateBlog(Long id, Blog blogDetails) {
        Blog blog = blogRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));

        blog.setTitle(blogDetails.getTitle());
        blog.setContent(blogDetails.getContent());
        blog.setUpdatedAt(blogDetails.getUpdatedAt());

        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        if (!blogRepository.existsById(Math.toIntExact(id))) {
            throw new RuntimeException("Blog not found with id: " + id);
        }
        blogRepository.deleteById(Math.toIntExact(id));
    }
}
