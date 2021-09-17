package com.bookstore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Data
@Getter
@Setter
public class Cart {
    HashMap<Long, Integer> currentCar = new HashMap<>();
    public void addBookToCart(long book_id) {
        if (currentCar.containsKey(book_id)) {
            currentCar.put(book_id, currentCar.get(book_id) + 1);
        }
        else {
            currentCar.put(book_id, 1);
        }
    }
}
