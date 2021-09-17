package com.bookstore.entity;

import lombok.*;

import java.util.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Long user_id;
    private HashMap<Long, Integer> instance = new HashMap<>();
    public Cart(long user_id) {
        this.user_id = user_id;
    }

    public void addBookToCart(long book_id) {
        if (instance.containsKey(book_id)) {
            instance.put(book_id, instance.get(book_id) + 1);
        }
        else {
            instance.put(book_id, 1);
        }
    }
}
