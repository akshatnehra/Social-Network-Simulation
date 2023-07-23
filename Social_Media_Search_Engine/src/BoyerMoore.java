//package textprocessing;

/***************************************************************
 *  Compilation:  javac BoyerMoore.java
 *  Execution:    java BoyerMoore pattern text
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  bad-character rule part of the Boyer-Moore algorithm.
 *  (does not implement the strong good suffix rule)
 *
 *  % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:               abracadabra
 *
 *  % java BoyerMoore rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:         rab
 *
 *  % java BoyerMoore bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:                                   bcara
 *
 *  % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java BoyerMoore abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ***************************************************************/

public class BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    // pattern provided as a string
    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    // pattern provided as a character array
    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    // return offset of first match; N if no match
    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }


    // return offset of first match; N if no match
    public int search(char[] text) {
        int M = pattern.length;
        int N = text.length;
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }
    
    public static String generateRandomString(int length) {
		
		
		// Modifying StringBuilder is less costly, so we are using it instead of String
		
		StringBuilder str = new StringBuilder();
		for(int j=0; j<length; j++) {
			// Generate random character and append it to StringBuilder
			
			int isCapital = random_Number_Generator(0,1);
			if(isCapital == 1) {
				str.append((char)('A'+random_Number_Generator(0,25)) + "");
			} else {
				str.append((char)('a'+random_Number_Generator(0,25)) + "");
			}
		}
		return str.toString();
	
}

	public static int random_Number_Generator(int range_min, int range_max) {
		// Generate random number from 0 to 1 (decimal)
		// Multiply it with TOTAL NUMBERS in the range
			// if range is from 100 to 120, there are 21 number
		// It will generate a random number between 0 to 21
		// Adding minimum value will give a random number in desired range
		return (int)(Math.random() * (range_max - range_min + 1) + range_min);
	}

    public static void searchString(String str1, String str2) {
	       BoyerMoore boyermoore1 = new BoyerMoore(str1);
	       int offset1 = boyermoore1.search(str2);
	       if(offset1 != 10000)
	    	   StdOut.println(str1 + " at pos " + offset1);
	}

    // test client
    public static void main(String[] args) {
    	
    }
}