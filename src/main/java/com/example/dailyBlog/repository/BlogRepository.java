package com.example.dailyBlog.repository;

import com.example.dailyBlog.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BlogRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class BlogRowMapper implements RowMapper<Blog> {
        public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
            Blog blog = new Blog();
            blog.setId(rs.getInt("id"));
            blog.setTitle(rs.getString("title"));
            blog.setContent(rs.getString("content"));
            blog.setPublishDate(rs.getTimestamp("publish_date").toLocalDateTime());
            return blog;
        }
    }

    public List<Blog> findAll() {
        return jdbcTemplate.query("SELECT * FROM blog", new BlogRowMapper());
    }

    public Blog findById(int id) {
        List<Blog> blogs = jdbcTemplate.query("SELECT * FROM blog WHERE id = ?", new Object[]{id}, new BlogRowMapper());
        return blogs.isEmpty() ? null : blogs.get(0);
    }

    public void addBlog(Blog blog) {
        jdbcTemplate.update("INSERT INTO blog (title, content, publish_date) VALUES (?, ?, ?)",
                blog.getTitle(), blog.getContent(), blog.getPublishDate());
    }
    public void updateBlog(Blog blog) {
        jdbcTemplate.update("UPDATE blog SET title = ?, content = ?, publish_date = ? WHERE id = ?",
                blog.getTitle(), blog.getContent(), blog.getPublishDate(), blog.getId());
    }

}
