package com.bookstore.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "remark")
public class Remark {
    @Id
    private Long id;
    private List<String> remark_items = new ArrayList<>();

    public Remark(Long i) {id = i;}

    public List<String> addRemark(String remark) {
        remark_items.add(remark);
        return remark_items;
    }
}
