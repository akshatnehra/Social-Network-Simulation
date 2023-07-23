import java.io.*;
import java.util.*;

public class Start {
	
	static HashMap<String, Integer> hashmapCount = new HashMap<>();
	static HashMap<String, HashMap<String, Set<Integer>>> categories = new HashMap<>();

	public static void main(String[] args) {
		// All the variable Declarations
		int numberOfUsers, choice;
		String userDetailsFilePath = "./src/resources/user_data.txt";
		String userHobbiesFilePath = "./src/resources/hobbies.txt";
		String userHashtagsFilePath = "./src/resources/hash_tags.txt";
		String userLocationsFilePath = "./src/resources/cities.txt";
		int maxFriendsLimit = 50;
		int maxHobbiesLimit = 10;
		int maxHashtagsLimit = 10;
		int minCommonHobbyToSuggestFriend = 3;
		
		// Initializing HashMaps
		categories.put("hobbies", new HashMap<>());
		categories.put("hashtags", new HashMap<>());
		categories.put("locations", new HashMap<>());
		
		// Asking user for number of users to create
		Scanner scn = new Scanner(System.in);
		System.out.print("How many users would you like to create: ");
		numberOfUsers = scn.nextInt();
        
		// Generate User Data
        List<User> users = User_Operations.generateUserData(numberOfUsers);
        System.out.println(numberOfUsers + " have been created successfully!");
        
        List<String> hobbies = File_Handling.fileReader(userHobbiesFilePath);
        List<String> hashtags = File_Handling.fileReader(userHashtagsFilePath);
        List<String> locations = File_Handling.fileReader(userLocationsFilePath);

        // Assign friends to each user (maxFriendsLimit inclusive)
        User_Operations.assignFriends(users, maxFriendsLimit);
        System.out.println("Assigned random friends to all the users successfully!");
        
        // Assign Hobbies to each user (maxHobbiesLimit inclusive)
        User_Operations.assignHobbies(users, hobbies, maxHobbiesLimit);
        System.out.println("Assigned random hobbies to all the users successfully!");
        
        // Assign Hashtags to each user (maxHashtagLimit inclusive)
        User_Operations.assignHashtags(users, hashtags, maxHashtagsLimit);
        System.out.println("Assigned random hashtags to all the users successfully!");
        
        // Assign a random location to each user 
        User_Operations.assignLocationtoAllUsers(users, locations);
        System.out.println("Assigned a random location to all the users successfully!");
        
        // Insert all users in Trie to search
        TrieST<Integer> trie = Matching.prepareTrie(users);
        
        // Insert all users in the GRAPH 
        for(User user : users) {
        	Friends.addUser(user.getId(), user.getFriends());
        }
        
        boolean whileCondition = true;
        // Infinite loop that will stop on certain input (14) by the user
        while(whileCondition) {
        	System.out.println();
            System.out.println("Enter your choice, which operation would you like to perform: ");
            System.out.println("1. Search User (Autocomplete)");
            System.out.println("2. Search User (Exact Match)");
            System.out.println("3. Find mutual friends of a user");
            System.out.println("4. Minimum friends(steps) to make a user friend");
            System.out.println("5. Friend Suggestion");
            System.out.println("6. Find Twin Friend of a user");
            System.out.println("7. Top Hashtags");
            System.out.println("8. Search based on filters");
            System.out.println("9. Add Hashtag");
            System.out.println("10. Sort users");
            System.out.println("11. Sort friends of a particular user");
            System.out.println("12. Sort friends of all users");
            System.out.println("13. Write current data to file");
            System.out.println("14. Exit");
            
            choice = scn.nextInt();
            
            switch(choice) {
            	case 1:
            		System.out.println("Enter the username you want to search...");
            		scn.nextLine();
            		String userInput = scn.nextLine();
            		Matching.autoComplete(trie, userInput);
            		break;
            	case 2:
            		System.out.println("Enter the username you want to search...");
            		scn.nextLine();
            		userInput = scn.nextLine();
            		Matching.searchUser(trie, userInput);   
            		break;
            	case 3:
            		System.out.println("Enter the userid of first user...");
            		int user1 = scn.nextInt();
            		System.out.println("Enter the userid of second user...");
            		int user2 = scn.nextInt();
            		
            		List<Integer> mutualFriends = Friends.findMutualFriends(user1, user2);
            		if(mutualFriends.size() <= 0) {
            			System.out.println("No mutual friends for " + user1 + " and " + user2);
            		}
            		
            		System.out.println("Users have " + mutualFriends.size() + " mutual friends.");
            		for(int user : mutualFriends) {
            			System.out.println("\t\tName: " + users.get(user).getFullName() + ", userid: " + user);
            		}
            		break;
            	case 4:
            		System.out.println("Enter the userid of first user...");
            		user1 = scn.nextInt();
            		System.out.println("Enter the userid of second user...");
            		user2 = scn.nextInt();
            		
            		List<Integer> path = Friends.findDistance(user1, user2);
            		
            		if(path.size() == 2) {
            			System.out.println("They are already friends!");
            		}
            		
            		if(path.size() > 2) {
            			System.out.println("They can become in " + (path.size()-2) + " steps.");
            		}
            		
            		for(int node : path) {
            			System.out.print("\t\tName: " + users.get(node).getFullName() + ", userid: " + node + " ----> ");
            		}
            		System.out.println();
            		break;
            	case 5:
            		System.out.println("Enter userid of the users to get their friend suggestions: ");
            		int userid = scn.nextInt();
            		Friend_Suggestion.suggestFriends(users, userid, minCommonHobbyToSuggestFriend);
            		break;
            	case 6:
            		System.out.println("Enter userid whose twin friends you would like to find: ");
            		userid = scn.nextInt();
            		Twin_Friend.findTwinFriend(userid, users);
            		break;
            	case 7:
            		System.out.println("How many top hashtags would you like to see? ");
            		int topN = scn.nextInt();
            		Hashtag.findMostFamousHashtag(hashmapCount, topN);
            		break;
            	case 8:
            		Filter_Search.searchOnFilter(users);
            		break;
            	case 9:
            		System.out.println("Enter the hashtag you want to add...");
            		scn.nextLine();
            		userInput = scn.nextLine();
            		Validate_Add_Hashtag.validate(userInput, userHashtagsFilePath);
            		break;
            	case 10:
            		Sorting.sortUsersOnNames(users);
            		System.out.println("Users are now sorted as per their name.");
            		break;
            	case 11:
            		System.out.print("Enter userid of the user whose friends you would like to sort: ");
            		userid = scn.nextInt();
            		Sorting.sortFriendsOfaUser(users, userid);
            		System.out.println("Friends of user " + users.get(userid).getFullName() + "(userid: " + users.get(userid).getId() + ") are now sorted as per their name.");
            		break;
            	case 12:
            		Sorting.sortFriendsOfAllUsers(users);
            		System.out.println("Friends of all the users are now sorted as per their name.");
            		break;
            	case 13:
                    File_Handling.writeUserDataToFile(users, userDetailsFilePath);
                    System.out.println("All the user data has been successfully written in the file: " + userDetailsFilePath);
            		break;
            	case 14:
            		return;
            	default:
            		System.out.println("Invalid Choice, please enter a valid choice!");
            }
        }    
    }
    
    
}
