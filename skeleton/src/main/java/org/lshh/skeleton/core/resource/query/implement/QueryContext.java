package org.lshh.skeleton.core.resource.query.implement;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "query")
public class QueryContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
