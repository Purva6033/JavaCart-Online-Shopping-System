package com.javacart.service;

import com.javacart.dao.CartDAO;
import com.javacart.model.Cart;
import java.util.List;
import com.javacart.model.CartItem;

public class CartService {

    private CartDAO dao = new CartDAO();

    public boolean addToCart(Cart cart) {

        return dao.addToCart(cart);

    }
    public List<CartItem> getCartItems(int userId){

        return dao.getCartItems(userId);

    }
    public boolean removeCartItem(int cartId){

        return dao.removeCartItem(cartId);

    }
    public boolean increaseQuantity(int cartId){

        return dao.increaseQuantity(cartId);

    }

    public boolean decreaseQuantity(int cartId){

        return dao.decreaseQuantity(cartId);

    }
    public int getCartCount(int userId){

        return dao.getCartCount(userId);

    }

}