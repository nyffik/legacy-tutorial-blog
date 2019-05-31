package pl.socodeit.removedependencies;

public class AddressRepository {
    public Address getAddress(Long addressId) {
      return  DBUtils.getAddress(addressId);
    }

    public Address saveAddress(Address address) {
        return DBUtils.saveAddress(address);
    }
}
