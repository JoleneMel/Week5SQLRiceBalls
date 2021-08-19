package entity;

public class ExtraIngredient {
	private int extraIngredientId;
	private String ingredient;
	
	public ExtraIngredient(int extraIngredientId, String ingredient) {
		this.setExtraIngredientId(extraIngredientId);
		this.setIngredient(ingredient);
	}

	public int getExtraIngredientId() {
		return extraIngredientId;
	}

	public void setExtraIngredientId(int extraIngredientId) {
		this.extraIngredientId = extraIngredientId;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
}
