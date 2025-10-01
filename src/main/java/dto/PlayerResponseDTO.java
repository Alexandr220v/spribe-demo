package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(builder = PlayerResponseDTO.Builder.class)
public class PlayerResponseDTO {
    @JsonProperty("age")
    private final int age;
    @JsonProperty("gender")
    private final String gender;
    @JsonProperty("id")
    private final int id;
    @JsonProperty("login")
    private final String login;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("role")
    private final String role;
    @JsonProperty("screenName")
    private final String screenName;

    private PlayerResponseDTO(Builder builder) {
        this.age = builder.age;
        this.gender = builder.gender;
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.screenName = builder.screenName;
    }


    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }
    @Override
    public String toString() {
        return "Player{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }


    public static class Builder {

        private  int age;
        private  String gender;
        private  int id;
        private  String login;
        private  String password;
        private  String role;

        private  String screenName;

        public static Builder newBuilder() {
            return new Builder();
        }
        @JsonProperty("age")
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        @JsonProperty("gender")
        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }
        @JsonProperty("id")
        public Builder id(int id) {
            this.id = id;
            return this;
        }
        @JsonProperty("login")
        public Builder login(String login) {
            this.login = login;
            return this;
        }
        @JsonProperty("password")
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        @JsonProperty("role")
        public Builder role(String role) {
            this.role = role;
            return this;
        }
        @JsonProperty("screenName")
        public Builder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public PlayerResponseDTO build() {
            return new PlayerResponseDTO(this);
        }
    }
}
