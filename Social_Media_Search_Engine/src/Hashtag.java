import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

class Hashtag {
    static class Hashtag_Skeleton {
        String name;
        int frequency;

        public Hashtag_Skeleton(String name, int frequency) {
            this.name = name;
            this.frequency = frequency;
        }
    }

    static class HashtagComparator implements Comparator<Hashtag_Skeleton> {
        @Override
        public int compare(Hashtag_Skeleton h1, Hashtag_Skeleton h2) {
            // Reverse order so that the hashtag with higher frequency comes first
            return Integer.compare(h2.frequency, h1.frequency);
        }
    }

    public static void findMostFamousHashtag(Map<String, Integer> hashtags, int topN) {
        // Create a priority queue with the custom comparator
        PriorityQueue<Hashtag_Skeleton> priorityQueue = new PriorityQueue<>(new HashtagComparator());

        // Add hashtags to the priority queue
        for (Map.Entry<String, Integer> entry : hashtags.entrySet()) {
            String hashtagName = entry.getKey();
            int frequency = entry.getValue();
            priorityQueue.add(new Hashtag_Skeleton(hashtagName, frequency));
        }

        for(int i=0; i<topN; i++) {
        	Hashtag_Skeleton topElement = priorityQueue.poll();
        	System.out.println("Trending " + (i+1) + ": " + topElement.name + " with " + topElement.frequency + " mentions.");
        }
    }
}
