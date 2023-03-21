import java.util.*;
public class random_string {
	public static void main(String[] args) {
		int length = 6;
		int number_of_words = 20;
		
		for(int i=0; i<number_of_words; i++) {
			StringBuilder str = new StringBuilder();
			for(int j=0; j<length; j++) {
				str.append((char)('a'+random_Number_Generator(0,25)) + "");
			}
			System.out.println(str);
		}
	}
	
	public static int random_Number_Generator(int range_min, int range_max) {
		return (int)(Math.random() * (range_max - range_min + 1) + range_min);
	}
}
