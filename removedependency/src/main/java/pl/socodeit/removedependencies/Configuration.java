package pl.socodeit.removedependencies;

public class Configuration {

    public LegacyServiceWithoutDependency legacyServiceWithoutDependency() {
        return new LegacyServiceWithoutDependency(new UserRepository(), new AddressRepository());
    }
}
