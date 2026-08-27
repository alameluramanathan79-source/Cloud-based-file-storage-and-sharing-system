package com.example.cloudfilestorage.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="stored_files")
public class StoredFile {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String originalName;
 @Column(nullable=false,unique=true) private String storedName;
 private String contentType; private long size; private LocalDateTime uploadedAt;
 @ManyToOne(optional=false) private User owner;
 public StoredFile(){} public StoredFile(String n,String s,String t,long z,LocalDateTime d,User o){originalName=n;storedName=s;contentType=t;size=z;uploadedAt=d;owner=o;}
 public Long getId(){return id;} public String getOriginalName(){return originalName;} public String getStoredName(){return storedName;} public String getContentType(){return contentType;} public long getSize(){return size;} public LocalDateTime getUploadedAt(){return uploadedAt;} public User getOwner(){return owner;}
}