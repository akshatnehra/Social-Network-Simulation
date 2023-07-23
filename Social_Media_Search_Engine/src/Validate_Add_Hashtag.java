import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate_Add_Hashtag {
	public static void validate(String hashtag, String filePath) {
		
		// Validate Hashtag: it should start with "#" and it cannot contain spaces and special symbols after it.
 		if(isValidHashtagFormat(hashtag) == false) {
			System.out.println("Hashtag is not in valid format.");
			return;
		}
		
		String bannedWords[] = {"ban1", "ban2", "ban3"};
		
		for(int i=0; i<bannedWords.length; i++) {
			if(searchString(bannedWords[i], hashtag)) {
				System.out.println("Sorry, you cannot add this hashtag. It is banned!");
				return;
			}
		}
		
		appendToFile(hashtag, filePath);
	}
	
	public static boolean searchString(String str1, String str2) {
	       BoyerMoore boyermoore1 = new BoyerMoore(str1);
	       int offset1 = boyermoore1.search(str2);
	       if(offset1 != str2.length())
	    	   return true;
	       return false;
	}
	
	public static void appendToFile(String text, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        	writer.newLine();
        	writer.write(text);

            System.out.println("Hashtag added successfully.");
        } catch (IOException e) {
            System.err.println("Error adding hashtag to file: " + e.getMessage());
        }
    }
	
	public static boolean isValidHashtagFormat(String input) {
        // Regex pattern to check if the string starts with "#" and contains no spaces or special characters inside it
        String regex = "^#[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        return matcher.matches();
    }
}
