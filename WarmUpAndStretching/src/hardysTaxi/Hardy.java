package hardysTaxi;

import java.util.List;
import java.util.ArrayList;
import static hardysTaxi.TaxicabNumber.cube;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all
 * "taxicab numbers" that are less than n.  These are represented as TaxicabNumber objects:
 * each includes the sum and two different ways of writing that sum as
 * a sum of two cubes.
 * 
 * @author anderson  November, 2010.
 *
 */
public class Hardy {
	
	/**
	 * Returns floor of the cube root of n.
	 * Can you see why this method is useful for this problem?
	 * This is not a very efficient implementation.  
	 * (Not required) Can you think of a more efficient approach?
	 * 
	 * @param n a positive integer
	 * @return integer cube root of n
	 */
	public static int cubeRootFloor(int n){
		// Very inefficient, but quick to write.
		int i=0;
		while (cube(i) <= n)
			i++;
		return i-1; 
	}
		
	/**
	 * Computes and returns a sorted list of all taxicab numbers less than n.
	 * @param n a positive integer
	 * @return a List<TaxicabNumber>  object.  Each object contains the sum and two different ways to write it as a sum of cubes.
	 */
	
	public static List<TaxicabNumber> hardySolutionsLessThan(int n) {
		List<TaxicabNumber> result = new ArrayList<TaxicabNumber>();  // You are to populate this list.
		int limit = cubeRootFloor(n);
       /* TODO: fill in the calculations */
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0; i <= limit; i++){
			for (int j = i; j <= limit; j++){
				if (cube(i) + cube(j) >= n) break;
				int temp = cube(i) + cube(j);
				int index = -1;
				for(int l = 0; l < results.size(); l++){
					if(results.get(l).get(0) == temp) index = l;
				}
				
				if(index >= 0){
					ArrayList<Integer> array = results.get(index);
					for (int k = 0; k < (array.size() - 1) / 2; k++) {
						TaxicabNumber r = new TaxicabNumber(array.get(0), i, j, array.get(k * 2 + 1),
								array.get(k * 2 + 2));
						result.add(r);
					}
					array.add(i);
					array.add(j);
				}else{
					ArrayList<Integer> r = new ArrayList<Integer>();
					r.add(temp);
					r.add(i);
					r.add(j);
					results.add(r);
				}
			}
		}

		java.util.Collections.sort(result);
		return result;
	}
	
	/**
	 * main() is provided in case you want to test your code other than 
	 * with unit tests. Just put try various arguments in the method call below.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(hardySolutionsLessThan(1730));
	}

}
