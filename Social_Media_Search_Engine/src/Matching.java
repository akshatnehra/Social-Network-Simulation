import java.util.List;

public class Matching {
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
				StdOut.println("User Found: " + s);
				isFound = true;
			}
	        
			// False indicates that no user was found
			if(isFound == false) {
				System.out.println("No exact match found for: " + name);
			}
			
		}
}
