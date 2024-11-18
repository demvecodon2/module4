package com.example.demo_spring_data.service.iml;

import com.example.demo_spring_data.model.Blog;
import com.example.demo_spring_data.repository.BlogRepository;
import com.example.demo_spring_data.service.IBlogService;
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
            blog.setCategory(blogDetails.getCategory());
            blogRepository.save(blog);
        } else {
            // Thêm ngoại lệ hoặc xử lý nếu không tìm thấy blog
            throw new RuntimeException("Blog with ID " + id + " not found");
        }
    }

    @Override
    public void deleteBlog(Long id) {
        // Kiểm tra nếu blog tồn tại trước khi xóa
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            blogRepository.deleteById(id);
        } else {
            throw new RuntimeException("Blog with ID " + id + " not found");
        }
    }

    @Override
    public Page<Blog> getBlogsByCategory(Long categoryId, Pageable pageable) {
        return blogRepository.findByCategory_Id(categoryId, pageable);
    }

    @Override
    public Page<Blog> searchBlogsByTitle(String title, Pageable pageable) {
        return blogRepository.findByTitleContainingIgnoreCase(title, pageable);
    }
}
