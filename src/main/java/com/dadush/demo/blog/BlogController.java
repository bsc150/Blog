package com.dadush.demo.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(method = RequestMethod.POST, value = "/blogs")
    public void addBlog(@RequestBody Blog blog) {
        blogService.addBlog(blog);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blogs/{id}")
    public Blog getBlog(@PathVariable int id){
        return blogService.getBlog(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/blogs/{id}")
    public void updateBlog(@RequestBody Blog blog, @PathVariable int id) {
        blogService.updateBlog(blog, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/blogs/{id}")
    public void deleteBlog(@PathVariable int id) {
        blogService.deleteBlog(id);
    }

    @RequestMapping("/blogs/like/{id}")
    public void likeBlog(@PathVariable int id) {
        blogService.likeBlog(id);
    }

    @RequestMapping("/mostLiked")
    public List<Blog> getMostLikedBlogs() {
        return blogService.getMostLikedBlogs();
    }

    @RequestMapping("/mostRecentBlogs")
    public List<Blog> getMostRecentBlogs() {
        return blogService.getMostRecentBlogs();
    }
}
