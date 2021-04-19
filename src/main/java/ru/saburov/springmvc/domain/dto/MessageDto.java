package ru.saburov.springmvc.domain.dto;

import ru.saburov.springmvc.domain.Message;
import ru.saburov.springmvc.domain.User;

public class MessageDto {
    private Long id;
    private String text;
    private String tag;
    private User author;
    private Long likes;
    private String filename;
    private boolean meLiked;

    public MessageDto(Message message, Long likes, boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public User getAuthor() {
        return author;
    }

    public Long getLikes() {
        return likes;
    }

    public String getFilename() {
        return filename;
    }

    public boolean isMeLiked() {
        return meLiked;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", tag='" + tag + '\'' +
                ", author=" + author +
                ", likes=" + likes +
                ", filename='" + filename + '\'' +
                ", meLiked=" + meLiked +
                '}';
    }
}
