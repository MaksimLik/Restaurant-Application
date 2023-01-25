package com.example.restarauntsys.tables;

public class Comments {
    private int id_comment;
    private String name_food;
    private String comment;
    private String nick_name;

    public Comments(int id_comment, String name_food, String comment, String nick_name) {
        this.id_comment = id_comment;
        this.name_food = name_food;
        this.comment = comment;
        this.nick_name = nick_name;
    }

    public Comments(int id_comment, String name_food, String comment) {
        this.id_comment = id_comment;
        this.name_food = name_food;
        this.comment = comment;
    }

    public int getId() {
        return id_comment;
    }

    public void setId(int id) {
        this.id_comment = id;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
