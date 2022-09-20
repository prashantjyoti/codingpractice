package practice.morganstanley;

//I/P : Palindrome String, O/P: Return lowest possible one character replaced non palindrome string or "IMPOSSIBLE" if not possible
//https://leetcode.com/problems/break-a-palindrome/
class BreakPalindrome {

    /*
     * Complete the 'breakPalindrome' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING palindromeStr as parameter.
     */

    public static String breakPalindrome(String palindromeStr) {
    
        int[] charcount =  new int[26];
        
        char[] ch = palindromeStr.toCharArray();
        
        for(int i= 0; i< ch.length; i++){
            int count = charcount[ch[i] - 'a'];
            charcount[ch[i] - 'a']  = ++count;
        }
        
        // for(int i= 0; i< charcount.length; i++){
        //     System.out.print(charcount[i]+ " ");
        // }
        
        return computeIfPossible(charcount,palindromeStr);
    }
    
    public static String computeIfPossible(int[] charcount,String palindromeStr){
        
        boolean firstCharFound = false;
        //boolean impossibleFlag = true;
        
        for (int i = 0; i < charcount.length; i++) {
            if(!firstCharFound && charcount[i] != 0){
                firstCharFound = true;
                continue;
            }
            
            if(firstCharFound && charcount[i] >= 2){
                
                return replaceNonPalindromeString(i,palindromeStr);
            }
        }
        
       return "IMPOSSIBLE" ;
    }
    
    public static String replaceNonPalindromeString(int i, String palindromeString){
          char ch = (char)(97 + i);
          char chReplace = 'a';
          
          return palindromeString.replaceFirst(ch+ "", chReplace+"");
    }

}
