package com.company.bsu.rfe.java.group7.lab1.Tsyhankova.varC3;

import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter number of ice cream:");

        Scanner m = new Scanner(System.in);
        int n = m.nextInt();

        Food[] breakfast = new Food[n];

        for (int k = 0; k < n; k++) {
            System.out.println("Enter food_name (f.e.: 'IceCream/chocolate'): ");
            Scanner scn = new Scanner(System.in);
            String s;
            s = scn.nextLine();
            String[] parts = s.split("/");

            if (parts[0].equals("IceCream") && (parts[1].equals("chocolate") || (parts[1].equals("caramel")))){
                breakfast[k] = new IceCream(parts[1]);
            }
        }

        System.out.println("Enter special commad (-calories, -sort, -exit): ");
        Scanner scnl = new Scanner(System.in);
        String scn;
        while (true){
            scn = scnl.nextLine();
            if (scn.equals("-calories")){
                Double sum = 0.0;
                for (Food item : breakfast) {
                    if (item != null)
                        sum = item.calculateCalories();
                    else
                        break;
                }
                System.out.println("Breakfast has " + sum + " calories");
            }
            if (scn.equals("-sort")){
                FoodComparator cmp = new FoodComparator();
                Arrays.sort(breakfast, cmp);
                for (Food item: breakfast)
                    System.out.println(item.toString());
            }
            if (scn.equals("-exit"))
                System.exit(0);
        }
    }
}
