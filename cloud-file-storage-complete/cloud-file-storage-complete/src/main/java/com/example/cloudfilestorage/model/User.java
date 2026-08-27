package com.example.cloudfilestorage.model;
import jakarta.persistence.*;
@Entity @Table(name="users")
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String username;
 @Column(nullable=false) private String password;
 @Column(nullable=false) private String role="USER";
 public User(){} public User(String u,String p,String r){username=u;password=p;role=r;}
 public Long getId(){return id;} public String getUsername(){return username;} public String getPassword(){return password;} public String getRole(){return role;}
 public void setUsername(String v){username=v;} public void setPassword(String v){password=v;} public void setRole(String v){role=v;}
}