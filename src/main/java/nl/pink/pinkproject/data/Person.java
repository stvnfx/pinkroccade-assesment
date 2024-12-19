package nl.pink.pinkproject.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Slf4j
public class Person extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //name
    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<String> names;

    //birthdate
    private LocalDate birthdate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent1_id")
    @JsonIgnore
    private Person parent1;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent2_id")
    @JsonIgnore
    private Person parent2;

    //children
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Person> children;

    //partner
    @OneToOne
    @JsonIgnore
    private Person partner;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", names=" + names +
                ", birthdate=" + birthdate +
                '}';
    }

    public void addName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        if (this.names == null) {
            this.names = List.of(name);
        } else {
            this.names.add(name);
        }
    }

    public void addChild(Person child) {
        log.info("Adding child: {}", child);
        if (this.children == null) {
            this.children = new HashSet<>();
        }
        this.children.add(child);
    }

    public void removeAllReferences() {
        this.parent1 = null;
        this.parent2 = null;
        this.partner = null;
        this.children = null;
    }
}
