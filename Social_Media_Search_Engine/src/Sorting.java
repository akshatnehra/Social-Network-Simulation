import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {
	// Sorting based on Names of users
    public static void sortUsersOnNames(List<User> users) {
    	Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
            	return u1.getFullName().compareTo(u2.getFullName());
            }
        });
    }
    
    // Sort friends of a particular user
    public static void sortFriendsOfaUser(List<User> users, int userid){
    	List<Integer> friends = users.get(userid).getFriends();
    	Collections.sort(friends, new Comparator<Integer>() {
            @Override
            public int compare(Integer friendId1, Integer friendId2) {
                User friend1 = users.get(friendId1);
                User friend2 = users.get(friendId2);
                return friend1.getFullName().compareTo(friend2.getFullName());
            }
        });
    }
    
    // Sort friends of all the users
    public static void sortFriendsOfAllUsers(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            sortFriendsOfaUser(users, i);
        }
    }
}
