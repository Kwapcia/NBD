package model;

import java.io.Serializable;
import java.util.UUID;



public abstract class AbstractEntity implements Serializable {

    private UUID entityId = UUID.randomUUID();

    protected long version;
}
