package nl.pink.pinkproject.data.repository;

import nl.pink.pinkproject.data.Person;
import nl.pink.pinkproject.data.projection.SortedProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query("SELECT p FROM Person p")
    List<SortedProjection> findAllSorted();

}
