package ua.alex.railway.tickets.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    public long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleType role;
    private List<Ticket> tickets = new ArrayList<>();

    public User() {
    }

    public User(String email, RoleType role) {
        this.email = email;
        this.role = role;
    }

    public User(int id, String firstName, String lastName, String email, String password, RoleType role, List<Ticket> tickets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tickets = tickets;
    }

    public static UserBuilder userBuilder() {
        return new User().new UserBuilder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", tickets=" + tickets +
                '}';
    }

    public class UserBuilder {

        private UserBuilder() {
        }

        public UserBuilder withId(Long id) {
            User.this.id = id;
            return this;
        }

        public UserBuilder withEmail(String email) {
            User.this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public UserBuilder withRole(RoleType role) {
            User.this.role = role;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
