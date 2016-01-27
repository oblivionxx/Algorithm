/*
Find the total area covered by two rectilinear rectangles in a 2D plane.
Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.

Math
 */
public class LT223_Rectangle_Area {
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //no overlap 
        int surface1 = (C-A)*(D-B);
        int surface2 = (G-E)*(H-F);
        
        if(C<E || G<A)
            return surface1+surface2;
        
        if(D<F || H<B)
            return surface1+surface2;
            
        //with overlap
        int right = Math.min(C,G);
        int left = Math.max(A,E);
        int top = Math.min(D,H);
        int bottom = Math.max(B,F);
        
        return surface1+surface2-(right-left)*(top-bottom);
    }
}
