package orchard.model.fruit;

import orchard.model.enums.FruitType;

public class Plum extends Fruit {

	public Plum(Integer index) {
		super(FruitType.PLUM, index);
	}
	
	@Override
	public String toString() {
		return "Plums";
	}
	
}
