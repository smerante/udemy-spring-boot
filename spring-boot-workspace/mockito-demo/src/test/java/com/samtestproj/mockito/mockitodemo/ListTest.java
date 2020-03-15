package com.samtestproj.mockito.mockitodemo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.awt.List;

import org.junit.Test;
import org.mockito.Mockito;

public class ListTest {

	@Test 
	public void test() {
		List listMock = mock(List.class);
		when(listMock.getWidth()).thenReturn(10);
		assertEquals(10,listMock.getWidth());
	}
	
	@Test 
	public void test_multipleReturns() {
		List listMock = mock(List.class);
		when(listMock.getWidth()).thenReturn(10).thenReturn(20).thenReturn(30);
		assertEquals(10,listMock.getWidth());
		assertEquals(20,listMock.getWidth());
		assertEquals(30,listMock.getWidth());
		assertEquals(30,listMock.getWidth());
	}
	
	@Test 
	public void testGetParam() {
		List listMock = mock(List.class);
		when(listMock.getItem(0)).thenReturn("SomeString");
		assertEquals("SomeString",listMock.getItem(0));
		assertEquals(null,listMock.getItem(1));
	}
	
	@Test 
	public void testGetParamGeneric() {
		List listMock = mock(List.class);
		when(listMock.getItem(Mockito.anyInt())).thenReturn("SomeString");
		assertEquals("SomeString",listMock.getItem(0));
		assertEquals("SomeString",listMock.getItem(1));
	}
}
