package com.sg.kata.bankaccount.business.dddframework;

import java.util.Objects;

public abstract class Entity<ID> {
    protected ID id;

    public Entity(ID id) {
        if (id == null) throw new IllegalArgumentException();
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

