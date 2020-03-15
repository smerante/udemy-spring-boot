package com.samtestproj.mockito.mockitodemo;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockAnnotationsTest {
	@Mock
	DataService dataServiceMock;
	
	@InjectMocks
	SomeBusinessImpl businessImpl;
	
	@Test
	public void testFindGreatestFromAllData() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24,6,-1,10,81,69,-100});
		assertEquals(81,businessImpl.findGreatestFromAllData());
	}

	@Test
	public void testFindGreatestFromAllData_OneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {-100});
		assertEquals(-100,businessImpl.findGreatestFromAllData());
	}
	
	@Test
	public void testFindGreatestFromAllData_NoValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(Integer.MIN_VALUE,businessImpl.findGreatestFromAllData());
	}
	
}

