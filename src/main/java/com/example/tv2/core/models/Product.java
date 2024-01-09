package com.example.tv2.core.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Product {

    private List<ProductItem> items;

    public Product(List<ProductItem> items) {
        this.items = new ArrayList<>(items);
    }

    public Product add(Product productItems) {
        var clone = new ArrayList<>(this.items);
        clone.addAll(productItems.getItems());
        return new Product(clone);
    }
    public Product add(ProductItem productItem) {
        var clone = new ArrayList<>(items);

        var currentProductItem = find(productItem);

        if (currentProductItem.isEmpty())
            clone.add(productItem);
        else
            clone.set(clone.indexOf(currentProductItem.get()), currentProductItem.get().mergeWith(productItem));

        return new Product(clone);
    }

    public Product remove(ProductItem productItem) {
        var clone = new ArrayList<>(items);

        var currentProductItem = assertThatCanRemove(productItem);

        clone.remove(currentProductItem);

        return new Product(clone);
    }

    Optional<ProductItem> find(ProductItem productItem) {
        return items.stream().filter(pi -> pi.matchesProductAndUnitPrice(productItem))
                .findAny();
    }

    public ProductItem assertThatCanRemove(ProductItem productItem) {
        var currentProductItem = find(productItem);

        if (currentProductItem.isEmpty())
            throw new IllegalStateException("Product item wasn't found");

        if (currentProductItem.get().getQuantity() < productItem.getQuantity())
            throw new IllegalStateException("Not enough product items");

        return currentProductItem.get();
    }

    public static Product empty() {
        return new Product(new ArrayList<>());
    }

    @Override
    public String toString() {
        return "ProductItemsList{items=" + items + "}";
    }
}