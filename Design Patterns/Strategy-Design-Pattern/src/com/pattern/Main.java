package com.pattern;

import com.pattern.strategy.PayByCreditCard;
import com.pattern.strategy.PayByPayPal;
import com.pattern.strategy.PayStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer,Integer> priceOnProducts=new HashMap<>();
    private static BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    private static Order order=new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1,2200);
        priceOnProducts.put(2,260);
        priceOnProducts.put(3,2100);
        priceOnProducts.put(4,200);
    }

    public static void main(String[] args) throws IOException {
        while (!order.isClosed()){
            int cost;
            String continueChoice;
            do {
                System.out.print("Please, select a product:" + "\n" +
                        "1 - Mother board" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memory" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost=priceOnProducts.get(choice);
                System.out.println("Number of quantity required: ");
                int count=Integer.parseInt(reader.readLine());
                order.setTotalCost(cost*count);
                System.out.println("Do you wish to continue selecting products? Y/N: ");
                continueChoice=reader.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy==null){
                System.out.println("Please, select a payment method:" + "\n" +
                        "1 - PayPal" + "\n" +
                        "2 - Credit Card");
                String paymentMethod=reader.readLine();
                if (paymentMethod.equals("1")){
                    strategy=new PayByPayPal();
                } else {
                    strategy=new PayByCreditCard();
                }
            }

            order.processOrder(strategy);

            System.out.println("Pay "+order.getTotalCost()+" units or continue shopping? P/C: ");
            String proceed = reader.readLine();
            if(proceed.equalsIgnoreCase("P")){
                if (strategy.pay(order.getTotalCost())){
                    System.out.println("Payment has been successful.");
                } else {
                    System.out.println("FAIL! Please, Try Again!!");
                }
                order.setClosed();
            }
        }
    }
}