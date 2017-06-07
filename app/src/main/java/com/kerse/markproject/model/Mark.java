package com.kerse.markproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Mark {

    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private String uniqueID;

    @Getter
    @Setter
    private String longitude;

    @Getter
    @Setter
    private String latitude;

    @Getter
    @Setter
    private String placeName;

    @Getter
    @Setter
    private String placeType;

    @Getter
    @Setter
    private boolean live;

}
