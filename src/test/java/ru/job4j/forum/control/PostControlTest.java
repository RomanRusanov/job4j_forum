package ru.job4j.forum.control;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Discussions;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoginControl control;

    @MockBean
    private PostService posts;

    @Test
    @WithMockUser
    public void shouldReturnPost() throws Exception {
        this.mockMvc.perform(get("/post?post_id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnEdit() throws Exception {
        this.mockMvc.perform(get("/edit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnAddDiscussion() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setName("Test");
        when(this.posts.getPostById(anyLong())).thenReturn(post);
        this.mockMvc.perform(get("/add_discussion?post_id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("add_discussion"));
        verify(posts).getPostById(anyLong());
    }

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(this.control);
    }

    @Test
    @WithMockUser
    public void testAddNewPost() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).savePost(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void testAddNewDiscussion() throws Exception {
        this.mockMvc.perform(post("/add_discussion?post_id=1")
                .param("description", "Новый комментарий."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Discussions> argument = ArgumentCaptor.forClass(Discussions.class);
        verify(posts).addDiscussionToPost(anyLong(), argument.capture());
        MatcherAssert.assertThat(argument.getValue().getDescription(), is("Новый комментарий."));
    }
}