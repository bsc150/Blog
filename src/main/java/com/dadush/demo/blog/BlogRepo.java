package com.dadush.demo.blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Integer> {
}