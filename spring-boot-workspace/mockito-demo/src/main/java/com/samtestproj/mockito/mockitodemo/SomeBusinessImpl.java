package com.samtestproj.mockito.mockitodemo;

public class SomeBusinessImpl {
	private DataService dataService;
	
	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	int findGreatestFromAllData() {
		int[] data = dataService.retrieveAllData();
		int greatest = Integer.MIN_VALUE;
		for(int val : data) {
			if(greatest < val)
				greatest= val;
		}
		return greatest;
	}
	
}