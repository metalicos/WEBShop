package com.ostap.komplikevych.webshop.model.sort;


import com.ostap.komplikevych.webshop.dao.ProductDao;
import com.ostap.komplikevych.webshop.entity.DetailedProduct;
import com.ostap.komplikevych.webshop.entity.Product;
import com.ostap.komplikevych.webshop.localization.Language;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter {

    public static final Comparator<DetailedProduct> SORT_DETAILED_PRODUCT_BY_PRICE_FROM_CHEAP_TO_EXPENSIVE =
            Comparator.comparing(DetailedProduct::getPrice);

    public static final Comparator<DetailedProduct> SORT_DETAILED_PRODUCT_BY_PRICE_FROM_EXPENSIVE_TO_CHEAP =
            Comparator.comparing(DetailedProduct::getPrice).reversed();

    public static final Comparator<DetailedProduct> SORT_DETAILED_PRODUCT_BY_NAME_A_Z =
            Comparator.comparing(DetailedProduct::getName);

    public static final Comparator<DetailedProduct> SORT_DETAILED_PRODUCT_BY_NAME_Z_A =
            Comparator.comparing(DetailedProduct::getName).reversed();

    public static final Comparator<DetailedProduct> SORT_PRODUCT_BY_CREATE_DATE_NEW =
            Comparator.comparing(DetailedProduct::getCreateDate).reversed();

    public static final Comparator<DetailedProduct> SORT_PRODUCT_BY_CREATE_DATE_OLD =
            Comparator.comparing(DetailedProduct::getCreateDate);

    public static void setSortDetailedProductByNameAZ(List<DetailedProduct> products) {
        products.sort(SORT_DETAILED_PRODUCT_BY_NAME_A_Z);
    }

    public static void setSortDetailedProductByNameZA(List<DetailedProduct> products) {
        products.sort(SORT_DETAILED_PRODUCT_BY_NAME_Z_A);
    }

    public static void setSortDetailedProductByPriceFromCheapToExpensive(List<DetailedProduct> products) {
        products.sort(SORT_DETAILED_PRODUCT_BY_PRICE_FROM_CHEAP_TO_EXPENSIVE);
    }

    public static void setSortDetailedProductByPriceFromExpensiveToCheap(List<DetailedProduct> products) {
        products.sort(SORT_DETAILED_PRODUCT_BY_PRICE_FROM_EXPENSIVE_TO_CHEAP);
    }

    public static void sortDetailedProductByCreateDateNew(List<DetailedProduct> products) {
        products.sort(SORT_PRODUCT_BY_CREATE_DATE_NEW);
    }

    public static void sortDetailedProductByCreateDateOld(List<DetailedProduct> products) {
        products.sort(SORT_PRODUCT_BY_CREATE_DATE_OLD);
    }

    public static void main(String[] args) throws Exception {
        ProductDao productDao = new ProductDao();
        List<Product> products1 = productDao.readAllProductsInShop();
        List<DetailedProduct> products = new ArrayList<>();
        for (Product p : products1) {
            products.add(new DetailedProduct(p.getId(), Language.EN));
        }

        System.out.println("By PRICE LOW -> HIGH $$$$$$$$$$$$$");
        Sorter.setSortDetailedProductByPriceFromCheapToExpensive(products);
        products.forEach(System.out::println);

        System.out.println("By PRICE HIGH -> LOW $$$$$$$$$$$$$");
        Sorter.setSortDetailedProductByPriceFromExpensiveToCheap(products);
        products.forEach(System.out::println);

        System.out.println("By Date New $$$$$$$$$$$");
        Sorter.sortDetailedProductByCreateDateNew(products);
        products.forEach(System.out::println);

        System.out.println("By Date New $$$$$$$$$$$");
        Sorter.sortDetailedProductByCreateDateOld(products);
        products.forEach(System.out::println);

        System.out.println("By NAME A-Z $$$$$$$$$$$$$");
        Sorter.setSortDetailedProductByNameAZ(products);
        products.forEach(System.out::println);

        System.out.println("By NAME Z-A $$$$$$$$$$$$$");
        Sorter.setSortDetailedProductByNameZA(products);
        products.forEach(System.out::println);
    }
}
