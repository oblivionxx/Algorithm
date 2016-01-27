/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

String
 */
public class LT158_Read_N_Characters_Given_Read4_II_Call_Multiple_Times {
	private int offSet = 0;
    private int remaining = 0;		//sth more.
    private boolean eof = false;
    private char[] buffer = new char[4];

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int res = 0;
        while (res < n && (remaining != 0 || !eof)) {
            int readSize = 0;
            if(remaining != 0){
                readSize = remaining;
            }else{
                offSet = 0;
                readSize = read4(buffer);
                if (readSize != 4) {
                	eof = true;
                }
            }
            int length = Math.min(n - res, readSize);
            for (int i= offSet; i<offSet + length; i++) {
                buf[res++] = buffer[i];
            }
            remaining = readSize - length;
            if (remaining != 0) {
                offSet += length;
            }
        }
        return res;
    }
    
    public int read4(char[] buf){
    	return 0;
    }
}
