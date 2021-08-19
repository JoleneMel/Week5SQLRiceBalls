package application;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.ExtraIngredientDao;
import dao.RiceBallDao;
import entity.ExtraIngredient;
import entity.RiceBall;

public class Menu {
	
	private RiceBallDao riceBallDao = new RiceBallDao();
	private ExtraIngredientDao extraIngredientDao = new ExtraIngredientDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display RiceBalls", 
			"Display RiceBall's Recipe", 
			"Create RiceBall", 
			"Delete RiceBall", 
			"Create RiceBall Recipe By Ingredient", 
			"Delete RiceBall Recipe By Ingredient",
			"Update RiceBall Recipe By Ingredient",
			"Update RiceBall"
			);
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			try {
			if (selection.equals("1")) {
				displayRiceBalls();
			} else if (selection.equals("2")) {
				displayRiceBall();
			} else if (selection.equals("3")) {
				createRiceBall();	
			} else if (selection.equals("4")) {
				deleteRiceBall();
			} else if (selection.equals("5")) {
				createExtraIngredient();
			} else if (selection.equals("6")) {
				deleteExtraIngredient();
			} else if (selection.equals("7")) {
				updateExtraIngredient();
			} else if (selection.equals("8")) {
				updateRiceBall();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			System.out.println("Please press enter to continue");
			scanner.nextLine();
		} while (!selection.equals("-1"));
	}

	private void deleteExtraIngredient() throws SQLException {
		System.out.print("Enter extraIngredient id to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		extraIngredientDao.deleteExtraIngredientById(id);
		
	}

	private void createExtraIngredient() throws SQLException {
		displayRiceBalls();
		System.out.print("Enter name of new ingredient:");
		String ingredient = scanner.nextLine();
		System.out.print("Enter riceBall id for the new ingredient:");
		int riceBallId = Integer.parseInt(scanner.nextLine());
		extraIngredientDao.createNewExtraIngredient(ingredient, riceBallId);
	}
	
	private void updateExtraIngredient() throws SQLException {
		displayRiceBalls();
		System.out.println("Which riceball recipe would you like to update?");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the new ingredient: ");
		String ingredient = scanner.nextLine();
		extraIngredientDao.updateExtraIngredientById(id, ingredient);
		System.out.println("Recipe has been updated");
	}

	private void deleteRiceBall() throws SQLException {
		displayRiceBalls();
		System.out.print("Enter riceBall id to delete");
		int id = Integer.parseInt(scanner.nextLine()); // add id verification :) 
		riceBallDao.deleteRiceBallById(id);
		
		
	}

	private void createRiceBall() throws SQLException {
		System.out.print("Enter new riceBall name:");
		String riceBallName = scanner.nextLine();
		riceBallDao.createNewRiceBall(riceBallName);
		
	}

	private void displayRiceBall() throws SQLException {
		System.out.print("Enter riceBall id: ");
		int id = Integer.parseInt(scanner.nextLine());
		RiceBall riceBall = riceBallDao.getRiceBallbyId(id);
		System.out.println(riceBall.getRiceBallId() + ": " + riceBall.getName());
		for (ExtraIngredient extraIngredient : riceBall.getExtraIngredients()) {
			System.out.println("\tExtraIngredientId: " + extraIngredient.getExtraIngredientId() + " Ingredient: " 
		+ extraIngredient.getIngredient());
		}
		
	}

	private void displayRiceBalls() throws SQLException {
		List<RiceBall> riceBalls = riceBallDao.getRiceBalls();
		for (RiceBall riceBall :  riceBalls) {
			System.out.println(riceBall.getRiceBallId() + ": " + riceBall.getName());
		}
		
	}

	private void updateRiceBall() throws SQLException {
		displayRiceBalls();
		System.out.println("Which riceball would you like to update?");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the new riceball: ");
		String name = scanner.nextLine();
		riceBallDao.updateRiceBallById(id, name);
		System.out.println("Recipe has been updated");
	}
	
	private void printMenu() {
		System.out.println("Select an Option: \n ------------------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
		
	}

}