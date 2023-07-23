import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Friend_Suggestion {
	public static void suggestFriends(List<User> users, int userid, int countHobbiesToSuggest) {
		Set<Integer> processedUsers = new HashSet<>();
		int count = 0;
		System.out.println("Friend suggestions for " + users.get(userid).getFullName() + " (userid: " + userid + ")");
		
		for(int friend : users.get(userid).getFriends()) {
			for(int friendOfFriend : users.get(friend).getFriends()) {
				if(processedUsers.contains(friendOfFriend)) {
					continue;
				}
				
				processedUsers.add(friendOfFriend);
				List<String> commonHobbies = calculateCommonHobbies(users, userid, friendOfFriend);
				
				if(commonHobbies.size() >= countHobbiesToSuggest) {
					count++;
					System.out.println(users.get(friendOfFriend).getFullName() + " (userid: " + friendOfFriend + "), you both like " + commonHobbies);
				}
			}
		}
		
		if(count == 0) {
			System.out.println("No friend suggestion");
		} 
	}
	
	public static List<String> calculateCommonHobbies(List<User> users, int userid, int friendOfFriend) {
		Set<String> user1hobbies = users.get(userid).getHobbies();
		Set<String> user2hobbies = users.get(friendOfFriend).getHobbies();
		
		List<String> commonHobbies = new ArrayList<>();
		
		for(String hobby : user1hobbies) {
			if(user2hobbies.contains(hobby)) {
				commonHobbies.add(hobby);
			}
		}
		
		return commonHobbies;
	}
}
