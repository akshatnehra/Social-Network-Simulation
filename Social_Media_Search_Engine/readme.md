
# Social Network Simulation - Java

This Social Network Simulation project is a command-line application that simulates a social network environment. It allows users to create profiles, connect with friends, share hobbies, use hashtags, and perform various operations on the network. This simulation is designed to demonstrate key concepts of data structures, file handling, trie, hashing, priority queue, regex, string matching and graph algorithms.




## Features

- **User Creation:** Generate user profiles with random data, including names, friends, hashtags, location and interests.

- **Friendship:** Automatically assign friends to users within a defined maximum friends limit.

- **Hobbies and Hashtags:** Assign random hobbies and hashtags to users within specified limits.

- **Location Assignment:** Assign random locations (cities) to users.

- **Search Functionality:** Search for users with autocomplete and exact match options.

- **Mutual Friends:** Find mutual friends between two users.

- **Minimum Friendship Path:** Determine the minimum number of steps needed to become friends between two users.
        
        For example, if the path is: User A -> Mutual Friend 1 -> Mutual Friend 2 -> User B, it means that User A and User B can become friends in two steps.

- **Friend Suggestions:** Suggest friends based on common hobbies.

- **Twin Friend Finder:** Find users with similar hobbies.

- **Top Hashtags:** Find the most popular hashtags used in the network.

- **Filter-Based Search:** Search for users based on filters.

- **Hashtag Addition:** Add new hashtags to the network.

- **User and Friends Sorting:** Sort users and friends based on names.

- **Data Persistence:** Write user data to a file for future use.






## File Structure

The project's file structure is organized as follows:

- **resources/:** Directory containing data files.
- **user_data.txt:** User data storage.
- **hobbies.txt:** Hobbies data storage.
- **hash_tags.txt:** Hashtags data storage.
- **cities.txt:** Cities data storage.
- Various Java source files for different functionalities.


## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/).
