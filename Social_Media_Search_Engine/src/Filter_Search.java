import java.io.*;
import java.util.*;

public class Filter_Search {
	public static void searchOnFilter(List<User> users) {
		System.out.println();
		System.out.println("Choose a filter: ");
		System.out.println("1. Hashtag");
		System.out.println("2. Hobby");
		System.out.println("3. Location");
		
		Scanner scn = new Scanner(System.in);
		int filter = scn.nextInt();
		
		switch(filter) {
			case 1:
				System.out.println("Enter name of hashtag to filter: ");
				scn.nextLine();
        		String userInput = scn.nextLine();
        		
        		if(Start.categories.get("hashtags").containsKey(userInput) == false) {
        			System.out.println("Sorry no result found!");
        			return;
        		}
        		
        		Set<Integer> filteredUsers = Start.categories.get("hashtags").get(userInput);
        		printData(filteredUsers, users);
				break;
			case 2:
				System.out.println("Enter name of hobby to filter: ");
				scn.nextLine();
        		userInput = scn.nextLine();
        		
        		if(Start.categories.get("hobbies").containsKey(userInput) == false) {
        			System.out.println("Sorry no result found!");
        			return;
        		}
        		
        		filteredUsers = Start.categories.get("hobbies").get(userInput);
        		printData(filteredUsers, users);
				break;
			case 3:
				System.out.println("Enter name of location to filter: ");
				scn.nextLine();
        		userInput = scn.nextLine();
        		
        		if(Start.categories.get("locations").containsKey(userInput) == false) {
        			System.out.println("Sorry no result found!");
        			return;
        		}
        		
        		filteredUsers = Start.categories.get("locations").get(userInput);
        		printData(filteredUsers, users);
				break;
			default:
				System.out.println("Invalid input");
		}
	}
	
	public static void printData(Set<Integer> set, List<User> users) {
		for(int value : set) {
			System.out.println("\t\t" + users.get(value).getFullName() + " (userid: " + users.get(value).getId() + ")");
		}
	}
}
