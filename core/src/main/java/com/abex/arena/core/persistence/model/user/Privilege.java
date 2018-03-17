package com.abex.arena.core.persistence.model.user;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.abex.arena.core.persistence.model.PersistentObject;

@Entity
public class Privilege extends PersistentObject {

   

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<UserRole> roles;

    public Privilege() {
        super();
    }

    public Privilege(final String name) {
        super();
        this.name = name;
    }

    //

  

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Collection<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Privilege other = (Privilege) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Privilege [name=").append(name).append("]").append("[id=").append(getId()).append("]");
        return builder.toString();
    }
}
