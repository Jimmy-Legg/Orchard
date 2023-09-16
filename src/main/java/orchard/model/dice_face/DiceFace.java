package orchard.model.dice_face;

public abstract class DiceFace {
	
	protected final String imageURL;

	protected DiceFace(String imageURL) {
		super();
		this.imageURL = imageURL;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	
	public abstract void action();
	
}
