package orchard.model.dice_face;

import orchard.ui.JigsawDeck;

public class RavenFace extends DiceFace{

	private JigsawDeck jigsawDeckUI;
	
	public RavenFace(JigsawDeck jigsawDeckUI) {
		super("/dice4.png");
		this.jigsawDeckUI = jigsawDeckUI;
	}

	@Override
	public void action() {
		jigsawDeckUI.show();
	}
}
