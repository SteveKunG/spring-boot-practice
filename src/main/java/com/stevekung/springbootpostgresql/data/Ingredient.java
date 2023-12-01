package com.stevekung.springbootpostgresql.data;

import java.util.Objects;

import org.springframework.core.style.ToStringCreator;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredient_details")
public class Ingredient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    public Ingredient()
    {}

    public Ingredient(String name, String type)
    {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString()
    {
        return new ToStringCreator(this)
                .append("id", this.id)
                .append("name", this.name)
                .append("type", this.type)
                .toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Ingredient o))
        {
            return false;
        }
        return Objects.equals(this.id, o.id) && Objects.equals(this.name, o.name) && Objects.equals(this.type, o.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.name, this.type);
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}