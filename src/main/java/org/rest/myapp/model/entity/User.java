package org.rest.myapp.model.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "{user.name.not-blank}")
    private String username;

    @NotBlank(message = "{password.not-blank}")
    @Size(min = 8, max = 255, message = "{password.size}")
    @Pattern(
            regexp = "^$|^\\S(?:.*\\S)?$",
            message = "Password must not start or end with space"
    )
    private String password;

//    @OneToMany(mappedBy = "user")
//    @JsonBackeference
//    private Set<Item> = new HashSet<>();

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
