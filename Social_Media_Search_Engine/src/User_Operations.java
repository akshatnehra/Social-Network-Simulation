import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User_Operations {
	// Generate user data 
    public static List<User> generateUserData(int countNames) {
    	String fname_path = "./src/resources/first_names.txt";
        String lname_path = "./src/resources/last_names.txt";

        List<String> firstNames = File_Handling.fileReader(fname_path);
        List<String> lastNames = File_Handling.fileReader(lname_path);
    	
    	List<User> users = new ArrayList<>();
        
        for (int i = 0; i < countNames; i++) {
            String firstName = firstNames.get((int) (Math.random() * firstNames.size()));
            String lastName = lastNames.get((int) (Math.random() * lastNames.size()));
            users.add(new User(firstName, lastName, i)); // Assign ID based on the index
        }
        return users;
    }
    
    // Assign random friends to each user
    public static void assignFriends(List<User> users, int maxFriendsLimit) {
        for (User user : users) {
        	int numOfFriends = (int) (Math.random() * Math.min(maxFriendsLimit+1, users.size()));
        	
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
    
    public static void assignHobbies(List<User> users, List<String> hobbies, int maxHobbyLimit) {
        for (User user : users) {
            // Generate a random number of hobbies to assign to the user
            int numOfHobbies = (int) (Math.random() * (maxHobbyLimit+1));

            // Set in order to prevent addition of duplicate hobbies
            Set<String> userHobbiesSet = new HashSet<>();
            while (userHobbiesSet.size() < numOfHobbies) {
                int randomIndex = (int) (Math.random() * hobbies.size());
                String hobby = hobbies.get(randomIndex);
                userHobbiesSet.add(hobby);
            }

            // Add all hobbies in set
            user.setHobbies(userHobbiesSet);
        }
    }
    
    // Assign random hashtags to each user
    public static void assignHashtags(List<User> users, List<String> hashtags, int maxHashtagLimit) {
        for (User user : users) {
            int numOfHashtags = (int) (Math.random() * (maxHashtagLimit + 1));

            // Set in order to prevent addition of duplicate hashtags
            Set<String> hashtagsSet = new HashSet<>();
            while (hashtagsSet.size() < numOfHashtags) {
                int randomIndex = (int) (Math.random() * hashtags.size());
                String hashtag = hashtags.get(randomIndex);
                hashtagsSet.add(hashtag);
                
                if(Start.hashmapCount.containsKey(hashtag)) {
                	Start.hashmapCount.put(hashtag, Start.hashmapCount.get(hashtag) + 1);
                } else {
                	Start.hashmapCount.put(hashtag, 1);
                }
            }

            // Add all hashtags in set
            user.setHashtags(hashtagsSet);
        }
    }
    
    // Assign random location to each user
    public static void assignLocationtoAllUsers(List<User> users, List<String> locations) {
        for (User user : users) {
        	// random index of location
            int randomIndex = (int) (Math.random() * locations.size());
            
            // Set random location
            user.setLocation(locations.get(randomIndex));
        }
    }
}
