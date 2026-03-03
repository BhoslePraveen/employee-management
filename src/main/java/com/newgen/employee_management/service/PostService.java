package com.newgen.employee_management.service;

import com.newgen.employee_management.dto.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final RestTemplate restTemplate;

    // Get All Posts
    public List<Post> getPosts() {
        ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/posts", Post[].class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Post[] posts = responseEntity.getBody();
            return Arrays.asList(posts);
        }
        return null;
    }

    public Post getPost(int id) {
        // Basic way ==> restTemplate.getForEntity(BASE_URL + "/posts/" + id, Post.class);
        ResponseEntity<Post> responseEntity = restTemplate.getForEntity(BASE_URL + "/posts/{id}", Post.class, id);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Post post = responseEntity.getBody();
            return post;
        }
        return null;
    }

    public Post createPost(Post post) {
        ResponseEntity<Post> responseEntity = restTemplate.postForEntity(BASE_URL+"/posts", post, Post.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            Post post1 = responseEntity.getBody();
            return post1;
        }
        return null;
    }


}
