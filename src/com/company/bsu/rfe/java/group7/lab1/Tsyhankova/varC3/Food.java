package com.company.bsu.rfe.java.group7.lab1.Tsyhankova.varC3;

public abstract class Food implements Nutritious{
    String name = null;
    String sirup = null;

    public Food(String name){
        this.name = name;
    }

    public boolean equals(Food arg0){
        if (name==null || arg0.name==null) return false;
        return name.equals(arg0.name);
    }

    public String toString(){
        String str = name + " with " + this.getSirup();
        return str;
    }

    public String getName() {
        return name;
    }

    public String getSirup() { return sirup; }

    public void setName(String name){
        this.name = name;
    }

}
