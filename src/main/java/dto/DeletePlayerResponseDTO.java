package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Objects;


public class DeletePlayerResponseDTO {
    @JsonProperty("body")
    public Object body;
    @JsonProperty("statusCode")
    public String statusCode;
    @JsonProperty("statusCodeValue")
    public Integer statusCodeValue;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusCodeValue() {
        return statusCodeValue;
    }

    public void setStatusCodeValue(Integer statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletePlayerResponseDTO that = (DeletePlayerResponseDTO) o;
        return Objects.equals(body, that.body) && Objects.equals(statusCode, that.statusCode) && Objects.equals(statusCodeValue, that.statusCodeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, statusCode, statusCodeValue);
    }

    @Override
    public String toString() {
        return "DeletePlayerResponseDTO{" +
                "body=" + body +
                ", statusCode='" + statusCode + '\'' +
                ", statusCodeValue=" + statusCodeValue +
                '}';
    }
}
