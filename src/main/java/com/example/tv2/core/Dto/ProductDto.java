package com.example.tv2.core.Dto;

import java.math.BigDecimal;

public class ProductDto {
    private String productId ;
    private Integer count ;
    private BigDecimal price ;
    public ProductDto(String productId, Integer quantity , BigDecimal price) {
        this.productId = productId ;
        this.count = quantity ;
        this.price = price ;
    }

    public BigDecimal getPrice(){
        return this.price ;
    }

    public void setPrice(BigDecimal price){
        this.price = price ;
    }


    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId){
        this.productId = productId ;
    }

    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count){
        this.count = count ;
    }
}
