package com.recipe.tagservices;

import com.recipe.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;


@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findById(Long tagId);
    ArrayList<Tag> findByRecipeId(Long recipeId);
}
