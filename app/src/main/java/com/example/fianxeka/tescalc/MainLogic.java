package com.example.fianxeka.tescalc;

import android.util.Log;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by fianxeka on 02/04/17.
 */

public class MainLogic {

    public static String format = "0123456789ABCDEFGH";
    public static boolean cantZero = false;

    public static boolean cekOperator (String formula){
        char last = formula.charAt(formula.length() - 1);
        if (last == '+' || last == '-' || last == 'x' || last == ':'){
            return true;
        }
        return false;
    }

    public static String getResult(String bilangan){
        char[] bil = bilangan.toCharArray();
        Log.d("Split", Arrays.toString(bil));

        Stack<Integer> operan = new Stack<Integer>();
        Stack<Character> operator = new Stack<Character>();

        for (int i = 0; i < bil.length ; i++) {
            if ( (bil[i] >= '0' && bil[i] <= '9' ) || (bil[i] >= 'A' && bil[i] <= 'H') ){
                String temp = "";
                while (i < bil.length && ((bil[i] >= '0' && bil[i] <= '9') || (bil[i] >= 'A' && bil[i] <= 'H')) ){
                    temp+=bil[i++];
                }
                i--;
                operan.push(toDec(temp));
            }
            if (bil[i] == '+' | bil[i] == '-' | bil[i] == 'x' | bil[i] == ':'){
                while (!operator.empty() && checkPriority(bil[i],operator.peek())){
                    Log.d("tes", Arrays.toString(operan.toArray()));
                    operan.push(compute(operator.pop(),operan.pop(),operan.pop()));
                }
                operator.push(bil[i]);
            }
        }
        Log.d("Operan", Arrays.toString(operan.toArray()));
        Log.d("Operator", Arrays.toString(operator.toArray()));

        while (!operator.empty()){
            operan.push(compute(operator.pop(),operan.pop(),operan.pop()));
        }
        return toBase18(operan.pop());

    }

    public static int compute(char operan, int b, int a){
        switch (operan) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'x':
                return a * b;
            case ':':
                if (b == 0) {
                    cantZero = true;
                    return 0;
                }
                return a / b;
        }
        return 0;
    }

    public static boolean checkPriority(char operator1, char operator2){
        if ((operator1 == 'x' || operator1 == ':') && (operator2 == '+' || operator2 == '-')) {
            return false;
        }
        else {
            return true;
        }
    }

    public static int toDec(String base){
        base = base.toUpperCase();
        int result = 0;
        for (int i = 0; i < base.length() ; i++) {
            char c = base.charAt(i);
            int d =  format.indexOf(c);
            result = 18*result + d;
        }
        return result;
    }

    public static String toBase18(int base){
        if (base == 0){
            return "0";
        }
        String result = "";
        while (base > 0){
            int mod = base % 18;
            result = format.charAt(mod) + result;
            base /= 18;
        }
        return result;
    }

}
