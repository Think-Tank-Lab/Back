package com.example.data;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Entity<ID>
{
    //private static final long serialVersionUID = 7331115341259248461L;
    protected final ID id;
    public Entity(ID id)
    {
        if(id==null){
            this.id =  (ID) UUID.randomUUID().toString();
        }
        else{
            this.id=id;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entitate = (Entity) o;
        return Objects.equals(id, entitate.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Entitate{" + "id='" + id + '\'' + '}';
    }
    public ID getId() { return id; }
}