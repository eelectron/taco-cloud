package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/design")
public class DesignTacoController {
	private final IngredientRepository ingredientRepo;
	private TacoRepository designRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));

		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}

		Taco taco = new Taco();
		model.addAttribute("taco", taco);		
		return "design";
	}

	/*
	 * @param ingredients : list of ingredients
	 * @param type : type of ingredient we want to filter
	 * @return Return only those ingredients whose type matches the given "type"
	 * */
	private List<Ingredient> filterByType(List<Ingredient> ingredients,
			Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design,
		Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design";
		}
		
		Taco saved = designRepo.save(design);
		order.addDesign(design);
		
		// Save the taco design...
		// We'll do this in chapter 3
		log.info("Processing design: " + design);
		return "redirect:/orders/current";
	}
}