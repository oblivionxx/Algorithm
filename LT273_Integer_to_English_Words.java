/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Hint:

Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

Math, String
 */
public class LT273_Integer_to_English_Words {
	public String numberToWords(int num) {
        //maximum is 21 billiion
        //Need enumerate [1-19], and [20,30,40,50,60,70,80,90].
        //一个三位数n，百位数表示为n/100，后两位数一起表示为n%100，十位数表示为n%100/10，个位数表示为n%10，然后我们看后两位数是否小于20，小于的话直接从数组中取出单词，如果大于等于20的话，则分别将十位和个位数字的单词从两个数组中取出来。然后再来处理百位上的数字，还要记得加上Hundred。主函数中调用四次这个帮助函数，
        //中间要插入"Thousand", "Million","Billion"到对应的位置，最后check一下末尾是否有空格，把空格都删掉，返回的时候检查下输入是否为0，是的话要返回'Zero'。
        String[] units = {""," Thousand"," Million"," Billion"};
        int i = 0;
        String res="";
        while(num>0) {
            int temp = num%1000;
            if(temp>0) res = convert(temp) + units[i] + (res.length()==0 ?"": " "+res);
            num /= 1000;
            i++;
        }
        return res.isEmpty()? "Zero" : res;
    }
    public String convert(int num){
        String res = "";
        String[] ten = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] hundred = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] twenty = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        if(num>0) {
            int temp = num/100;
            if(temp>0) {
                res += ten[temp] + " Hundred";
            }
            temp = num%100;
            if(temp>=10 && temp<20){
                if(!res.isEmpty()) res +=" ";
                res = res + twenty[temp%10];
                return res;
            }else if(temp>=20){
                temp = temp/10;
                if(!res.isEmpty()) res +=" ";
                res = res + hundred[temp-1];
            }
            temp = num%10;
            if(temp>0) {
                if(!res.isEmpty()) res +=" ";
                res = res + ten[temp];
            }
        }
        return res;
    }
}
