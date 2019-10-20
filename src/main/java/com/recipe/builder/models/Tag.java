package com.recipe.builder.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "rc_tag")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "update rc_tag set deleted = 1 WHERE id = ?")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deleted_at = new Date();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Recipe recipe;

    public Tag() {
    }

    public Tag(@NotNull String title, Recipe recipe) {
        this.title = title;
        this.recipe = recipe;
    }

    public String getTitle() {
        return title;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
