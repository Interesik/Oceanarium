package org.nbd.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity {
    @Version
    private long version;
}
