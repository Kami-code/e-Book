package com.bookstore.repository;

import com.bookstore.node.TagNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TagNodeRepository extends Neo4jRepository<TagNode, Long> {
    TagNode findByName(String name);
}
