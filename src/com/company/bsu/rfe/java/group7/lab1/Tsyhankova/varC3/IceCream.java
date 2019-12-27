package com.company.bsu.rfe.java.group7.lab1.Tsyhankova.varC3;

public class IceCream extends Food{
    static double calories;

    public IceCream(String sirup){
        super("IceCream");
        this.sirup = sirup;
        this.calories = 0;
    }

    public double calculateCalories(){
        if (sirup.equals ("chocolate")){
            calories += 40.0;
        } else
            if (sirup.equals ("caramel")){
                calories += 67.3;
            } else System.out.println("Enter other kind of sirup");
        return calories;
    }

    public String getSirup(){
        return sirup;
    }

    public void setSirup(String sirup){
        this.sirup = sirup;
    }

    public boolean equals(IceCream arg0){
        if (this.sirup.equals(arg0.sirup)) return true;
        return false;
    }
}