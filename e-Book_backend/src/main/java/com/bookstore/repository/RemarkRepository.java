package com.bookstore.repository;

import com.bookstore.entity.Remark;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RemarkRepository extends MongoRepository<Remark, Long> {
}
