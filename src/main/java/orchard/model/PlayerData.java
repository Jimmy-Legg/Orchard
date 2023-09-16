package orchard.model;

public class PlayerData {

	private final String name;
	private int money;
	private int victory;
	private int loose;
	private String basketImageUrl;
	
	public PlayerData(String name) {
		this(name, 0,0,0, "/basket.png");
	}
	
	public PlayerData(String name, int money, int victory, int loose, String basketImageUrl) {
		super();
		this.name = name;
		this.money = money;
		this.victory = victory;
		this.loose = loose;
		this.basketImageUrl = basketImageUrl;
	}

	public String getBasketImageUrl() {
		return basketImageUrl;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getVictory() {
		return victory;
	}

	public void setVictory(int victory) {
		this.victory = victory;
	}

	public int getLoose() {
		return loose;
	}

	public void setLoose(int loose) {
		this.loose = loose;
	}

	public String getName() {
		return name;
	}
	
	
}
