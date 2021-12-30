package com.bookstore.node;

import com.bookstore.entity.Book;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import java.util.HashSet;
import java.util.Set;

@Node
//@Builder.Default
//@Data
public class BookNode {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Relationship(type = "contain")
    public Set<TagNode> tagNodes = new HashSet<>();

    public BookNode() {}

    public BookNode(String name) {
        this.name = name;
    }

    public void containTag(TagNode tag) {
        if (tagNodes == null) {
            tagNodes = new HashSet<>();
        }
        tagNodes.add(tag);
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
