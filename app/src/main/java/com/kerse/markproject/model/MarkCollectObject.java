package com.kerse.markproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class MarkCollectObject {

    @Getter
    @Setter
    private String personToken;

    @Setter
    @Getter
    private String MarkID;

    @Getter
    @Setter
    private String longitude;

    @Getter
    @Setter
    private String latitude;

}
