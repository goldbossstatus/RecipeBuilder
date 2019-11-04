package com.recipe.controller;

import com.recipe.models.Recipe;
import com.recipe.models.User;
import com.recipe.recipeservices.IRecipeService;
import com.recipe.recipeservices.RecipeDTO;
import com.recipe.userservices.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

	private final IUserService userService;

	private final IRecipeService recipeService;

	public RecipeController(IUserService userService,
							IRecipeService recipeService) {
		this.userService = userService;
		this.recipeService = recipeService;
	}

	@GetMapping("/")
	public List<Recipe> getAll(Principal principal) {
		User currentUser = userService.retrieveUser(principal);
		List<Recipe> recipe = recipeService.findByUserId(currentUser.getId());
		return recipe;
	}

	@GetMapping("/{id}")
	public RecipeDTO getRecipeById(@PathVariable("id") Long id,
												   Principal principal) {
		User currentUser = userService.retrieveUser(principal);
		RecipeDTO recipeDTO = recipeService.findById(id);
		if(recipeDTO.getUserId() == currentUser.getId()) {
			return recipeDTO;
		}
		// return customerror;
		return null;
	}

	@PostMapping("/")
	public ResponseEntity<RecipeDTO> createRecipe(Principal principal,
												  @RequestBody RecipeDTO recipeDTO) {
		User currentUser = userService.retrieveUser(principal);
		RecipeDTO recipeDTO1 = recipeService.save(recipeDTO, currentUser);
		return ResponseEntity.created(URI.create("api/recipe/"+recipeDTO1.getId())).body(recipeDTO1);
	}


}
