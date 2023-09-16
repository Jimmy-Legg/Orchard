package orchard.model;

import java.util.List;
import java.util.Random;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import orchard.model.dice_face.DiceFace;

public class Dice {

	private List<DiceFace> faces;

	private Boolean isClickable;
	private IntegerProperty actualFace;
	
	private Random random;
	
	public Dice(List<DiceFace> faces) {
		super();
		
		this.random = new Random();
		
		this.faces = faces;
		this.actualFace = new SimpleIntegerProperty();
		this.isClickable = true;
	}

	public void changeFace() {
		Integer randomNumber = random.nextInt(faces.size()-1);
        
        if(randomNumber < actualFace.intValue())actualFace.set(randomNumber);
        else actualFace.set(randomNumber + 1);
	}
	
	public void action() {
		this.faces.get(actualFace.intValue()).action();
	}
	
	public IntegerProperty getActualFaceProperty() {
		return actualFace;
	}

	public DiceFace getFace() {
		return faces.get(actualFace.intValue());
	}
	
	public List<DiceFace> getFaces() {
		return faces;
	}

	public Boolean isClickable() {
		return isClickable;
	}

	public void setClickable(Boolean isClickable) {
		this.isClickable = isClickable;
	}
	
}
