package com.aloranking.foodvendor.models;

import javax.persistence.*;

@Entity
@Table(name = "auth_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;


   /* @OneToMany(mappedBy = "roles")
    private Set<AuthUser> authUser;*/


    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    /*  public Set<AuthUser> getAuthUser() {
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
