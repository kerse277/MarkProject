package com.kerse.markproject.model;

import lombok.Getter;
import me.himanshusoni.edittextspinner.EditTextSpinner;

public class ListableObject implements EditTextSpinner.Listable {

    @Getter
    private String name;

    public ListableObject(String name) {
        this.name = name;
    }


    @Override
    public String getLabel() {
        return name;
    }
}