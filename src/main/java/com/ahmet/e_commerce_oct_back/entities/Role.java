package com.ahmet.e_commerce_oct_back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String roleName;

    private String description;

    @JsonIgnore
    @ManyToMany( mappedBy = "roles")
    private List<AppUser> appUsers = new ArrayList<>();

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
