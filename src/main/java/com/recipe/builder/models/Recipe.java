package com.recipe.builder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rc_recipe")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "update rc_recipe set deleted = 1 WHERE id = ?")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 50)
    private String cooktime;

    @NotNull
    private String instructions;

    @NotNull
    private String description;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @NotNull
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tag> tags = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt = new Date();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt = new Date();

    @Column(name = "deleted")
    private int deleted = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deleted_at = new Date();

    public Recipe() {
    }

    public Recipe(@NotNull @Size(max = 100) String title,
                  @NotNull @Size(max = 50) String cooktime,
                  @NotNull String instructions, @NotNull String description,
                  @NotNull User user) {
        this.title = title;
        this.cooktime = cooktime;
        this.instructions = instructions;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCooktime() {
        return cooktime;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getDeleted() {
        return deleted;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }



}
