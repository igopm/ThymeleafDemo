package org.example.model;

import lombok.Getter;

@Getter
public class Back {
    private final String url;


    public Back(String url) {
        this.url = url;
    }
}
