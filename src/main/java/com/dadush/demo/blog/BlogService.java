package com.dadush.demo.blog;

import com.dadush.demo.exception.BlogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final int TRENDING_LIMIT = 10;

    @Autowired
    private BlogRepo blogs;

    public List<Blog> getAllBlogs() {
        List<Blog> blogs_to_ret = new ArrayList<>();
        blogs.findAll().forEach(b -> blogs_to_ret.add(b));
        System.out.println("getting all blogs");
        return blogs_to_ret;
    }

    public Blog getBlog(int id) throws BlogNotFoundException {
        Optional<Blog> blog = blogs.findById(id);
        if (blog.isPresent()) {
            System.out.println("Got blog with id: " + id);
            return blog.get();
        }
        System.out.println("throwing no blog with id: " + id);
        throw new BlogNotFoundException(genErrorMessage(id));
    }

    public void addBlog(Blog blog){
        blogs.save(blog);
    }

    public void updateBlog(Blog blog, int id) {
        Optional<Blog> optionalBlog = blogs.findById(id);
        if (optionalBlog.isPresent()){
            Blog b = optionalBlog.get();
            b.setText(blog.getText());
            System.out.println("updated blog with id: " + id);
            blogs.save(b);
        }else{
            System.out.println("throwing no blog with id: " + id);
            throw new BlogNotFoundException(genErrorMessage(id));
        }
    }

    public void deleteBlog(int id) {
        if (blogs.existsById(id)) {
            System.out.println("deleting blog with id: " + id);
            blogs.deleteById(id);
        }
        //Throws an exception if id doesn't exist
        else {
            System.out.println("throwing no blog with id: " + id);
            throw new BlogNotFoundException(genErrorMessage(id));
        }
    }

    private String genErrorMessage(int id) {
        return String.format("There is no blog with id: #%d", id);
    }

    public void likeBlog(int id) {
        Optional<Blog> opt_blog = blogs.findById(id);
        if (opt_blog.isPresent()){
            Blog blog = opt_blog.get();
            blog.Like();
            blogs.save(blog);
        }else{
            throw new BlogNotFoundException(genErrorMessage(id));
        }
    }

    public List<Blog> getMostLikedBlogs() {
        if (isDBEmpty()) {
            throw new BlogNotFoundException("There are 0 blogs");
        }
        //Sorts the blogs by 'numLikes' in DESC order
        Blog[] mostLikedBlogs = blogs.findAll(Sort.by(Sort.Direction.DESC, "numLikes")).toArray(new Blog[0]);
        return firstNBlogs(mostLikedBlogs, TRENDING_LIMIT);
    }

    private List<Blog> firstNBlogs(Blog[] blogs_array, int limit) {
        int numBlogs = Math.min(limit, blogs_array.length);
        List<Blog> firstBlogs = new ArrayList<>(Arrays.asList(blogs_array).subList(0, numBlogs));
        return firstBlogs;
    }

    private boolean isDBEmpty() {
        return blogs.count() == 0;
    }

    public List<Blog> getMostRecentBlogs() {
        //Throws an exception if there aren't blogs at all
        if (isDBEmpty()) {
            throw new BlogNotFoundException("There are 0 blogs");
        }
        //Sorts the blogs by 'createdDate' in DESC order - most recent will appear first
        Blog[] mostRecentBlogs = blogs.findAll(Sort.by(Sort.Direction.DESC, "createdDate")).toArray(new Blog[0]);
        return firstNBlogs(mostRecentBlogs, TRENDING_LIMIT);
    }
}
