package com.company.bsu.rfe.java.group7.lab3.Tsyhankova.varB7;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.lang.String;

public class GornerTableModel extends AbstractTableModel{

  private Double[] coefficients;
  private Double from;
  private Double to;
  private Double step;
  private Double x;

  public GornerTableModel(Double from, Double to, Double step, Double[] coefficients){
      this.from = from;
      this.to = to;
      this.step = step;
      this.coefficients = coefficients;
  }

  public Double getTo(){
      return to;
  }

  public Double getFrom(){
      return from;
  }

  public Double getStep(){
      return step;
  }

  public int getColumnCount(){
      return 3;
  }

  public int getRowCount(){
      return new Double(Math.ceil((to - from) / step)).intValue() + 1;
  }

  public Object getValueAt(int row, int col) {
      double x = from + step * row;

      switch (col) {
          case 0:
              return x;
          case 1: {
              double result = 0.0;
              for (int i = 0; i < coefficients.length; i++) {
                  result += Math.pow(x, coefficients.length - 1 - i) * coefficients[i];
              }

              return result;
          }
          default: {
              double result = 0.0;
              for (int i = 0; i < coefficients.length; i++) {
                  result += Math.pow(x, coefficients.length - 1 - i) * coefficients[i];
              }

              String str = String.valueOf(result);

              if ((str.charAt(0) == str.charAt(str.length()-1)) || ((str.charAt(str.length()-1) == '0') && (str.charAt(0) == str.charAt(str.length()-3)) && (str.length()-2 != 1) ) )
                  return 1;
              else
                  return 0;
          }
      }

  }

  public Class<?> getColumnClass(int col){
      return Double.class;
  }

  public String getColumnName(int col){
    switch (col){
        case 0:
            return "X's value";
        case 1:
            return "Polynomial's value";
        default:
            return "The whole part is even";
    }
  }
}