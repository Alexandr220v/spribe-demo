package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Gender;
import enums.Role;
import java.util.Objects;
import java.util.stream.Stream;

public class PlayerRequestDTO {

    @JsonProperty("age")
    private final int age;
    @JsonProperty("gender")
    private final Gender gender;
    @JsonProperty("login")
    private final String login;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("role")
    private final Role role;
    @JsonProperty("screenName")
    private final String screenName;

    private PlayerRequestDTO(Builder builder) {
        this.age = builder.age;
        this.gender = builder.gender;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.screenName = builder.screenName;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    public boolean isValid() {
        return Stream.of(gender, login, password, role, screenName)
                .noneMatch(Objects::isNull) && age > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRequestDTO playerRequestDTO = (PlayerRequestDTO) o;
        return age == playerRequestDTO.age && Objects.equals(gender, playerRequestDTO.gender) && Objects.equals(login, playerRequestDTO.login) && Objects.equals(password, playerRequestDTO.password) && Objects.equals(role, playerRequestDTO.role) && Objects.equals(screenName, playerRequestDTO.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, login, password, role, screenName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    public static class Builder {
        private int age;
        private Gender gender;
        private String login;
        private String password;
        private Role role;
        private String screenName;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
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

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public PlayerRequestDTO build() {
            if (this.login == null || this.password == null || this.age == 0) {
                throw new IllegalStateException("Required fields (login, password, age) must be set.");
            }
            return new PlayerRequestDTO(this);
        }
    }


}
