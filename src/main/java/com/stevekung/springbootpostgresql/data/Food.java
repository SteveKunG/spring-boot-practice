package com.stevekung.springbootpostgresql.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.core.style.ToStringCreator;

import jakarta.persistence.*;

@Entity
@Table(name = "food_details")
public class Food
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private List<Ingredient> ingredient;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    public Food()
    {}

    public Food(String name, List<Ingredient> ingredient, LocalDate expiredDate)
    {
        this.name = name;
        this.ingredient = ingredient;
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString()
    {
        return new ToStringCreator(this)
                .append("id", this.id)
                .append("name", this.name)
                .append("ingredient", this.ingredient)
                .append("expiredDate", this.expiredDate.toString())
                .toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Food o))
        {
            return false;
        }
        return Objects.equals(this.id, o.id) && Objects.equals(this.name, o.name) && Objects.equals(this.ingredient, o.ingredient) && Objects.equals(this.expiredDate, o.expiredDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.id, this.name, this.ingredient, this.expiredDate);
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

    public List<Ingredient> getIngredient()
    {
        return this.ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient)
    {
        this.ingredient = ingredient;
    }

    public LocalDate getExpiredDate()
    {
        return this.expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate)
    {
        this.expiredDate = expiredDate;
    }
}