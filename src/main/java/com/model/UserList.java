import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public User createAccount(String displayName, String username, String email, String password) {
        return null;
    }

    public User authenticate(String usernameOrEmail, String password) {
        return null;
    }

    public User getById(UUID userId) {
        return null;
    }

    public User getByUsername(String username) {
        return null;
    }

    public ArrayList<User> getAll() {
        return users;
    }

    public boolean save() {
        return false;
    }
}
