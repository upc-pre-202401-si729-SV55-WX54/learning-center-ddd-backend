package pe.upc.learningcenter.profiles.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.upc.learningcenter.profiles.domain.model.valueobjects.EmailAddress;
import pe.upc.learningcenter.profiles.domain.model.valueobjects.PersonName;
import pe.upc.learningcenter.profiles.domain.model.valueobjects.StreetAddress;
import pe.upc.learningcenter.shared.domain.model.aggregate.AuditableAbstractAggregateRoot;

@Entity
@Getter
@NoArgsConstructor
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Embedded
    PersonName name;

    @Embedded
    EmailAddress email;

    @Embedded
    StreetAddress address;

    public String getFullName(){
        return name.getFullName();
    }

    public String getEmailAdress(){
        return email.emailAddress();
    }

    public String getStreetAddress(){
        return address.getStreetAddress();
    }

}
