package com.in5steps.junit;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyMathTest {
	MyMath myMath = new MyMath();
	
	@BeforeClass
	public static void BeforeClass() {
		System.out.println("Before Class");
	}
	
	@AfterClass
	public static void AfterClass() {
		System.out.println("After Class");
	}
	
	@Before
	public void beforeTest() {
		myMath = new MyMath();
		System.out.print("Before Test : ");
	}

	@After
	public void afterTest() {
		System.out.println("After Test");
	}
	
	@Test
	public void sum_with1number() {
		int[] nums = {3};
		System.out.print("Test1 : ");
		assertEquals(3, myMath.sum(nums));
		
	}
	@Test
	public void sum_with3numbers() {
		int[] nums = {1,2,3};
		System.out.print("Test3 : ");
		assertEquals(6, myMath.sum(nums));
	}
}