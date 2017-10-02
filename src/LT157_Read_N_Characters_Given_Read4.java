/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
Note:
The read function will only be called once for each test case.

String
 */
public class LT157_Read_N_Characters_Given_Read4 {
    /*
     * The read4 API is defined in the parent class Reader4. int read4(char[] buf);
     */

    /**
     * @param buf
     *            Destination buffer
     * @param n
     *            Maximum number of characters to read
     * @return The number of characters read
     */
    public int read(char[] buf, int n) {
	char[] read = new char[4];

	boolean eof = false; // end of text. which means text length<n
	int res = 0; // final result
	int bytes = 0; // how many byte is needed exactly.

	while (!eof && res < n) {
	    int size = read4(read); // 1. text>n (stop by res<n). 2. text<n.(will have size<4, stop by eof)
	    eof = size < 4;

	    bytes = Math.min(n - res, size); // if size=4. bytes=4. if n-res<4. text may have more than n letters. but n+1, n+2...is not necessary.
	    for (int i = 0; i < bytes; i++) {
		buf[res++] = read[i]; // res in the end = res+bytes.
	    }
	}

	return res;
    }

    public int read4(char[] buf) {
	return 0;
    }
	
    public int read2(char[] buf, int n) {
        for(int i = 0; i < n; i += 4){
            char[] tmp = new char[4];
            // 将数据读入临时数组
            int len = read4(tmp);
            // 将临时数组拷贝至buf数组，这里拷贝的长度是本次读到的个数和剩余所需个数中较小的
            System.arraycopy(tmp, 0, buf, i, Math.min(len, n - i));
            // 如果读不满4个，说明已经读完了，返回总所需长度和目前已经读到的长度的较小的
            if(len < 4) return Math.min(i + len, n);
        }
        // 如果循环内没有返回，说明读取的字符是4的倍数
        return n;
    }
}
