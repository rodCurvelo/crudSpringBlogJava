package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
