package com.ahmet.e_commerce_oct_back.services;

import com.ahmet.e_commerce_oct_back.DTO.CardItemDTO;
import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.entities.CardItem;
import com.ahmet.e_commerce_oct_back.entities.Product;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceNotFoundExceptionLongValue;
import com.ahmet.e_commerce_oct_back.repositories.CardItemRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CardItemService {

    private CardItemRep cardItemRep;

    private UserService userService;

    private ProductService productService;

    public CardItem createCart(CardItemDTO request) {
        CardItem newCart = new CardItem();
        Product cartProduct = productService.getOne(request.getProductId());
        AppUser cartOwner = userService.findUser(request.getUserId());

        newCart.setProduct(cartProduct);
        newCart.setAppUser(cartOwner);
        newCart.setQuantity(request.getQuantity());
        newCart.setCreatedDate(new Date());


        return cardItemRep.save(newCart);
    }

    public List<CardItem> findUserCarts(Long userId) {
        AppUser cartOwner = userService.findUser(userId);
        return cardItemRep.findByAppUser(cartOwner);
    }

    public CardItem findOneCart(Long cartId) {
        return cardItemRep.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("CartItem", "ID", cartId));
    }

    public void deleteCartItem(Long cartId) {
        CardItem deleteOne = this.findOneCart(cartId);
        cardItemRep.deleteById(deleteOne.getId());
    }


    public void makeEmptyUserCartBox(Long userId) {
        List<CardItem> usersCartItems = this.findUserCarts(userId);

        for (CardItem cardItem : usersCartItems) {
            this.deleteCartItem(cardItem.getId());
        }
    }
}
