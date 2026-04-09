package com.briomax.briobpm.business.helpers;

import java.util.ArrayList;

public class BrioBPMGridBean {

	private ArrayList<GridCells>lrows;
	
	public BrioBPMGridBean(){
		lrows = new ArrayList<GridCells>();
	}
	
	public GridCells[] getRows() {
		return  lrows.toArray(new GridCells[0]); 
	}

	public void addRow(String[] row){
		lrows.add(new GridCells(row));
	}
	
	class GridCells{
		public String[] cells;
		public GridCells(String[] row){
			cells = row;
		}
	}

}
