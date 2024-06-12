package pe.upc.learningcenter.iam.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.upc.learningcenter.iam.domain.model.valueobjects.Roles;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @Size(max = 20)
    private Roles name;

    public Role(Roles name) {
        this.name = name;
    }

    public static Role getDefaultRole(){
        return new Role(Roles.ROLE_USER);
    }

    public static Role getRoleFromName(String name){
        return new Role(Roles.valueOf(name));
    }
    public static String getStringFromRole(Role role){
        return role.name.name();
    }
}
