package nl.pink.pinkproject.data.projection;

import nl.pink.pinkproject.data.Person;

import java.util.List;

public interface SortedProjection {
    Long getId();
    List<String> getNames();
    Person getParent1();
    Person getParent2();
    Person getPartner();
}
