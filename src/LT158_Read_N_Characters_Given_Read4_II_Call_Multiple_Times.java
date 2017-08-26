/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

String
 */
public class LT158_Read_N_Characters_Given_Read4_II_Call_Multiple_Times {
    /**
     * @param buf
     *            Destination buffer
     * @param n
     *            Maximum number of characters to read
     * @return The number of characters read
     */
    // buffer pointer (buffPtr) and buffer Counter (buffCnt) to store the data received in previous calls. In the while loop, if buffPtr reaches current buffCnt, it will be set as zero to be ready to
    // read new data.

    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];

    public int read(char[] buf, int n) {
	int ptr = 0;
	while (ptr < n) {
	    if (buffPtr == 0) {
		buffCnt = read4(buff); // if buffPtr=0. read new data
	    }
	    if (buffCnt == 0)
		break; // get back nothing
	    while (ptr < n && buffPtr < buffCnt) {
		buf[ptr++] = buff[buffPtr++]; // buffPtr save the buffer index in previous calls.
	    }
	    if (buffPtr >= buffCnt)
		buffPtr = 0; // finish reading all the remaining data from previous call
	}
	return ptr;
    }

    public int read4(char[] buf) {
	return 0;
    }
}
