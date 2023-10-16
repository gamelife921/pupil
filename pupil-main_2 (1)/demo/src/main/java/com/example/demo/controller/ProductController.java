package com.example.demo.controller;

import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.dto.ProductKeywordDto;
import com.example.demo.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/index")
    public void product_index() {

    }

    @GetMapping("/set")
    public void product_set() {
        log.info("GET /product/set...");
    }

    @PostMapping("/set")
    public String product_post(ProductDto dto,@RequestParam("files")MultipartFile[] files) {
        productService.setProduct(dto);

        String aa = "c:\\etc\\products";
        String path = aa+File.separator+dto.getProducttype()+File.separator+UUID.randomUUID();
        System.out.println("생성하려는 경로: " + path);
        //만약 폴더가 없으면 생성한다.
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();


        MultipartFile[] images = files;
        System.out.println(images);
        //for로 하나씩 뺴내야 일므을 볼수있어




        return "redirect:/product/index";
    }

    @GetMapping("/keyword/set")
    public void product_keyword() {

    }

    @PostMapping("/keyword/set")
    public String product_keyword_post(ProductKeywordDto dto) {

        //null값 방지
        if (dto.getKeyWordname() != null && dto.getKeyWordText() != null && !dto.getKeyWordname().equals("") && !dto.getKeyWordText().equals("")) {
            log.info("POST /product/inkeyword..." + dto);
            dto.setKeyWordNo(null);
            productService.setKeyword(dto);
        } else {
            log.info("POST /product/inkeyword...no data");
            return "redirect:/product/keyword/set";
        }
        return "redirect:/product/keyword/set";

    }
}