/*
 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"

Math
 */
public class LT640_Solve_the_Equation {
    public String solveEquation(String equation) {
        int[] res = evaluateExpression(equation.split("=")[0]),             //left equation
        res2 = evaluateExpression(equation.split("=")[1]);                  //right equation
        res[0] -= res2[0];                                                  //move x to left
        res[1] = res2[1] - res[1];                                          //move number to right
        if (res[0] == 0 && res[1] == 0) return "Infinite solutions";
        if (res[0] == 0) return "No solution";
        return "x=" + res[1]/res[0];
    }  

    public int[] evaluateExpression(String exp) {
        String[] tokens = exp.split("(?=[-+])");                    //split by signs. but keep signs
        int[] res =  new int[2];                                    //res[0] store the x. res[1] store for number
        for (String token : tokens) {
            if (token.equals("+x") || token.equals("x")) res[0] += 1;
            else if (token.equals("-x")) res[0] -= 1;
            else if (token.contains("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else res[1] += Integer.parseInt(token);
        }
        return res;
    }
}
