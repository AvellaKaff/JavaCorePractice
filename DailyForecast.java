
package main.java.lesson7.forecast5days;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Date",
    "Temperature",
    "Day"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecast {

    @JsonProperty("Date")
    private String date;
    @JsonProperty("Temperature")
    private Temperature5 temperature;
    @JsonProperty("Day")
    private Day day;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("Temperature")
    public Temperature5 getTemperature() {
        return temperature;
    }

    @JsonProperty("Temperature")
    public void setTemperature(Temperature5 temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("Day")
    public Day getDay() {
        return day;
    }

    @JsonProperty("Day")
    public void setDay(Day day) {
        this.day = day;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("line.separator"));
        sb.append(" на дату ");
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(" ожидается температура: ");
        sb.append(((this.temperature == null)?"<null>":this.temperature));
        sb.append(',');
        sb.append(((this.day == null)?"<null>":this.day));
        return sb.toString();
    }

}
