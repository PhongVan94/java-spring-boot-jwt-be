package phongvan.javaspringbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "_group_role",
            joinColumns = @JoinColumn(name = "_group_id"),
            inverseJoinColumns = @JoinColumn(name = "_role_id")
    )
    private Collection<Role> roles;

}
