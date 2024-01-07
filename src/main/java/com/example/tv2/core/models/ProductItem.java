package com.example.tv2.core.models;

import java.math.BigDecimal;
import java.util.UUID;



public class ProductItem {

    private  UUID productId;
    private  int quantity;
    private BigDecimal unitPrice;

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal price){
        this.unitPrice = price ;
    }

    public ProductItem(UUID productId, int quantity , BigDecimal unitPrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity has to be a positive number");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice ;
    }

    public UUID getProductId() {
        return productId;
    }



    public int getQuantity() {
        return quantity;
    }

    public boolean matchesProductAndUnitPrice(ProductItem pricedProductItem) {
        return this.productId.equals(pricedProductItem.getProductId())
                && this.unitPrice == pricedProductItem.getUnitPrice() ;
    }


    public ProductItem mergeWith(ProductItem productItem) {
        if (!this.productId.equals(productItem.getProductId()))
            throw new IllegalArgumentException("Product ids do not match.");
        if (this.unitPrice != productItem.getUnitPrice())
            throw new IllegalArgumentException("Product unit prices do not match.");

        return new ProductItem(productId , productItem.quantity , unitPrice);
    }

}