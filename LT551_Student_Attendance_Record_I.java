/*
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False

String
 */
public class LT551_Student_Attendance_Record_I {
    public boolean checkRecord(String s) {
        //count 'A'>1 or contains 'LLL' return false;
        int count = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='A') count++;
        }
        if(count>1||s.contains("LLL")) return false;
        return true;
    }
}
