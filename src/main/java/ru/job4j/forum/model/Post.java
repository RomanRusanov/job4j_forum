package ru.job4j.forum.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private String name;
    private String desc;
    private Calendar created;
    private List<Discussions> discussions;

    {
        this.created = new GregorianCalendar();
        this.created.setTimeInMillis(System.currentTimeMillis());
        this.discussions = new ArrayList<>();
    }

    public static Post of(int id, String name) {
        Post post = new Post();
        post.id = id;
        post.name = name;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getCreateDate() {
        GregorianCalendar calendar = (GregorianCalendar) this.created;
        return calendar.toZonedDateTime().format(DateTimeFormatter.ofPattern("d MMM uuuu : kk.mm"));
    }

    public Discussions addDiscussion(Discussions discussion) {
        this.discussions.add(discussion);
        return discussion;
    }

    public List<Discussions> getDiscussions() {
        return new ArrayList<>(this.discussions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null
                || getClass()
                != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(name, post.name)
                && Objects.equals(desc, post.desc)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", created=" + created
                + '}';
    }
}