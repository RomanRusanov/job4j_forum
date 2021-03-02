package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Discussions;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {

    private PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/edit")
    public String editPost(@RequestParam(value = "post_id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("post", postService.getPostById(id));
        }
        return "edit";
    }

    @PostMapping("/save")
    public String savePost(@RequestParam(value = "post_id", required = false) Long id,
                           @ModelAttribute Post post) {
        if (id != null) {
            Post postFromStore = postService.getPostById(id);
            postFromStore.setName(post.getName());
            postService.updatePost(postFromStore);
        } else {
            postService.savePost(post);
        }
        return "redirect:index";
    }

    @GetMapping("/post")
    public String discussion(@RequestParam("post_id") Long id, Model model) {
        model.addAttribute("post", this.postService.getPostById(id));
        return "post";
    }

    @GetMapping("/add_discussion")
    public String addDiscussion(@RequestParam("post_id") Long id, Model model) {
        model.addAttribute("post_id", id);
        model.addAttribute("post_name", this.postService.getPostById(id).getName());
        return "add_discussion";
    }

    @PostMapping("/add_discussion")
    public String saveDiscussion(@RequestParam("post_id") Long id,
                                 @ModelAttribute Discussions discussions,
                                 Model model) {
        model.addAttribute("post_id", id);
        this.postService.addDiscussionToPost(id, discussions);
        return "redirect:post?post_id=" + id;
    }
}