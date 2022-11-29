package ru.dorjik.rest_light.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Component
@NamedEntityGraph(name = "User.roles",
        attributeNodes = @NamedAttributeNode("roles")
)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;


    private String password;


    private String email;


    private int age;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, String email, int age, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public void setRoles(Set<Role> rolesByName) {
        this.roles = rolesByName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
