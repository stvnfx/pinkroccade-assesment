package nl.pink.pinkproject.data;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class AuditEntity {
    @CreationTimestamp
    private String createdBy;
    @UpdateTimestamp
    private String updatedBy;
}
