import java.util.*;


public class Friends {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public Friends() {
        graph = new HashMap<>();
    }

    // Add a user and their friends to the graph
    public static void addUser(int userId, List<Integer> friends) {
        graph.put(userId, friends);
        for (int friend : friends) {
            if (!graph.containsKey(friend)) {
                graph.put(friend, new ArrayList<>());
            }
            graph.get(friend).add(userId);
        }
    }

    // Check if two users are friends
    public static boolean areFriends(int user1, int user2) {
        return graph.containsKey(user1) && graph.get(user1).contains(user2);
    }

    // Find mutual friends between two users
    public static List<Integer> findMutualFriends(int user1, int user2) {
        List<Integer> mutualFriends = new ArrayList<>();
        if (!graph.containsKey(user1) || !graph.containsKey(user2)) {
            return mutualFriends;
        }

        for (int friend : graph.get(user1)) {
            if (graph.get(user2).contains(friend)) {
                mutualFriends.add(friend);
            }
        }
        return mutualFriends;
    }

    // Find the distance between two users
    public static List<Integer> findDistance(int user1, int user2) {
    	
    	// Users not found in the graph
        if (!graph.containsKey(user1) || !graph.containsKey(user2)) {
        	
        	if(!graph.containsKey(user1))
        		System.out.println(user1 + " is not on our Social Media");
        	if(!graph.containsKey(user2))
        		System.out.println(user2 + " is not on our Social Media");
            return new ArrayList<>(); 
        }
        
        // We are creating a queue using this LinkedList
        LinkedList<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        queue.add(user1);
        visited.add(user1);
        distance.put(user1, 0);
        parent.put(user1, -1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == user2) {
                return reconstructPath(parent, user1, user2);
            }

            for (int friend : graph.get(current)) {
                if (!visited.contains(friend)) {
                    queue.add(friend);
                    visited.add(friend);
                    distance.put(friend, distance.get(current) + 1);
                    parent.put(friend, current);
                }
            }
        }
        
        // There is no path to connect both users
        System.out.println("Users cannot be connected via other people (NO PATH)");
        return new ArrayList<>();
    }
    
    // Method used to reconstruct the path from user1 to user2
    private static List<Integer> reconstructPath(Map<Integer, Integer> parent, int user1, int user2) {
        List<Integer> path = new ArrayList<>();
        int current = user2;
        while (current != -1) {
            path.add(0, current);
            current = parent.get(current);
        }
        return path;
    }

    // Check if two users are in the same connected circle
    public static boolean areInConnectedCircle(int user1, int user2) {
        return findDistance(user1, user2).size() > 0;
    }
}
