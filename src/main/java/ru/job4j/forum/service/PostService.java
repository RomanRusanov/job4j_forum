package ru.job4j.forum.service;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Discussions;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        ArrayList<Post> posts = new ArrayList();
        this.postRepository.findAll().forEach(posts::add);
        return posts;
    }

    public Post savePost(Post post) {
        this.postRepository.save(post);
        return post;
    }

    public Post getPostById(Long id) {
        return this.postRepository.findById(id).get();
    }

    public Post updatePost(Post post) {
        this.postRepository.save(post);
        return post;
    }

    @Transactional
    public Post addDiscussionToPost(Long postId, Discussions discussions) {
        Post postFromStore = this.getPostById(postId);
        postFromStore.addDiscussion(discussions);
        this.postRepository.save(postFromStore);
        return postFromStore;
    }
}