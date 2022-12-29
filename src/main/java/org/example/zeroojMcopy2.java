package org.example;

import java.util.Scanner;

public class zeroojMcopy2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int count = 0;
            if (N==0 && M==0){
                break;
            }
            else if (N<2||M<2){
                System.out.println(count);
                break;
            }
            for (int i = N; i <= M; i++) {
                if (is_primary(i)) {
                    if(is_palindrome(i*i)){
                        count=count+1;
                    }
                }
            }
            System.out.println(count);
        }
    }
    /**
     * 判断素数
     * @param num
     * @return
     */
    public static boolean is_primary(int num){
        boolean flag = true;
        for (int i=2;i<num/2;i++){
            if (num%i==0){//能被整除 就不是素数
                flag = false;
                break;
            }
        }
        if (flag){
            return true;
        }
        return false;
    }

    /**
     * 判断回文数
     * @param target
     * @return
     */
    public static boolean is_palindrome(int target){
        boolean flag = true;
        //把目标数变成string
        String s = String.valueOf(target);
        for (int i=0;i<s.length()/2;i++){
            if (s.charAt(i)!=s.charAt(s.length()-i-1)){
                flag = false;
                break;
            }
        }
        if (flag){
            return true;
        }
        return false;
    }
}