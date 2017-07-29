import java.util.HashMap;
import java.util.Map;

/*
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
                  
HashTable
 */
public class LT311_Sparse_Matrix_Multiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        //without table
        int m = A.length;
        int n = A[0].length;
        int l = B[0].length;
        int[][] C = new int[m][l];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]!=0){
                    for(int k=0;k<l;k++){
                        if(B[j][k]!=0) C[i][k] += A[i][j]*B[j][k];
                    }
                }
            }
        }
        
        return C;
    }
    
    //with hashmap
    public int[][] multiply2(int[][] A, int[][] B) {
	    if (A == null || A[0] == null || B == null || B[0] == null) return null;
	    int m = A.length, n = A[0].length, l = B[0].length;
	    int[][] C = new int[m][l];
	    Map<Integer, HashMap<Integer, Integer>> tableB = new HashMap<>();
	
	    for(int k = 0; k < n; k++) {
	        tableB.put(k, new HashMap<Integer, Integer>());
	        for(int j = 0; j < l; j++) {
	            if (B[k][j] != 0){
	                tableB.get(k).put(j, B[k][j]);
	            }
	        }
	    }
	
	    for(int i = 0; i < m; i++) {
	        for(int k = 0; k < n; k++) {
	            if (A[i][k] != 0){
	                for (Integer j: tableB.get(k).keySet()) {
	                    C[i][j] += A[i][k] * tableB.get(k).get(j);
	                }
	            }
	        }
	    }
	    return C;   
	}
}
