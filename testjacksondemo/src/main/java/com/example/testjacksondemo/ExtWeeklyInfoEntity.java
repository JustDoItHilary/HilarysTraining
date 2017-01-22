package com.example.testjacksondemo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Hilary on 2016/9/13.
 */
public class ExtWeeklyInfoEntity {

    @JsonProperty(value = "FORMVALUE")
    private String formvalue;
    @JsonProperty(value = "CREATEDATE")
    private String createdate;

    ExtWeeklyInfoEntity() {
    }

    public ExtWeeklyInfoEntity(String s, String s1) {
        this.formvalue=s;
        this.createdate=s1;
    }


    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getFormvalue() {
        return formvalue;
    }

    public void setFormvalue(String formvalue) {
        this.formvalue = formvalue;
    }
}
