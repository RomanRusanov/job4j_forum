package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Discussions;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private static Integer postIdCounter = 1;
    private static Integer discussionIdCounter = 1;

    public PostService() {
        Post post = Post.of(postIdCounter++, "Продаю машину ладу 01.");
        post.addDiscussion(Discussions.of(discussionIdCounter++, "Какое состояние?"));
        posts.add(post);
    }

    public List<Post> getAll() {
        return posts;
    }

    public Post savePost(Post post) {
        post.setId(postIdCounter++);
        this.posts.add(post);
        return post;
    }

    public Post getPostById(int id) {
        return this.posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst().get();
    }

    public Post updatePost(Post post) {
        this.posts.set(this.posts.indexOf(post), post);
        return post;
    }

    public Post addDiscussionToPost(Integer postId, Discussions discussions) {
        Post postFromStore = this.getPostById(postId);
        discussions.setId(discussionIdCounter++);
        postFromStore.addDiscussion(discussions);
        return postFromStore;
    }
}