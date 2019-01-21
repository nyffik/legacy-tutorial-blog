package pl.socodeit.legacy.service;

import java.util.List;

public interface NextService {
    List<LegacyEntity> removeDuplicates(List<LegacyEntity> l);
}
