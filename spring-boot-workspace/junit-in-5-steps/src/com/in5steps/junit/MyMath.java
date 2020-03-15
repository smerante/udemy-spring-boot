package com.in5steps.junit;

public class MyMath {
	
	int sum(int[] nums) {
		int sum=0;
		for(int i : nums) {
			sum+=i;
		}
		return sum;
	}
}
