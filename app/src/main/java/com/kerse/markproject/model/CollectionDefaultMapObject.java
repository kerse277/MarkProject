package com.kerse.markproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by KAPLAN on 14.06.2017.
 */
@Accessors(chain = true)
public class CollectionDefaultMapObject {
    @Getter
    @Setter
    private String markDesc;

    @Getter
    @Setter
    private int markpath;

    @Getter
    @Setter
    private int alpha;
}
