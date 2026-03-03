package com.newgen.employee_management.controller;

import com.newgen.employee_management.dto.Post;
import com.newgen.employee_management.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getPosts();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Post post = postService.getPost(id);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping
    public ResponseEntity<Post> createPost() {
        Post post = new Post();
        post.setUserId(2);
        post.setId(102);
        post.setTitle("This is my Post");
        post.setBody("Hello from Velocity");

        Post post1 = postService.createPost(post);
        return ResponseEntity.ok().body(post1);
    }

}
