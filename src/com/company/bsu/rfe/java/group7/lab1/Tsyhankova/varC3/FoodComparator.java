package com.company.bsu.rfe.java.group7.lab1.Tsyhankova.varC3;

import java.util.Comparator;

public class FoodComparator implements Comparator<Food> {
    public int compare(Food A, Food B){
        if (A.getSirup().length() == B.getSirup().length()) {
            return 0;
        }
        else {
            if (A.getSirup().length() < B.getSirup().length())
                return 1;
            else
                return -1;
        }
    }
}
