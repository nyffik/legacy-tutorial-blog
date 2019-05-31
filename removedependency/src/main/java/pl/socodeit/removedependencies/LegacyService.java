package pl.socodeit.removedependencies;

public class LegacyService {


    public Address updateStreetForUser(Long id,String street) {

        User user = DBUtils.getUser(id);

        Address address  = DBUtils.getAddress(user.getAddressId());

        address.setStreet(street);

        return DBUtils.saveAddress(address);

    }

    public Address updateStreetForUserNew(Long id,String street) {

        Configuration configuration = new Configuration();
        LegacyServiceWithoutDependency legacyServiceWithoutDependency = configuration.legacyServiceWithoutDependency();

        return legacyServiceWithoutDependency.updateStreetForUser(id, street);

    }
}
