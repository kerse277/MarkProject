package com.kerse.markproject.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by pc on 12.5.2017.
 */

public class CollectedPersonList {

    @Getter
    @Setter
    private String uniqueID;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Setter
    @Getter
    private String profilePic;

    @Getter
    @Setter
    private int popularPoint;

}
