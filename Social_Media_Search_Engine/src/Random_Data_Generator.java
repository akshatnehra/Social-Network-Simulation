import java.io.*;
import java.util.*;

public class Random_Data_Generator {

	public static void main(String[] args) {
		
		// All the variable Declarations
		int numberOfUsers, choice;
		String userDetailsFilePath = "./src/resources/user_data.txt";
		
		// Asking user for number of users to create
		Scanner scn = new Scanner(System.in);
		System.out.print("How many users would you like to create: ");
		numberOfUsers = scn.nextInt();
        
		// Generate User Data
        List<User> users = generateUserData(numberOfUsers);
        System.out.println(numberOfUsers + " have been created successfully!");

        // Assign friends to each user
        assignFriends(users);
        System.out.println("Assigned random friends to all the users successfully!");

        // Write all users in a file
        writeUserDataToFile(users, userDetailsFilePath);
        System.out.println("All the user data has been successfully written in the file: " + userDetailsFilePath);
        
        // Insert all users in Trie to search
        TrieST<Integer> trie = prepareTrie(users);
        
        boolean whileCondition = true;
        // Infinite loop that will stop on certain input by the user
        while(whileCondition) {
        	System.out.println();
            System.out.println("Enter your choice, which operation would you like to perform: ");
            System.out.println("1. Search User (Autocomplete)");
            System.out.println("2. Search User (Exact Match)");
            System.out.println("3. ");
            System.out.println("4. ");
            System.out.println("5. ");
            System.out.println("6. Exit");
            
            choice = scn.nextInt();
            
            switch(choice) {
            	case 1:
            		System.out.println("Enter the username you want to search...");
            		String userInput = scn.nextLine();
            		autoComplete(trie, userInput);
            		break;
            	case 2:
            		System.out.println("Enter the username you want to search...");
            		userInput = scn.nextLine();
            		searchUser(trie, userInput);   
            		break;
            	case 3:
            		break;
            	case 4:
            		break;
            	case 5:
            		break;
            	case 6:
            		whileCondition = false;
            		break;
            	default:
            		System.out.println("Invalid Choice, please enter a valid choice!");
            }
        }    
    }
	
	// Insert usernames in the Trie which will help us in searching users
	public static TrieST<Integer> prepareTrie(List<User> users) {
		TrieST<Integer> st = new TrieST<Integer>();
		for (int i = 0; i < users.size(); i++) {
            st.put(users.get(i).getFullName(), i);
        }
		
		return st;
	}
	
	public static void autoComplete(TrieST<Integer> st, String name){
		 System.out.println("Autocomplete Runnning...");
		
		 for (String s : st.keysWithPrefix(name))
	            StdOut.println(s);
	        StdOut.println();
	}
	
	public static void searchUser(TrieST<Integer> st, String name){
		// We will mark boolean true to indicate that a user found with that name
		boolean isFound = false;
		System.out.println("Finding Exact Match...");
		
		for (String s : st.keysThatMatch(name)) {
			StdOut.println("User Found:" + s);
			isFound = true;
		}
        
		// False indicates that no user was found
		if(isFound == false) {
			System.out.println("No exact match found for: " + name);
		}
		
	}
	
	// Read names from FILES and return List of String
    public static List<String> nameGenerator(String filePath) {
        List<String> namesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                namesList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return namesList;
    }
    
    // Generate user data 
    public static List<User> generateUserData(int countNames) {
    	String fname_path = "./src/resources/first_names.txt";
        String lname_path = "./src/resources/last_names.txt";

        List<String> firstNames = nameGenerator(fname_path);
        List<String> lastNames = nameGenerator(lname_path);
    	
    	List<User> users = new ArrayList<>();
        
        for (int i = 0; i < countNames; i++) {
            String firstName = firstNames.get((int) (Math.random() * firstNames.size()));
            String lastName = lastNames.get((int) (Math.random() * lastNames.size()));
            users.add(new User(firstName, lastName, i)); // Assign ID based on the index
        }
        return users;
    }
    
    // Assign random friends to each user
    public static void assignFriends(List<User> users) {
        for (User user : users) {
        	int numOfFriends = (int) (Math.random() * Math.min(1001, users.size()));
        	
        	// Set in order to prevent addition of duplicate friends
            Set<Integer> friendsSet = new HashSet<>();
            while (friendsSet.size() < numOfFriends-1) {
                int friendId = (int) (Math.random() * users.size());
                
                // Make sure the user is not a friend of itself
                if (friendId != user.getId()) {
                    friendsSet.add(friendId);
                }
            }
            
            // Add all friends in set into its friendList
            user.friends.addAll(friendsSet);
        }
    }

    // User class to represent user data
    static class User {
        private int id;
        private String firstName;
        private String lastName;
        private List<Integer> friends;

        public User(String firstName, String lastName, int id) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.friends = new ArrayList<>();
        }
        
        // Method to return user's full name
        public String getFullName() {
            return firstName + " " + lastName;
        }

        public int getId() {
            return id;
        }

        public void addFriend(int friendId) {
            friends.add(friendId);
        }

        // Override toString() to print user data
        @Override
        public String toString() {
            return "User ID: " + id + ", Name: " + firstName + " " + lastName + ", Friends: " + friends.size();
        }
        
        // Method to get formatted friends' data (ID and full name)
        public String getFormattedFriends(List<User> users) {
            StringBuilder sb = new StringBuilder();
            for (Integer friendId : friends) {
                User friend = users.get(friendId);
                sb.append("\t\tUser ID: ").append(friendId).append(", Name: ").append(friend.getFullName()).append("\n");
            }
            return sb.toString();
        }
        
        // Method to get a formatted string with friends data
        public String toStringWithFriends(List<User> users) {
            return toString() + "\nFriends:\n" + getFormattedFriends(users);
        }
    }
    
    public static void writeUserDataToFile(List<User> users, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.toStringWithFriends(users));
                writer.newLine();
            }
            System.out.println("User data successfully written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
