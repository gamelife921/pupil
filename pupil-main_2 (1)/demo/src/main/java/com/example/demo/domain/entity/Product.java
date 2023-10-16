package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue
    private Long productcode;
    private String producttype;
    private String productname;
    private LocalDateTime producttime;

    private String productcontext;
    private MultipartFile[] productimagepaths;


}