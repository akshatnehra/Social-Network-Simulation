import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User_Operations {
	// Generate user data 
    public static List<User> generateUserData(int countNames) {
    	String fname_path = "./src/resources/first_names.txt";
        String lname_path = "./src/resources/last_names.txt";

        List<String> firstNames = File_Handling.nameGenerator(fname_path);
        List<String> lastNames = File_Handling.nameGenerator(lname_path);
    	
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
            user.getFriends().addAll(friendsSet);
        }
    }
}
