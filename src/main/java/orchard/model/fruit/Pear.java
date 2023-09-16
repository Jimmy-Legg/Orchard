package orchard.model.fruit;

import orchard.model.enums.FruitType;

public class Pear extends Fruit {

	public Pear(Integer index) {
		super(FruitType.PEAR, index);
	}

	@Override
	public String toString() {
		return "Pear";
	}
	
}
