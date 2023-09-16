package orchard.ui;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import orchard.exceptions.TreeException;
import orchard.model.enums.FruitType;
import orchard.model.fruit.Fruit;
import orchard.model.tree.Tree;

public class TreeUI extends StackPane{
	
	private Double[] fruitX;
	private Double[] fruitY;
	
	private Tree tree;
	
	private FruitUI[] fruitUIs;
	private ImageView treeImageView;
	
	public TreeUI(Tree tree, Double[] fruitX, Double[] fruitY) {
		super();
		
		try {
			setTree(tree);
		} catch (TreeException e) {
			e.printStackTrace();
		}
		
		this.fruitX = fruitX;
		this.fruitY = fruitY;
	}
	
	public TreeUI(Tree tree) {
		this(
			tree,
			new Double[] {
				-0.6, -0.45, -0.175, 0.1, 0.3,
				-0.65, -0.35, 0.0, 0.35, 0.7
			},
			new Double[] {
				-0.2, -0.57, -0.7, -0.77, -0.4,
				0.1, 0.0, -0.2, -0.0, -0.2
			}
		);
	}
	
	private class OnFruitChangeListener implements ListChangeListener<Fruit>{
		
		@Override
		public void onChanged(Change<? extends Fruit> changed) {
			while(changed.next()){
				hideRemovedFruits(changed);
				showAddedFruits(changed);
			}
		}
		
		private void hideRemovedFruits(Change<? extends Fruit> changed) {
			if(changed.wasRemoved()) {
				for(Fruit fruit : changed.getRemoved()) {
					if(fruit != null) {
						fruitUIs[fruit.getIndex()].setVisible(false);
					}
				}
			}
		}
		
		private void showAddedFruits(Change<? extends Fruit> changed) {
			if(changed.wasAdded()) {
				for(Fruit fruit : changed.getAddedSubList()) {
					if(fruit != null) {
						fruitUIs[fruit.getIndex()] = new FruitUI(fruit);
						getChildren().add(fruitUIs[fruit.getIndex()]);
					}
				}
			}
		}
	}
	
	public void setSize(Double size) throws TreeException {
		
		if(fruitX.length >= fruitUIs.length) {
			if(fruitY.length >= fruitUIs.length) {
				for(int i=0; i < fruitUIs.length; i++) {
					if(fruitUIs[i] != null)fruitUIs[i].resize(size/7, fruitX[i] * size / 2, fruitY[i] * size / 2);
				}
			} else throw new TreeException("The fruit list y is incorrectly setuped");
		} else throw new TreeException("The fruit x list is incorrectly setuped");
		
		treeImageView.setFitHeight(size);
		treeImageView.setFitWidth(size);
		
		this.setMaxWidth(size);
		this.setMaxHeight(size);
	}
	
	public FruitUI[] getFruitUIs() {
		return fruitUIs;
	}

	public FruitType getFruitType() {
		return this.getFruitType();
	}

	public Tree getTree() {
		return tree;
	}
	
	public void setTree(Tree tree) throws TreeException {
		if(tree != null) {
			this.tree = tree;
			this.fruitUIs = new FruitUI[tree.getFruits().size()];
			
			for(int i = 0; i < tree.getFruits().size(); i++) {
				if(tree.getFruits().get(i) != null) {
					this.fruitUIs[i] = new FruitUI(tree.getFruits().get(i));
					this.getChildren().add(this.fruitUIs[i]);
				}
				else this.fruitUIs[i] = null;
			}
			
			this.treeImageView = new ImageView(new Image(getClass().getResourceAsStream(tree.getImageURL())));
			this.getChildren().add(treeImageView);
			
			this.tree.getFruits().addListener(new OnFruitChangeListener());
		} else throw new TreeException("tree cannot be null");
	}
}


