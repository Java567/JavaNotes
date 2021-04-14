package com.lj.servlet;

/**
 * @description:
 * @author: LiJun
 * @date: Created in 2021/3/1 17:45
 */
public class Main {
    public static double trimMean(int[] arr) {
        int b=arr[0],c=arr[0],sum=0;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]<arr[i+1]){
                if(arr[i]<=b){
                    b=arr[i];
                }
            }
            if(arr[i]>arr[i+1]){
                if(arr[i]>=c){
                    c=arr[i];
                }
            }
        }
        for(int j=0;j<arr.length;j++){
            sum+=arr[j];
        }
        int d=arr.length-2;
        double result=(sum-b-c)/d;
        System.out.println("最小值："+b+"最大值："+c);
        return result;
    }

    public static int lengthOfLongestSubstring(String s) {
            char[] sum=s.toCharArray();
            int a=0,result=0;
            if(sum.length==0){
                result=1;
                return result;
            }else{
                for(int i=0;i<sum.length-1;i++){
                    for(int j=i+1;j<sum.length;j++){
                        if(sum[i]==sum[j]){
                            a=j-i;
                            if(result<a){
                                result=a;
                            }
                            break;
                        }
                    }
                }
            }
            return result;
    }

    public static int hammingWeight(int n) {
        int result=0;
        String s = Integer.toBinaryString(n) ;

        System.out.println("sss"+s);
        char[] sum=s.toCharArray();
        for(int i=0;i<sum.length;i++){
            if(sum[i]=='1'){
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 第一题
//        int[] arr={6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0};
//        System.out.println(trimMean(arr));

 //       System.out.println(lengthOfLongestSubstring(""));
        System.out.println(hammingWeight(9));

    }
}