package pl.socodeit.removedependencies;

@RequiredArgsConstructor
public class LegacyServiceWithoutDependency {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public Address updateStreetForUser(Long id,String street) {

        User user = userRepository.getUser(id);

        Address address  = addressRepository.getAddress(user.getAddressId());

        address.setStreet(street);

        return addressRepository.saveAddress(address);

    }
}
