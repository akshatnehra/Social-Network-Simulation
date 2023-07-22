import java.util.List;
import java.util.Set;

public class Twin_Friend {
	public static void findTwinFriend(int userid, List<User> users) {
		int max = 0;
		User user = users.get(userid);
		
		// Ensuring user has at lease 1 friend
		if(user.getFriends().size() == 0) {
			System.out.println("User has no friend :(");
			return;
		}
		
		// Extract first friend and assign it to twin to ensure twin in initialized
		User twin = users.get(user.getFriends().get(0));
		
		List<Integer> friends = user.getFriends();
		
		for(int friend : friends) {
			int commonHobbies = calculateCommonHobbiess(user, users.get(friend));
			if(max < commonHobbies) {
				max = commonHobbies;
				twin = users.get(friend);	
			}
		}
		
		if(max == 0) {
			System.out.println("User has no twin friend as none of their matched with anyone else :(");
			return;
		}
		
		System.out.println(twin.getFullName() + " (userid: " + twin.getId() + ")" + " is the twin and their " + max + " habits are common");
	
	}
	
	public static int calculateCommonHobbiess(User user1, User user2) {
		int count = 0;
		Set<String> hobbiesUser1 = user1.getHobbies();
		Set<String> hobbiesUser2 = user2.getHobbies();
		
		for(String str : hobbiesUser1) {
			// Common Habit as it is present in both
			if(hobbiesUser2.contains(str)) {
				count++;
			}
		}
		
		return count;
	}
}
