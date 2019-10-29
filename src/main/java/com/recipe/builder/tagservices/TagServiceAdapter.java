package com.recipe.builder.tagservices;

import com.recipe.builder.models.Recipe;
import com.recipe.builder.models.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagServiceAdapter implements ITagService {

    private final TagRepository tagRepository;

    public TagServiceAdapter(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag create(TagRequest tagRequest, Recipe recipe) {
        Tag tag = new Tag(tagRequest.getName(),
                recipe);

        return tagRepository.save(tag);
    }
}
