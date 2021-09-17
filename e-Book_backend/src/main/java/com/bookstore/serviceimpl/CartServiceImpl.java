package com.bookstore.serviceimpl;


import com.bookstore.entity.Cart;
import com.bookstore.service.CartService;
import com.bookstore.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public Cart addBooksToCartSession(long bookId) {
        HttpSession session = SessionUtil.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart((Long) session.getAttribute("user_id"));
        }
        System.out.println("the cart in session" + cart.toString());
        cart.addBookToCart(bookId);
        session.setAttribute("cart", cart);
        return cart;
    }

    @Override
    public Cart getCart() {
        HttpSession session = SessionUtil.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart((Long) session.getAttribute("user_id"));
        }
        return cart;
    }
}
