import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// User class to represent user data
    public class User {
        private int id;
        private String firstName;
        private String lastName;
        private List<Integer> friends;
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