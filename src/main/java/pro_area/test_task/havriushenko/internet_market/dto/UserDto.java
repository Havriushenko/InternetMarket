package pro_area.test_task.havriushenko.internet_market.dto;

import pro_area.test_task.havriushenko.internet_market.model.Role;

import java.util.Set;

public class UserDto {

    private int id;
    private String name;
    private String password;
    private String surname;
    private String email;
    private Set<Role> roles;

    public UserDto() {
    }

    public UserDto(String name, String password, String surname, String email, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.surname = surname;
        this.email = email;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRole(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto[" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", roles='" + roles + '\'' +
                "]";
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto user = (UserDto) o;
        return id == user.id && name == user.name;
    }
}
