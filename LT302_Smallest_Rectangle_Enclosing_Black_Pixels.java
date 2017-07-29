/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.

Binary Search
 */
public class LT302_Smallest_Rectangle_Enclosing_Black_Pixels {
	//BFS. 
	/*Theorem
	If there are only one black pixel region, then in a projected 1D array all the black pixels are connected.
	Proof by contradiction
	Assume to the contrary that there are disconnected black pixels at i and j where i < j in the 1D projection array. Thus there exists one column k, k in (i, j) and and the column k in the 2D array has no black pixel. Therefore in the 2D array there exists at least 2 black pixel regions separated by column k which contradicting the condition of "only one black pixel region".
	Therefore we conclude that all the black pixels in the 1D projection array is connected.
	This means we can do a binary search in each half to find the boundaries, if we know one black pixel's position. And we do know that.
	
	To find the left boundary, do the binary search in the [0, y) range and find the first column vector who has any black pixel.
	
	To determine if a column vector has a black pixel is O(m) so the search in total is O(m log n)
	
	We can do the same for the other boundaries. The area is then calculated by the boundaries. Thus the algorithm runs in O(m log n + n log m)
	*/
	public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        if(m==0) return 0;
        int n = image[0].length;
        
        int l = search(image, 0, x, 0, n, true, true);
        int r = search(image, x+1, m, 0, n, false, true);
        int top = search(image, 0, y,l,r, true, false);
        int bottom = search(image, y+1, n, l, r, false, false);
        
        
        return (r - l) * (bottom - top);
    }
    
    public int search(char[][] image, int start, int end, int min, int max, boolean first, boolean row){
        //start end is used in binary search.
        //first means whether we search for the left/up or the right/down boundary
        //row means whether we search for the row or the column
        while(start!=end){
            int mid = (start+end)/2;
            int k = min;
            while(k<max && white(image, mid, k, row)) k++;
            
            if(k<max==first) end = mid;
            else 
                start  =mid+1;
            }
        return start;
    }
    
    
    boolean white(char[][] image, int mid, int k, boolean row){
        return (row ? image[mid][k] : image[k][mid]) == '0';
    }
    
    //Normal DFS. Like island. O(mn)
    public int minArea2(char[][] image, int x, int y) {
        int column = image.length; // vertical
        if (column == 0) return 0;
        int row = image[0].length; // horizontal

        int[] res = new int[4];
        res[0] = column-1; // initial upper bound value
        res[1] = 0;        // initial bottom bound value
        res[2] = row - 1;  // initial left bound value
        res[3] = 0;        // initial right bound value
        dfs(image, x, y, res);
        return (res[1]-res[0]+1) * (res[3]-res[2]+1); // (bot - upper + 1) * (right - left + 1)
    }

    public void dfs(char[][] image, int x, int y, int[] res) {
        int column = image.length;
        int row = image[0].length;
        if (x < 0 || x > column-1 || y < 0 || y > row-1) return; 
        if (image[x][y] == '0') return;         //based on this: The black pixels are connected
        image[x][y] = '0';          // once visit, set to 0         

        if (x < res[0]) res[0] = x; // update upper bound
        if (x > res[1]) res[1] = x; // update bottom bound
        if (y < res[2]) res[2] = y; // update left bound
        if (y > res[3]) res[3] = y; // update right bound

        dfs(image, x+1, y, res);
        dfs(image, x, y+1, res);
        dfs(image, x-1, y, res);
        dfs(image, x, y-1, res);
    }
}
