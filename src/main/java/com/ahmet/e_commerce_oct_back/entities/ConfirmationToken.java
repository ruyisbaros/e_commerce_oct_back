//package com.ahmet.e_commerce_oct_back.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "confirmation_tokens")
//@Data
//@AllArgsConstructor
//public class ConfirmationToken {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String confirmationToken;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(nullable = false, name = "user_id")
//    private AppUser appUser;
//
//    public ConfirmationToken() {
//    }
//
//    public ConfirmationToken(AppUser appUser) {
//        this.appUser = appUser;
//        createdDate = new Date();
//    }
//}
