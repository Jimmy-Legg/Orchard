package orchard.model.enums;

import javafx.scene.input.DataFormat;

public enum FruitType {

	APPLE(0, "/apple.png"),
	PLUM(1, "/plums.png"),
	PEAR(2, "/pear.png"),
	CHERRY(3, "/cherry.png");
	
	private final Integer index;
	private final String imageURL;
	private static final  DataFormat DATA_FORMAT = new DataFormat("model/fruit/FruitType");
	
	FruitType(Integer index, String imageURL) {
		this.index = index;
		this.imageURL = imageURL;
	}

	public int getIndex() {
		return index;
	}
	
	public static DataFormat getDataFormat() {
		return DATA_FORMAT;
	}

	public String getImageURL() {
		return imageURL;
	}
	
}
