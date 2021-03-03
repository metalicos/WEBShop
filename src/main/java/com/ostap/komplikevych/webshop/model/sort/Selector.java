package com.ostap.komplikevych.webshop.model.sort;

import com.ostap.komplikevych.webshop.constant.Const;
import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.localization.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Selector {

    public static List<DetailedProduct> selectDetailedProductsThatMatchPrice(
            List<DetailedProduct> products, double minPrice, double maxPrice) {
        return products.stream()
                .filter(detailedProduct ->
                        (detailedProduct.getPrice().doubleValue() >= minPrice
                                && detailedProduct.getPrice().doubleValue() <= maxPrice))
                .collect(Collectors.toList());
    }

    public static List<DetailedProduct> selectDetailedProductsByCategoryName(
            List<DetailedProduct> products, String categoryName) {
        return products.stream()
                .filter((detailedProduct) -> (detailedProduct.getCategoryName().equals(categoryName)))
                .collect(Collectors.toList());
    }

    public static List<DetailedProduct> selectDetailedProductsByColor(
            List<DetailedProduct> products, String color) {
        return products.stream()
                .filter(detailedProduct -> (detailedProduct.getColor().equals(color)))
                .collect(Collectors.toList());
    }

    public static List<DetailedProduct> selectDetailedProductsThatContainName(
            List<DetailedProduct> products, String name) {
        return products.stream()
                .filter(detailedProduct -> (detailedProduct.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase())))
                .collect(Collectors.toList());
    }

    public static List<DetailedProduct> selectDetailedProductsThatContainSize(
            List<DetailedProduct> products, String size) {
        return products.stream()
                .filter(detailedProduct -> (detailedProduct.getSize()
                        .toLowerCase()
                        .contains(size.toLowerCase())))
                .collect(Collectors.toList());
    }

    public static List<String> selectCategoryNamesFromDetailedProducts(
            List<DetailedProduct> products) {
        return products.stream()
                .map(DetailedProduct::getCategoryName)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<String> selectProductColorNamesFromDetailedProducts(
            List<DetailedProduct> products) {
        return products.stream()
                .map(DetailedProduct::getColor)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<String> selectProductSizeNamesFromDetailedProducts(
            List<DetailedProduct> products) {
        return products.stream()
                .map(DetailedProduct::getSize)
                .distinct()
                .collect(Collectors.toList());
    }


    public static Map<String, Integer> selectProductsAmountWithColorNames(
            List<DetailedProduct> products) {
        Map<String, Integer> map = new HashMap<>();
        List<String> colors = selectProductColorNamesFromDetailedProducts(products);
        for (String color : colors) {
            for (DetailedProduct product : products) {
                if (product.getColor().equals(color)) {
                    Integer amount = map.get(color);
                    if (amount == null) {
                        map.put(color, 1);
                    } else {
                        map.put(color, ++amount);
                    }
                }
            }
        }
        return map;
    }

    public static Map<String, Integer> selectProductsAmountWithCategoryNames(
            List<DetailedProduct> products) {
        Map<String, Integer> map = new HashMap<>();
        List<String> categories = selectCategoryNamesFromDetailedProducts(products);
        for (String category : categories) {
            for (DetailedProduct product : products) {
                if (product.getCategoryName().equals(category)) {
                    Integer amount = map.get(category);
                    if (amount == null) {
                        map.put(category, 1);
                    } else {
                        map.put(category, ++amount);
                    }
                }
            }
        }
        return map;
    }

    public static Map<String, Integer> selectProductsAmountWithSizeNames(
            List<DetailedProduct> products) {
        Map<String, Integer> map = new HashMap<>();
        List<String> sizes = selectProductSizeNamesFromDetailedProducts(products);
        for (String size : sizes) {
            for (DetailedProduct product : products) {
                if (product.getSize().equals(size)) {
                    Integer amount = map.get(size);
                    if (amount == null) {
                        map.put(size, 1);
                    } else {
                        map.put(size, ++amount);
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

        Const.logger.debug("------------PRICE----------------");
        in.forEach(Const.logger::trace);
        List<DetailedProduct> out = selectDetailedProductsThatMatchPrice(in, 33, 12);
        Const.logger.debug("----------------------------");
        out.forEach(Const.logger::trace);

        Const.logger.debug("------------CATEGORY----------------");
        in.forEach(Const.logger::trace);
        out = selectDetailedProductsByCategoryName(in, in.get(3).getCategoryName());
        Const.logger.debug("----------------------------");
        out.forEach(Const.logger::trace);

        Const.logger.debug("------------COLOR----------------");
        in.forEach(Const.logger::trace);
        out = selectDetailedProductsByColor(in, in.get(3).getColor());
        Const.logger.debug("----------------------------");
        out.forEach(Const.logger::trace);

        Const.logger.debug("------------CONTAIN STRING----------------");
        in.forEach(Const.logger::trace);
        out = selectDetailedProductsThatContainName(in, "fl");
        Const.logger.debug("----------------------------");
        out.forEach(Const.logger::trace);


        Const.logger.debug("------------CATEGORY NAMES----------------");
        List<String> categoryNames = selectCategoryNamesFromDetailedProducts(in);
        categoryNames.forEach(Const.logger::trace);
        Map<String, Integer> amount1 = selectProductsAmountWithCategoryNames(in);
        for (int i = 0; i < amount1.size(); i++) {
            Const.logger.trace(amount1.get(categoryNames.get(i)));
        }

        Const.logger.debug("------------COLOR NAMES----------------");
        List<String> colors = selectProductColorNamesFromDetailedProducts(in);
        colors.forEach(Const.logger::trace);
        Map<String, Integer> amount2 = selectProductsAmountWithColorNames(in);
        for (int i = 0; i < amount2.size(); i++) {
            Const.logger.trace(amount2.get(colors.get(i)));
        }

        Const.logger.debug("------------SIZE NAMES----------------");
        List<String> sizes = selectProductSizeNamesFromDetailedProducts(in);
        sizes.forEach(Const.logger::trace);
        Map<String, Integer> amount3 = selectProductsAmountWithSizeNames(in);
        for (int i = 0; i < amount3.size(); i++) {
            Const.logger.trace(amount3.get(sizes.get(i)));
        }
    }
}
