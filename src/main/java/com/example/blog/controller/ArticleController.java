package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.model.Article;
import com.example.blog.repository.ArticleRepository;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "article/list";
    }

    @GetMapping("/article/create")
    public String showCreateArticleForm() {
        return "article/create";
    }


    @PostMapping("/article/create")
public String createArticle(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String title,
                            @RequestParam String content) {
    Article article = new Article();
    article.setName(name);
    article.setEmail(email);
    article.setTitle(title);
    article.setContent(content);
    articleRepository.save(article);
    return "redirect:/articles";
}

@GetMapping("/article/edit/{id}")
    public String showEditArticleForm(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            // Handle not found error
        }
        model.addAttribute("article", article);
        return "article/edit";
    }

    @PostMapping("/article/edit/{id}")
    public String editArticle(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String title,
                              @RequestParam String content) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            // Handle not found error
        }
        article.setName(name);
        article.setEmail(email);
        article.setTitle(title);
        article.setContent(content);
        articleRepository.save(article);
        return "redirect:/articles";
    }



    @PostMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }
}
