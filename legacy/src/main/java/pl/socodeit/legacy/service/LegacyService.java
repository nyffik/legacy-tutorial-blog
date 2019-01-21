package pl.socodeit.legacy.service;

import java.util.ArrayList;
import java.util.List;

public class LegacyService {

    private ExternalService externalService;
    private AnotherService anotherService;
    private NextService nextService;

    public String veryImportantLegacyLogic(String a, Integer b, boolean c) {
        if (c) {
            a = externalService.callRemote();
        }

        List<LegacyEntity> l = new ArrayList<>();
        for (int i = 0; i < b; i++) {
            LegacyEntity le = anotherService.getFromDb(a, i);
            l.add(le);
        }

        List<LegacyEntity> l2 = nextService.removeDuplicates(l);

        if (l.size() - l2.size() > 0) {
            return "Duplicates exist for id " + a;
        } else {
            return "No dupliactes found for id" + a;
        }
    }
}
