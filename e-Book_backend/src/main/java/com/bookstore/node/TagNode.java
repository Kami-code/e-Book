package com.bookstore.node;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

@Node
//@Builder
//@Data
public class TagNode {
    @Id @GeneratedValue
    private Long id;
    private String name;
    public TagNode(Long id, String name) {
        this.name = name;
        this.id = id;
    }
    public TagNode() {}

    public TagNode(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
