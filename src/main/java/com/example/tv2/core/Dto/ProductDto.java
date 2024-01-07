package com.example.tv2.core.Dto;

public class ProductDto {
    private String productId ;
    private Integer count ;

    public ProductDto(String productId, Integer quantity) {
        this.productId = productId ;
        this.count = quantity ;
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
