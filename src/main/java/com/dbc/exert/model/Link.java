package com.dbc.exert.model;

import lombok.Data;

@Data
public class Link {

    private String id;
    private String link;
    private int webSize;
    private String fileName;
    private String content;

    public Link() {
    }

    public Link(String id, String link, int webSize, String fileName) {
        this.id = id;
        this.link = link;
        this.webSize = webSize;
        this.fileName = fileName;
    }
}
