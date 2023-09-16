package orchard.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import orchard.model.enums.FruitType;

public class Player implements Comparable<Player> {
	
	private IntegerProperty appleAmount;
	private IntegerProperty pearAmount;
	private IntegerProperty cherryAmount;
	private IntegerProperty plumAmount;
	
	private Integer ravenPieceAmount;
	
	private NumberBinding score;
	private PlayerData playerData;
	
	public Player(PlayerData playerData, Integer appleAmount, Integer pearAmount, Integer cherryAmount, Integer plumAmount, Integer ravenPieceAmount) {
		this.appleAmount = new SimpleIntegerProperty(appleAmount);
		this.pearAmount = new SimpleIntegerProperty(pearAmount);
		this.cherryAmount = new SimpleIntegerProperty(cherryAmount);
		this.plumAmount = new SimpleIntegerProperty(plumAmount);
		this.ravenPieceAmount = ravenPieceAmount;
		this.playerData = playerData;
		this.score = Bindings.add(this.appleAmount, Bindings.add(this.pearAmount, Bindings.add(this.cherryAmount, this.plumAmount)));
	}
	
	public Player(PlayerData playerData) {
		this(playerData, 0, 0, 0, 0, 0);
	}

	public PlayerData getPlayerData() {
		return playerData;
	}

	@Override
	public String toString() {
		return playerData.getName() + " | score : " + getFruitAmount().intValue() + " | raven : " + getRavenPieceAmount().intValue();
	}
	
	public NumberBinding getFruitAmount() {
		return score;
	}
	
	public void addFruit(FruitType fruitType) {
		if(fruitType.equals(FruitType.APPLE))addApple();
		else if(fruitType.equals(FruitType.PEAR))addPear();
		else if(fruitType.equals(FruitType.PLUM))addPlum();
		else addCherry();
	}
	
	public void addPear() {
		pearAmount.set(pearAmount.get() + 1);
	}
	
	public void addApple() {
		appleAmount.set(appleAmount.get() + 1);
	}
	
	public void addCherry() {
		cherryAmount.set(cherryAmount.get() + 1);
	}
	
	public void addPlum() {
		plumAmount.set(plumAmount.get() + 1);
	}

	public IntegerProperty getAppleAmount() {
		return appleAmount;
	}

	public void setAppleAmount(Integer appleAmount) {
		this.appleAmount.set(appleAmount);
	}

	public IntegerProperty getPearAmount() {
		return pearAmount;
	}

	public void setPearAmount(Integer pearAmount) {
		this.pearAmount.set(pearAmount);
	}

	public IntegerProperty getCherryAmount() {
		return cherryAmount;
	}

	public void setCherryAmount(Integer cherryAmount) {
		this.cherryAmount.set(cherryAmount);
	}

	public IntegerProperty getPlumAmount() {
		return plumAmount;
	}

	public void setPlumAmount(Integer plumAmount) {
		this.plumAmount.set(plumAmount);
	}
	
	public Integer getRavenPieceAmount() {
		return ravenPieceAmount;
	}
	
	public void incrementRavenPieceAmount() {
		this.ravenPieceAmount++;
	}

	@Override
	public int compareTo(Player p2) {
		Player p1 = this;

		int i = Integer.compare(p2.getFruitAmount().intValue(), p1.getFruitAmount().intValue());
	    if (i != 0) return i;

	    return Integer.compare(p1.getRavenPieceAmount(), p2.getRavenPieceAmount());
	}

}
