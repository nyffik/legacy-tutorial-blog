package pl.socodeit.legacy.service;

import lombok.Data;

@Data
public class LegacyEntity {

    private String id;
    private Integer version;
    private String content;
}
