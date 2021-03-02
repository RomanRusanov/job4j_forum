package ru.job4j.forum.model;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Discussions {

    private int id;
    private String description;
    private Calendar created;

    {
        this.created = new GregorianCalendar();
        this.created.setTimeInMillis(System.currentTimeMillis());
    }

    public static Discussions of(int id, String description) {
        Discussions dis = new Discussions();
        dis.id = id;
        dis.description = description;
        return dis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Discussions that = (Discussions) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Discussions{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created=" + this.getCreateDate()
                + '}';
    }
}