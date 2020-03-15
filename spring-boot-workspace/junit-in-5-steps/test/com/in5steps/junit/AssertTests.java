package com.in5steps.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AssertTests {
MyMath math;
	
	@Test
	void testSum_with3() {
		math = new MyMath();
		int[] nums = {1,2,3};
		
		assertEquals(6,math.sum(nums));
		assertTrue(math.sum(nums)==6);
		assertFalse(math.sum(nums)!=6);
		assertNotNull(math.sum(nums));
		assertArrayEquals(nums, nums);
	}

}
