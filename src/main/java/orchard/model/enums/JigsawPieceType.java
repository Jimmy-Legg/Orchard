package orchard.model.enums;

import javafx.scene.input.DataFormat;

public enum JigsawPieceType {
	
	PIECE1("/jigsawPiece1.png", 0),
	PIECE2("/jigsawPiece2.png", 1),
	PIECE3("/jigsawPiece3.png", 2),
	PIECE4("/jigsawPiece4.png", 3),
	PIECE5("/jigsawPiece5.png", 4),
	PIECE6("/jigsawPiece6.png", 5),
	PIECE7("/jigsawPiece7.png", 6),
	PIECE8("/jigsawPiece8.png", 7),
	PIECE9("/jigsawPiece9.png", 8);
	
	private final Integer index;
	private final String imageURL;
	
	private static final DataFormat DATA_FORMAT = new DataFormat("model/jigsawPiece");
	
	public static DataFormat getDataFormat() {
		return DATA_FORMAT;
	}
	
	JigsawPieceType(String imageURL, int index) {
		this.imageURL = imageURL;
		this.index = index;
	}

	public String getImageURL() {
		return imageURL;
	}
	
	public Integer getIndex() {
		return index;
	}
	
}
