
package main.java.lesson7;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "LocalObservationDateTime",
    "EpochTime",
    "WeatherText",
    "Temperature"
})

public class WeatherResponse {

    @JsonProperty("LocalObservationDateTime")
    private String localObservationDateTime;
    @JsonProperty("WeatherText")
    private String weatherText;
    @JsonProperty("Temperature")
    private Temperature1 temperature;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("LocalObservationDateTime")
    public String getLocalObservationDateTime() {
        return localObservationDateTime;
    }

    @JsonProperty("LocalObservationDateTime")
    public void setLocalObservationDateTime(String localObservationDateTime) {
        this.localObservationDateTime = localObservationDateTime;
    }

    @JsonProperty("WeatherText")
    public String getWeatherText() {
        return weatherText;
    }

    @JsonProperty("WeatherText")
    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    @JsonProperty("Temperature")
    public Temperature1 getTemperature() {
        return temperature;
    }

    @JsonProperty("Temperature")
    public void setTemperature(Temperature1 temperature) {
        this.temperature = temperature;
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
        sb.append(((this.weatherText == null)?"<null>":this.weatherText));
        sb.append(',');
        sb.append("температура ");
        sb.append(((this.temperature == null)?"<null>":this.temperature));
        sb.append(" C");
        return sb.toString();
    }

}
