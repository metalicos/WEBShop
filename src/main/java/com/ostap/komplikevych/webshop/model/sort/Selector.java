package com.ostap.komplikevych.webshop.model.sort;

import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.localization.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Selector {

    public static List<DetailedProduct> selectDetailedProductsThatMatchPrice(List<DetailedProduct> products,
                                                                             double minPrice, double maxPrice) {
        Predicate<DetailedProduct> pred =
                (detailedProduct) -> (detailedProduct.getPrice().doubleValue() >= minPrice &&
                        detailedProduct.getPrice().doubleValue() <= maxPrice);
        return products.stream().filter(pred).collect(Collectors.toList());
    }

    public static List<DetailedProduct> selectDetailedProductsByCategoryName(List<DetailedProduct> products
            , String categoryName) {
        Predicate<DetailedProduct> pred =
                (detailedProduct) -> (detailedProduct.getCategoryName().equals(categoryName));
        return products.stream().filter(pred).collect(Collectors.toList());
    }

    public static List<DetailedProduct> selectDetailedProductsByColor(List<DetailedProduct> products
            , String color) {
        Predicate<DetailedProduct> pred =
                (detailedProduct) -> (detailedProduct.getColor().equals(color));
        return products.stream().filter(pred).collect(Collectors.toList());
    }

    public static List<DetailedProduct> selectDetailedProductsThatContainStringValue(List<DetailedProduct> products
            , String searchString) {
        Predicate<DetailedProduct> pred =
                (detailedProduct) -> (detailedProduct.getName().toLowerCase().contains(searchString.toLowerCase()));
        return products.stream().filter(pred).collect(Collectors.toList());
    }

    public static List<String> selectCategoryNamesFromDetailedProducts(List<DetailedProduct> products) {
        return products.stream()
                .map(DetailedProduct::getCategoryName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static Map<String, Integer> selectProductsAmountInCategory(List<DetailedProduct> products, List<String> category) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < category.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (products.get(j).getCategoryName().equals(category.get(i))) {
                    Integer amount = map.get(category.get(i));
                    if (amount == null) {
                        map.put(category.get(i), 1);
                    } else {
                        map.put(category.get(i), ++amount);
                    }
                }
            }
        }

        return map;
    }


    public static void main(String[] args) {
        List<DetailedProduct> in = new ArrayList<>();
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.readAllProductsInShop();
        for (Product p : products) {
            in.add(new DetailedProduct(p.getId(), Language.EN));
        }
        System.out.println("------------PRICE----------------");
        in.forEach(System.out::println);
        List<DetailedProduct> out = selectDetailedProductsThatMatchPrice(in, 33, 12);
        System.out.println("----------------------------");
        out.forEach(System.out::println);

        System.out.println("------------CATEGORY----------------");
        in.forEach(System.out::println);
        out = selectDetailedProductsByCategoryName(in, in.get(3).getCategoryName());
        System.out.println("----------------------------");
        out.forEach(System.out::println);

        System.out.println("------------COLOR----------------");
        in.forEach(System.out::println);
        out = selectDetailedProductsByColor(in, in.get(3).getColor());
        System.out.println("----------------------------");
        out.forEach(System.out::println);

        System.out.println("------------CONTAIN STRING----------------");
        in.forEach(System.out::println);
        out = selectDetailedProductsThatContainStringValue(in, "fl");
        System.out.println("----------------------------");
        out.forEach(System.out::println);


        System.out.println("------------CATEGORY NAMES----------------");
        List<String> categoryNames = selectCategoryNamesFromDetailedProducts(in);
        categoryNames.forEach(System.out::println);

        System.out.println("------------CATEGORY NAMES----------------");
        Map<String,Integer> amount = selectProductsAmountInCategory(in,categoryNames);

        for (int i = 0; i < amount.size(); i++) {
            System.out.println(amount.get(categoryNames.get(i)));
        }
    }
}
