package com.houlu.java.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.houlu.java.test.bean.Shop;

/**
 * AUTO-GENERATED: houlu @ 2018/8/19 下午6:19
 *
 * @author houlu
 * @version 1.0.0
 * @since 1.0.0
 */
public class PriceDemo {


    private List<Shop> shops = Arrays.asList(new Shop("shop1"),
            new Shop("shop2"),
            new Shop("shop3"),
            new Shop("shop4"),
            new Shop("shop5"),
            new Shop("shop6"),
            new Shop("shop7"),
            new Shop("shop8"),
            new Shop("shop9"),
            new Shop("shop10"),
            new Shop("shop12"),
            new Shop("shop12"),
            new Shop("shop13"),
            new Shop("shop14"),
            new Shop("shop15"),
            new Shop("shop16"),
            new Shop("shop17"),
            new Shop("shop18"));

    public List<String> findPrices(String product){
        return shops.stream().parallel().map(shop->
                String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

//	public List<String> findPrice(String product){
//		List<CompletableFuture<String>> priceFuture = shops.stream()
//				.map(shop -> CompletableFuture
//						.supplyAsync(()-> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
//				.collect(Collectors.toList());
//		return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
//	}

    public List<String> findPriceExecutorsCompletableFuture(String product){
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));
        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(()-> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
                .collect(Collectors.toList());
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        PriceDemo priceDemo = new PriceDemo();
        long start = System.currentTimeMillis();
        System.out.println(priceDemo.findPrices("iphone 7"));
        System.out.println("服务耗时："+(System.currentTimeMillis()-start));

        start = System.currentTimeMillis();
        System.out.println(priceDemo.findPriceExecutorsCompletableFuture("iphone 7"));
        System.out.println("服务耗时："+(System.currentTimeMillis()-start));
    }


}
