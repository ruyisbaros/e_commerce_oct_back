package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.DTO.CardItemDTO;
import com.ahmet.e_commerce_oct_back.entities.CardItem;
import com.ahmet.e_commerce_oct_back.exceptions.ApiResponse;
import com.ahmet.e_commerce_oct_back.services.CardItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@AllArgsConstructor
public class CardItemController {

    private CardItemService cardItemService;

    @PostMapping("/user/create")
    public ResponseEntity<?> addToCart(@Valid  @RequestBody CardItemDTO request){
        CardItem toCreated=cardItemService.createCart(request);
        return ResponseEntity.ok(toCreated);
    }

    @GetMapping("/user/get_one/{cartId}")
    public CardItem findACart(@PathVariable Long cartId){
        return cardItemService.findOneCart(cartId);
    }

    @GetMapping("/user/get_all/{userId}")
    List<CardItem> findUsersCartItems(@PathVariable Long userId){
        return cardItemService.findUserCarts(userId);
    }

    @DeleteMapping("/user/delete_one/{cartId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartId){
        cardItemService.deleteCartItem(cartId);
        return new ResponseEntity<>(new ApiResponse("Cart Item has been deleted", true), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete_all/{userId}")
    public ResponseEntity<ApiResponse> deleteUsersAllCarts(@PathVariable Long userId){

        cardItemService.makeEmptyUserCartBox(userId);
        return new ResponseEntity<>(new ApiResponse("Cart Item has been deleted", true), HttpStatus.OK);
    }
}
