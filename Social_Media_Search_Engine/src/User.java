import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


	// User class to represent user data
    public class User {
        private int id;
        private String firstName;
        private String lastName;
        private List<Integer> friends;
        private Set<String> hobbies;
        private Set<String> hashtags;
        private String location;
        
        public Set<String> getHashtags() {
			return hashtags;
		}

		public void setHashtags(Set<String> hashtags) {
			this.hashtags = hashtags;
		}

		public List<Integer> getFriends() {
			return friends;
		}

		public void setFriends(List<Integer> friends) {
			this.friends = friends;
		}

        public User(String firstName, String lastName, int id) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.friends = new ArrayList<>();
            this.hobbies = new HashSet<>();
            this.hashtags = new HashSet<>();
            this.location = "";
        }
        
        public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		// Method will return user's full name
        public String getFullName() {
            return firstName + " " + lastName;
        }

        public int getId() {
            return id;
        }

        public void addFriend(int friendId) {
            friends.add(friendId);
        }

        public Set<String> getHobbies() {
        	return hobbies;
        }
        
        public void setHobbies(Set<String> hobbies) {
        	this.hobbies = hobbies;
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
        
        // Print data of all the users
        public String printUsersData(User user) {
        	StringBuilder sb = new StringBuilder();
            sb.append("User ID: " + user.id);
            sb.append("\nName: " + user.getFullName());
            sb.append("\nFriends: " + user.friends.size());
            sb.append("\nHobbies: " + user.hobbies);
            sb.append("\nHashtags: " + user.hashtags);
            sb.append("\nLocation: " + user.location);
            sb.append("\nFriends:\n" + user.getFriends());
            sb.append("\n\n");
            
            return sb.toString();
        }
    }