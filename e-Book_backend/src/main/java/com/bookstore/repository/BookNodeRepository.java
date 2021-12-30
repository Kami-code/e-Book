package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.node.BookNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookNodeRepository extends Neo4jRepository<BookNode, Long> {
    BookNode findByName(String name);
    BookNode findByTagNodesName(String name);
    @Query("MATCH (p:BookNode) -[:contain]->(p1:TagNode)<-[:contain]- (p2:BookNode) where p.name=~ $n return p2")
    List<BookNode> findByTwoRelationship(@Param("n") String n);
}
