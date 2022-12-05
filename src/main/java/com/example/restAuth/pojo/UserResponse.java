package com.example.restAuth.pojo;

public class UserResponse {
    private Long id;
    private String name;
    private String email;

    private Boolean active;

    public UserResponse(Long id, String name, String email, Boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
