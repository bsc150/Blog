package com.dadush.demo.blog;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Blog {
    @Id
    @GeneratedValue
    private int id;
    private String text;
    private int numLikes;
    @CreatedDate
    private Date createdDate;

    public Blog(int id, String text) {
        this.id = id;
        this.text = text;
        this.numLikes = 0;
    }

    public Blog() {}

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void Like() {
        this.numLikes++;
    }
}
