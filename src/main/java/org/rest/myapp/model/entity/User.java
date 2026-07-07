package org.rest.myapp.model.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "{user.name.not-blank}")
    private String username;

    @NotBlank(message = "{password.not-blank}")
    @Size(min = 8, max = 255)
    private String password;

//    @OneToMany(mappedBy = "user")
//    @JsonBackeference
//    private Set<Item> = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<Item> getItems() {
//        return Collections.unmodifiableSet(items);
//    }

//    public void addItem(Item item) {
//        this.items.add(item);
//        item.setUser(this);
//    }
//
//    public void removeItem(Item item) {
//        this.items.remove(item);
//        item.setUser(null);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId() != null && getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
