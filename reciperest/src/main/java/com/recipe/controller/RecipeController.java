package com.recipe.controller;

import com.recipe.models.Recipe;
import com.recipe.models.User;
import com.recipe.recipeservices.*;
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

	@PostMapping("/")
	public ResponseEntity<RecipeDTO> createRecipe(Principal principal,
												  @RequestBody RecipeDTO recipeDTO) {
		User currentUser = userService.retrieveUser(principal);
		RecipeDTO recipeDTO1 = recipeService.save(recipeDTO, currentUser);
		return ResponseEntity.created(URI.create("api/recipe/"+recipeDTO1.getId())).body(recipeDTO1);
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
		Recipe recipe = recipeService.findById(id);
		RecipeDTO recipeDTO = new RecipeDTO(recipe.getId(), recipe.getTitle(),
				recipe.getCooktime(), recipe.getDescription(), recipe.getInstructions(),
				recipe.getTags(), recipe.getIngredients(), recipe.getUser().getId());
		if(recipeDTO.getUserId() == currentUser.getId()) {
			return recipeDTO;
		}
		// return customerror;
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<RecipeDTO> changeRecipe(@PathVariable("id") Long id,
													   @RequestBody RecipeDTO recipeDTO,
													   Principal principal) {
		User user = userService.retrieveUser(principal);
		Recipe recipe = recipeService.findById(id);
		if (recipe.getUser() == user) {
			Recipe newRecipe = recipeService.changeRecipe(recipe, recipeDTO);
			RecipeDTO recipeDTO1 = new RecipeDTO(newRecipe.getId(), newRecipe.getTitle(),
					newRecipe.getCooktime(), newRecipe.getDescription(), newRecipe.getInstructions(),
					newRecipe.getTags(), newRecipe.getIngredients(), newRecipe.getUser().getId());
			return ResponseEntity.created(URI.create("api/recipe/" + recipeDTO1.getId())).body(recipeDTO1);
		}
		// return customerror
		return null;
	}

	@PatchMapping("/{id}/title")
	public ResponseEntity<RecipeDTO> changeTitle(@PathVariable("id") Long id,
												 @RequestBody RecipeChangeTitleDTO recipeChangeTitleDTO,
												 Principal principal) {
		User user = userService.retrieveUser(principal);
		Recipe recipe = recipeService.findById(id);
		if(recipe.getUser() == user) {
			Recipe newRecipe = recipeService.changeTitle(recipe, recipeChangeTitleDTO);
			RecipeDTO recipeDTO = new RecipeDTO(newRecipe.getId(), newRecipe.getTitle(),
					newRecipe.getCooktime(), newRecipe.getDescription(), newRecipe.getInstructions(),
					newRecipe.getTags(), newRecipe.getIngredients(), newRecipe.getUser().getId());
			return ResponseEntity.created(URI.create("api/recipe/"+recipeDTO.getId())).body(recipeDTO);
		}
		// return customerror
		return null;
	}

	@PatchMapping("/{id}/cooktime")
	public ResponseEntity<RecipeDTO> changeCooktime(@PathVariable("id") Long id,
												 @RequestBody RecipeChangeCooktimeDTO recipeChangeCooktimeDTO,
												 Principal principal) {
		User user = userService.retrieveUser(principal);
		Recipe recipe = recipeService.findById(id);
		if(recipe.getUser() == user) {
			Recipe newRecipe = recipeService.changeCooktime(recipe, recipeChangeCooktimeDTO);
			RecipeDTO recipeDTO = new RecipeDTO(newRecipe.getId(), newRecipe.getTitle(),
					newRecipe.getCooktime(), newRecipe.getDescription(), newRecipe.getInstructions(),
					newRecipe.getTags(), newRecipe.getIngredients(), newRecipe.getUser().getId());
			return ResponseEntity.created(URI.create("api/recipe/"+recipeDTO.getId())).body(recipeDTO);
		}
		// return customerror
		return null;
	}

	@PatchMapping("/{id}/instructions")
	public ResponseEntity<RecipeDTO> changeInstructions(@PathVariable("id") Long id,
													@RequestBody RecipeChangeInstructionDTO recipeChangeInstructionDTO,
													Principal principal) {
		User user = userService.retrieveUser(principal);
		Recipe recipe = recipeService.findById(id);
		if (recipe.getUser() == user) {
			Recipe newRecipe = recipeService.changeInstruction(recipe, recipeChangeInstructionDTO);
			RecipeDTO recipeDTO = new RecipeDTO(newRecipe.getId(), newRecipe.getTitle(),
					newRecipe.getCooktime(), newRecipe.getDescription(), newRecipe.getInstructions(),
					newRecipe.getTags(), newRecipe.getIngredients(), newRecipe.getUser().getId());
			return ResponseEntity.created(URI.create("api/recipe/" + recipeDTO.getId())).body(recipeDTO);
		}
		// return customerror
		return null;
	}

	@PatchMapping("/{id}/description")
	public ResponseEntity<RecipeDTO> changeDescription(@PathVariable("id") Long id,
														@RequestBody RecipeChangeDescriptionDTO recipeChangeDescriptionDTO,
														Principal principal) {
		User user = userService.retrieveUser(principal);
		Recipe recipe = recipeService.findById(id);
		if (recipe.getUser() == user) {
			Recipe newRecipe = recipeService.changeDescription(recipe, recipeChangeDescriptionDTO);
			RecipeDTO recipeDTO = new RecipeDTO(newRecipe.getId(), newRecipe.getTitle(),
					newRecipe.getCooktime(), newRecipe.getDescription(), newRecipe.getInstructions(),
					newRecipe.getTags(), newRecipe.getIngredients(), newRecipe.getUser().getId());
			return ResponseEntity.created(URI.create("api/recipe/" + recipeDTO.getId())).body(recipeDTO);
		}
		// return customerror
		return null;
	}


}
