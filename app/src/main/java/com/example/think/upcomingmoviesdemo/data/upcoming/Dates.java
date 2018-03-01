
package com.example.think.upcomingmoviesdemo.data.upcoming;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Dates {

    @JsonProperty("maximum")
    private String maximum;
    @JsonProperty("minimum")
    private String minimum;

    public Dates () {}

    public Dates(String maximum, String minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }
}
