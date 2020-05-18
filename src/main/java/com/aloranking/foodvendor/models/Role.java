package com.aloranking.foodvendor.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "auth_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    private String role;


  /*  @OneToMany(mappedBy = "role")
    private Set<AuthUser> authUser;*/


    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

      /*public Set<AuthUser> getAuthUser() {
        return authUser;
    }

    public void setAuthUser(Set<AuthUser> authUser) {
        this.authUser = authUser;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
