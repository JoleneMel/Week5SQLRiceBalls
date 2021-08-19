package entity;

import java.util.List;

public class RiceBall {
	private int riceBallId; 
	private String name;
	private List<ExtraIngredient> extraIngredients;
	
	public RiceBall(int riceBallId, String name, List<ExtraIngredient> extraIngredients) {
		this.setRiceBallId(riceBallId);
		this.setName(name);
		this.setExtraIngredients(extraIngredients);
	}

	public int getRiceBallId() {
		return riceBallId;
	}

	public void setRiceBallId(int riceBallId) {
		this.riceBallId = riceBallId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExtraIngredient> getExtraIngredients() {
		return extraIngredients;
	}

	public void setExtraIngredients(List<ExtraIngredient> extraIngredients) {
		this.extraIngredients = extraIngredients;
	}

}






