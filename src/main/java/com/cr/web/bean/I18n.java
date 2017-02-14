package com.cr.web.bean;

import java.io.Serializable;

public class I18n extends MasterData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6938779978108113787L;
    private String code;
    private String value;
    private Language language;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
