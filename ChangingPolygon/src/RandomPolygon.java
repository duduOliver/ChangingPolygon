
public class RandomPolygon {
	private Vector dot[];
	private Vector quadrant1[]; // xPostive-axis(not include) to yPostive-axis(not include)
	private Vector quadrant2[]; // yPostive-axis(not include) to xNegative-axis(not include)
	private Vector quadrant3[]; // xNegative-axis(not include) to yNegative-axis(not include)
	private Vector quadrant4[]; // yNegative-axis(not include) to xPostive-axis(not include)
	
	private Vector centerMass;
	
	public RandomPolygon(int n)
	{
			dot = new Vector[n];
			quadrant1 = new Vector[n+1];
			quadrant2 = new Vector[n+1];
			quadrant3 = new Vector[n+1];
			quadrant4 = new Vector[n+1];
			//it's used to count the number of elements in the array
			quadrant1[0] = new Vector(0, 0);
			quadrant2[0] = new Vector(0, 0);
			quadrant3[0] = new Vector(0, 0);
			quadrant4[0] = new Vector(0, 0);
			
			centerMass = new Vector(0, 0);
			
		for(int i = 0; i < n; i++)
		{
			dot[i] = new Vector(Math.random()*500, Math.random()*500);
			while (dot[i].getX() == 0 || dot[i].getY() == 0){
				dot[i] = new Vector(Math.random()*500, Math.random()*500);
			}
			centerMass.setX(dot[i].getX()+centerMass.getX());
			centerMass.setY(dot[i].getY()+centerMass.getY());
		    //System.out.println("check 2 ");

			//System.out.println(dot[i].getX() + ", " + dot[i].getY() + ", " + dot[i].getTan());
			
		}
		
		//compute the center mass 
		centerMass.setX(centerMass.getX()/n);
		centerMass.setY(centerMass.getY()/n);
		
		
		//update all the points due to the new centerMass 
		//and distribute all the n Points into 4 quadrants
		for (int i = 0; i < n; i++){
			dot[i] = new Vector(dot[i].getX() - centerMass.getX(), 
					dot[i].getY() - centerMass.getY());
			
			if(dot[i].getX()>0){
				
				if(dot[i].getY() > 0){
					quadrant1[0].setX(quadrant1[0].getX() + 1);
					quadrant1[(int)quadrant1[0].getX()] = dot[i];
				}
				else{
					quadrant4[0].setX(quadrant4[0].getX() + 1);
					quadrant4[(int)quadrant4[0].getX()] = dot[i];
				}
			}
			else{
				if(dot[i].getY() > 0){
					quadrant2[0].setX(quadrant2[0].getX() + 1);
					quadrant2[(int)quadrant2[0].getX()] = dot[i];
				}
				else{
					quadrant3[0].setX(quadrant3[0].getX() + 1);
					quadrant3[(int)quadrant3[0].getX()] = dot[i];
				}
			}
		}
		
	    //System.out.println("check 3 ");

		
		//order n points according to the counter-clockwise 		
		quadrant1 = copyOfRange(quadrant1, 1, (int)quadrant1[0].getX());
		quadrant2 = copyOfRange(quadrant2, 1, (int)quadrant2[0].getX());
		quadrant3 = copyOfRange(quadrant3, 1, (int)quadrant3[0].getX());
		quadrant4 = copyOfRange(quadrant4, 1, (int)quadrant4[0].getX());
		
	    //System.out.println("check 4 ");
		
		quickSort(quadrant1);
		quickSort(quadrant2); 
		quickSort(quadrant3); 
		quickSort(quadrant4);
			//System.out.println("check 5 ");
		
		dot = merge(quadrant1, quadrant2, quadrant3, quadrant4);
	    //System.out.println("check 6 ");
		//printDots();
		//System.out.println("check 6 ");
	}
	
	public Vector[] getDot(){
		return dot;
	}
	
	public void quickSort(Vector array[]) 
	// pre: array is full, all elements are non-null integers
	// post: the array is sorted in ascending order
	{
		if (array != null){
			quickSort(array, 0, array.length - 1); // quicksort all the elements in the array
		}
	}


	public void quickSort(Vector array[], int start, int end)
	{
		boolean flag = false;
	    int i = start;                          // index of left-to-right scan
	    int k = end;                            // index of right-to-left scan
	    if (end - start >= 1)                   // check that there are at least two elements to sort
	    {	        
	    	Vector pivot = array[start];       // set the pivot as the first element in the partition
		        while (k > i){                  // while the scan indices from left and right have not met,
		        	while (array[i].getTan() <= pivot.getTan() && i <= end && k > i)	// from the left, look for the first
		        	i++;  // element greater than the pivot
		        	while (array[k].getTan() > pivot.getTan() && k >= start && k >= i)//{	// from the right, look for the first
		        		//if (array[i].getTan() == pivot.getTan()){
		        			//flag = true;
		        			//System.out.println("check 8 ");
		        			//break Search;
		        		//}
		        	//}
		        	k--;                          // element not greater than the pivot
		       		if (k > i)                    // if the left seekindex is still smaller than
		       			swap(array, i, k);                      // the right index, swap the corresponding elements
		       	}
	    	swap(array, start, k);          // after the indices have crossed, swap the last element in
	                                      // the left partition with the pivot 
	    	quickSort(array, start, k - 1); // quicksort the left partition
	    	quickSort(array, k + 1, end);   // quicksort the right partition
	    }
	    else{}    // if there is only one element in the partition, do not do any sorting
	    	                 // the array is sorted, so exit	    
	}
	        
	public void swap(Vector array[], int index1, int index2) 
	// pre: array is full and index1, index2 < array.length
	// post: the values at indices 1 and 2 have been swapped
	{
		Vector temp = array[index1];           // store the first value in a temp
		array[index1] = array[index2];      // copy the value of the second into the first
		array[index2] = temp;               // copy the value of the temp into the second
	}
	
	//cope
	public Vector[] copyOfRange(Vector[] original, int from, int to){
		Vector temp[] ;
		if (to > 0){
		int tempCounter = to - from + 1 ;
		temp = new Vector[tempCounter];
		for(int i = 0; i < tempCounter; i++){
			temp[i] = original[from + i];
		}
		return temp;
		}
		else{ return null;}
	}
	
	//merge 4 quadrants, a for 1, b for 2, c for 3, d for 4 quadrants
	public Vector[] merge(Vector[] q1, Vector[] q2, Vector[] q3, Vector[] q4){
		int tempL = 0;
		if (q1 != null){	tempL = tempL + q1.length;	}
		if (q2 != null){	tempL = tempL + q2.length;	}
		if (q3 != null){	tempL = tempL + q3.length;	}
		if (q4 != null){	tempL = tempL + q4.length;	}

		Vector[] comb = new Vector[tempL];
		int index = 0;

		if (q1 != null){
			for(int i = 0; i < q1.length; i++){
				comb[index] = q1[i];
				index++;
			}
		}
		if (q2 != null){
			for(int i = 0; i < q2.length; i++){
				comb[index] = q2[i];
				index++;
			}
		}
		if (q3 != null){
			for(int i = 0; i < q3.length; i++){
				comb[index] = q3[i];
				index++;
			}
		}
		if (q4 != null){
			for(int i = 0; i < q4.length; i++){
				comb[index] = q4[i];
				index++;
			}
		}
					
		return comb;
	}
	
	public void printDots(){
		System.out.println(dot.length);
		for(int i = 0; i < dot.length; i++){
			System.out.println(dot[i].getX() + ", " + dot[i].getY() + ", " + dot[i].getTan());
		}		
	}
}
