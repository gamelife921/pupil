package com.example.demo.restcontroller;

import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.dto.ProductKeywordDto;
import com.example.demo.domain.repository.ProductKeywordRepository;
import com.example.demo.domain.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductRestController {
    @Autowired
    private ProductService productService;


    //상품 목록 페이지
    @GetMapping("/list")
    public List<ProductDto> product_list(){
        //상품목록 을 불러온다.
        List<ProductDto> list = productService.getProductList();
        //키워드 목록을 불러온다.
        List<ProductKeywordDto> dto = productService.getKeywordList();

        //상품 하나하나에 키워드를 적용
        for(int i=0;i<list.size();i++){

            List<String> explain = new ArrayList<>();
            String[] explaintoString;
            List<String> keywords = new ArrayList<>();
            String[] keywordstoString;

            //해당하는 키워드가 있는지 검색
            for(int j=0;j<dto.size();j++){

                boolean iskey = list.get(i).getProductname().contains(dto.get(j).getKeyWordname());

                if(iskey){

                    explain.add(dto.get(j).getKeyWordText());
                    keywords.add(dto.get(j).getKeyWordname());

                }else{



                }

            }
            //리스트를 String 배열로 변경
            explaintoString = explain.toArray(new String[0]);
            keywordstoString = keywords.toArray(new String[0]);

            //리스트에 등록
            list.get(i).setProductkeywords(keywordstoString);
            list.get(i).setProductexplains(explaintoString);

            log.info(list.get(i).getProductname() +" 해당 키워드 : "+Arrays.toString(list.get(i).getProductkeywords())+ " 내용 : " + Arrays.toString(list.get(i).getProductexplains()));
        }


        return list;
    }

    @GetMapping("/keyword/list")
    public List<ProductKeywordDto> getKeyword_List(){
        List<ProductKeywordDto> list = productService.getKeywordList();

        return list;
    }

    @DeleteMapping("/keyword/delete/{no}")
    public void product_keyword_delete(@PathVariable int no){
        log.info("DELETE /product/keyword/delete..."+no);

        productService.deleteKeyword(no);
    }


}
