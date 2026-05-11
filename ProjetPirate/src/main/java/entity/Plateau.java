package entity;

public class Plateau {
	Case[] cases = new Case[30];
	
	public Case getCase(int indice) {
		return cases[indice];
	}
}
