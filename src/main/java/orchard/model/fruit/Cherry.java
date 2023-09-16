package orchard.model.fruit;

import orchard.model.enums.FruitType;

public class Cherry extends Fruit{

	public Cherry(Integer index) {
		super(FruitType.CHERRY, index);
	}
	
	@Override
	public String toString() {
		return "Cherry";
	}
	
}
