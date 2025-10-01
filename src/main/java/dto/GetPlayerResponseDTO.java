package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class GetPlayerResponseDTO {

    @JsonProperty("age")
    public Integer age;
    @JsonProperty("gender")
    public String gender;
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("login")
    public String login;
    @JsonProperty("password")
    public String password;
    @JsonProperty("role")
    public String role;
    @JsonProperty("screenName")
    public String screenName;


    public GetPlayerResponseDTO() {
    }

    // 2. FIX: Add the Private Constructor used by the Builder's build() method
    private GetPlayerResponseDTO(Builder builder) {
        this.age = builder.age;
        this.gender = builder.gender;
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.screenName = builder.screenName;
    }

    // 3. FIX: Add the Static Factory Method to start the Builder chain
    public static Builder builder() {
        return new Builder();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPlayerResponseDTO that = (GetPlayerResponseDTO) o;
        return Objects.equals(age, that.age) && Objects.equals(gender, that.gender) && Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(role, that.role) && Objects.equals(screenName, that.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, login, password, role, screenName);
    }

    @Override
    public String toString() {
        return "GetPlayerResponseDTO{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    public static class Builder {
        private Integer age;
        private String gender;
        private Integer id;
        private String login;
        private String password;
        private String role;
        private String screenName;


        private Builder() {
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public GetPlayerResponseDTO build() {
            return new GetPlayerResponseDTO(this);
        }

    }
}
