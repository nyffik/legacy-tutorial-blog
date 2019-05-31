package pl.socodeit.removedependencies;

public class DBUtils {
    public static User getUser(Long id) {
        return new User();
    }

    public static Address getAddress(Long addressId) {
        return new Address();
    }

    public static Address saveAddress(Address address) {
        return address;
    }
}
