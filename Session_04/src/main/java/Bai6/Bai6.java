package Bai6;

import java.time.LocalDate;
import java.util.List;

public class Bai6 {
    public static class User {
        private String id;
        private String email;
        private LocalDate dob;

        public User(String id, String email, LocalDate dob) {
            this.id = id;
            this.email = email;
            this.dob = dob;
        }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public LocalDate getDob() { return dob; }
        public void setDob(LocalDate dob) { this.dob = dob; }
        public String getId() { return id; }
    }

    public static class UserProfile {
        String newEmail;
        LocalDate newDob;

        public UserProfile(String newEmail, LocalDate newDob) {
            this.newEmail = newEmail;
            this.newDob = newDob;
        }
    }

    public static User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {
        if (newProfile.newDob.isAfter(LocalDate.now())) {
            return null;
        }

        if (allUsers != null) {
            for (User user : allUsers) {
                if (user.getEmail().equalsIgnoreCase(newProfile.newEmail) &&
                        !user.getId().equals(existingUser.getId())) {
                    return null;
                }
            }
        }

        existingUser.setEmail(newProfile.newEmail);
        existingUser.setDob(newProfile.newDob);

        return existingUser;
    }
}