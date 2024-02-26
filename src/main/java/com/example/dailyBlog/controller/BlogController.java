package com.example.dailyBlog.controller;

import com.example.dailyBlog.model.Blog;
import com.example.dailyBlog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 导入Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;
import java.util.List; // 导入List
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/") // 保持这个映射，表示访问 "/blogs/" 时执行此方法
    public String listBlogs(Model model) {
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blogs"; // 对应于 src/main/resources/templates/blogs.html
    }

    @PostMapping("/upload")
    public String uploadBlog(Blog blog) {
        blog.setPublishDate(LocalDateTime.now()); // 设置当前时间为发布时间
        blogRepository.addBlog(blog);
        return "redirect:/blogs/"; // 上传成功后重定向到博客列表页面，确保路径与@GetMapping一致
    }
    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload"; // 对应于 src/main/resources/templates/upload.html
    }
    @GetMapping("/view/{id}")
    public String viewBlog(@PathVariable("id") int id, Model model) {
        Blog blog = blogRepository.findById(id); // 假设blogRepository有一个findById方法
        model.addAttribute("blog", blog);
        return "blogDetail"; // 返回博客详情页的视图名称
    }
    @PostMapping("/update")
    public String updateBlog(Blog updatedBlog) {
        // 获取原始博客对象
        Blog originalBlog = blogRepository.findById(updatedBlog.getId());

        // 设置更新后的博客对象的创建时间为原始博客的创建时间
        updatedBlog.setPublishDate(originalBlog.getPublishDate());

        // 更新博客
        blogRepository.updateBlog(updatedBlog);

        // 重定向到博客详情页面
        return "redirect:/blogs/view/" + updatedBlog.getId();
    }



    @GetMapping("/edit/{id}")
    public String editBlog(@PathVariable("id") int id, Model model) {
        Blog blog = blogRepository.findById(id);
        model.addAttribute("blog", blog);
        return "edit"; // 返回编辑页面的视图名称
    }


}
