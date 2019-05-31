package pl.socodeit.removedependencies;

public class UserRepository {
    public User getUser(Long id) {
        return DBUtils.getUser(id);
    }
}
