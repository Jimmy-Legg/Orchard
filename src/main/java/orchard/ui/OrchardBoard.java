package orchard.ui;

import javafx.scene.layout.StackPane;
import orchard.exceptions.TreeException;
import orchard.model.Jigsaw;

public class OrchardBoard extends StackPane{
	
	private TreeUI[] treeUIs;
	private JigsawUI jigsaw;
	
	public OrchardBoard(Double size, TreeUI[] treeUIs) {
		this(treeUIs);
		
		setSize(size);
	}

	public OrchardBoard(TreeUI[] treeUIs) {
		super();
		
		this.treeUIs = treeUIs;
		
		for(int i = 0; i< treeUIs.length;i++) {
			treeUIs[i].setRotate(45.0 + 90.0 * i);
			this.getChildren().add(treeUIs[i]);
		}
		
		jigsaw = new JigsawUI(new Jigsaw());
		this.getChildren().add(jigsaw);
	}
	
	public void setSize(Double size) {
		
		Double treeDistance = size / 3;
		
		for(int i = 0; i< treeUIs.length;i++) {
			
			try {
				treeUIs[i].setSize(size - treeDistance);
			} catch (TreeException e) {
				e.printStackTrace();
			}
			
			if(i>1) treeUIs[i].setTranslateX(-treeDistance);
			else treeUIs[i].setTranslateX(treeDistance);

			if(i==0 || i==3) treeUIs[i].setTranslateY(-treeDistance);
			else treeUIs[i].setTranslateY(treeDistance);
		}
		jigsaw.setSize((size)/4.5, 7.0);
	}
	
	public boolean areTreesEmpty() {
		Boolean areTreesEmpty = true;
		
		for(TreeUI treeUI : treeUIs) {
			if (!treeUI.getTree().isEmpty()) {
				areTreesEmpty = false;
			}
		}
		return areTreesEmpty;
	}

	public TreeUI[] getTreeUIs() {
		return treeUIs;
	}

	public JigsawUI getJigsawUI() {
		return jigsaw;
	}

	public void setJigsawUI(JigsawUI jigsaw) {
		this.jigsaw = jigsaw;
	}
}
