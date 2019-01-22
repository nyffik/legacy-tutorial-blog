package pl.socodeit.legacy.service;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class LegacyService {

    private final ExternalService externalService;
    private final AnotherService anotherService;
    private final NextService nextService;

    public String veryImportantLegacyLogic(String id, Integer version, boolean couldCallRemote) {
        id = callRemote(id, couldCallRemote);

        List<LegacyEntity> legacyEntities = findByLegacyEntityAndVersionLessOrEqual(id, version);

        return checkDuplicates(id, legacyEntities);
    }

    private String checkDuplicates(String id, List<LegacyEntity> legacyEntities) {
        List<LegacyEntity> legacyEntitiesWithoutDuplicates = nextService.removeDuplicates(legacyEntities);

        if (doListsHaveDifferentSize(legacyEntities, legacyEntitiesWithoutDuplicates)) {
            return "Duplicates exist for id " + id;
        } else {
            return "No dupliactes found for id " + id;
        }
    }

    private boolean doListsHaveDifferentSize(List<LegacyEntity> legacyEntities, List<LegacyEntity> legacyEntitiesWithoutDuplicates) {
        return legacyEntities.size() - legacyEntitiesWithoutDuplicates.size() > 0;
    }

    private List<LegacyEntity> findByLegacyEntityAndVersionLessOrEqual(String id, Integer version) {
        List<LegacyEntity> legacyEntities = new ArrayList<>();
        for (int i = 0; i < version; i++) {
            LegacyEntity le = anotherService.getFromDb(id, i);
            legacyEntities.add(le);
        }
        return legacyEntities;
    }

    private String callRemote(String id, boolean couldCallRemote) {
        if (!couldCallRemote) {
            return externalService.callRemote();
        }
        return id;
    }
}
