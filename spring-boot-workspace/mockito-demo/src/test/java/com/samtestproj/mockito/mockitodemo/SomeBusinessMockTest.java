package com.samtestproj.mockito.mockitodemo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import org.junit.Test;

public class SomeBusinessMockTest {
	@Test
	public void testFindGreatestFromAllData() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {24,6,-1,10,81,69,-100});
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		assertEquals(81,businessImpl.findGreatestFromAllData());
	}

	@Test
	public void testFindGreatestFromAllData_OneValue() {
		DataService dataServiceMock = mock(DataService.class);
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {-100});
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		assertEquals(-100,businessImpl.findGreatestFromAllData());
	}
	
}

