package com.turkishdelight.taxe.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.turkishdelight.taxe.Game;
import com.turkishdelight.taxe.Player;
import com.turkishdelight.taxe.SpriteComponent;
import com.turkishdelight.taxe.guiobjects.Button;
import com.turkishdelight.taxe.guiobjects.Label;
import com.turkishdelight.taxe.guiobjects.LabelButton;
import com.turkishdelight.taxe.guiobjects.Pane;
import com.turkishdelight.taxe.guiobjects.Scroller;

public class ShopScene extends GameWindowedGUIScene {

	SpriteComponent shop;
	SpriteComponent scrollPaneBackground;
	
	public ShopScene(GameScene parent, Player player1, Player player2)
	{
		super(parent, player1, player2);
	}
	
	@Override
	public void drawGUIBackground()
	{
		// Create background image for shop
		Texture trainsText = new Texture("Shop_Trains.png");
		shop = new SpriteComponent(this, trainsText, Game.backgroundZ);
		shop.setPosition(0, 0);
		shop.setSize(Game.targetWindowsWidth, Game.targetWindowsHeight);
		Add(shop);
		// ---------------------
		
		drawTrainsScrollpane();
	}
	
	public void drawTrainsScrollpane()
	{
		final Pane pane = new Pane(this, -1);
		pane.setSize(922, 800);
		pane.setPosition(50, 485 - pane.getHeight());
		Add(pane);
		
		Texture scrollPaneBackgroundText = new Texture("Trains_Scrollpane_Background.png");
		scrollPaneBackground = new SpriteComponent(this, scrollPaneBackgroundText, Game.guiZ);
		scrollPaneBackground.setSize(922,800);
		scrollPaneBackground.setLocalPosition(0, 0);
		pane.Add(scrollPaneBackground);
		
		//Min Y is the position the pane must be in to show it's lowest content
		//Max Y is the position the pane must be in to show it's highest content
		final float minY = 73;
		final float maxY = 485 - pane.getHeight();
		
		Texture scrollerText = new Texture("Scroller.png");
		final Scroller scrollPane = new Scroller(this, scrollerText, Game.guiZ) {
					@Override
					public void onMove(float percentage)
					{
						pane.setY(((maxY - minY) * percentage) + minY);
					}
		};
		scrollPane.setOrientation(false);
		scrollPane.setSize(15, 30);
		scrollPane.setRange(74, 454);
		scrollPane.setPosition(35, 454);
		Add(scrollPane);
		
		Texture buyButtonText = new Texture("buy_bg.png");
		
		// Create Steam button
		LabelButton steamButton = new LabelButton(this) {
			@Override
			public void onClickEnd()
			{
				steamPressed();
			}
		};
		steamButton.setLocalPosition(65, 555);
		steamButton.setSize(115, 34);
		steamButton.setTexture(buyButtonText);
		steamButton.setText("Buy: 10cr");
		steamButton.setAlignment(1);
		steamButton.setFont(Label.genericFont(Color.WHITE, 22));
		pane.Add(steamButton);
		// ---------------------
		
		// Create Diesel button
		LabelButton dieselButton = new LabelButton(this) {
			@Override
			public void onClickEnd()
			{
				dieselPressed();
			}
		};
		dieselButton.setLocalPosition(530, 555);
		dieselButton.setSize(115, 34);
		dieselButton.setTexture(buyButtonText);
		dieselButton.setText("Buy: 30cr");
		dieselButton.setAlignment(1);
		dieselButton.setFont(Label.genericFont(Color.WHITE, 22));
		pane.Add(dieselButton);
		// ---------------------
		
		// Create Electric button
		LabelButton electricButton = new LabelButton(this) {
			@Override
			public void onClickEnd()
			{
				electricPressed();
			}
		};
		electricButton.setLocalPosition(65, 305);
		electricButton.setSize(115, 34);
		electricButton.setTexture(buyButtonText);
		electricButton.setText("Buy: 90cr");
		electricButton.setAlignment(1);
		electricButton.setFont(Label.genericFont(Color.WHITE, 22));
		pane.Add(electricButton);
		// ---------------------
		
		// Create Nuclear button
		LabelButton nuclearButton = new LabelButton(this) {
			@Override
			public void onClickEnd()
			{
				nuclearPressed();
			}
		};
		nuclearButton.setLocalPosition(530, 305);
		nuclearButton.setSize(115, 34);
		nuclearButton.setTexture(buyButtonText);
		nuclearButton.setText("Buy: 200cr");
		nuclearButton.setAlignment(1);
		nuclearButton.setFont(Label.genericFont(Color.WHITE, 22));
		pane.Add(nuclearButton);
		// ---------------------
		
		// Create MagLev button
		LabelButton magLevButton = new LabelButton(this) {
			@Override
			public void onClickEnd()
			{
				magLevPressed();
			}
		};
		magLevButton.setLocalPosition(65, 45);
		magLevButton.setSize(115, 34);
		magLevButton.setTexture(buyButtonText);
		magLevButton.setText("Buy: 500cr");
		magLevButton.setAlignment(1);
		magLevButton.setFont(Label.genericFont(Color.WHITE, 22));
		pane.Add(magLevButton);
		// ---------------------
		
		// Create King button
		LabelButton kingButton = new LabelButton(this) {
			@Override
			public void onClickEnd()
			{
				kingPressed();
			}
		};
		kingButton.setLocalPosition(530, 45);
		kingButton.setSize(115, 34);
		kingButton.setTexture(buyButtonText);
		kingButton.setText("Buy: 1000cr");
		kingButton.setAlignment(1);
		kingButton.setFont(Label.genericFont(Color.WHITE, 22));
		pane.Add(kingButton);
		// ---------------------
	}
	
	@Override
	public void drawWindowButtons()

	{
		// Create Train button
		Button trainButton = new Button(this) {
			@Override
			public void onClickEnd()
			{
				trainPressed();
			}
		};
		trainButton.setPosition(418, 518);
		trainButton.setSize(180, 70);
		Texture buttonText = new Texture("Clear_Button.png");
		trainButton.setTexture(buttonText);
		Add(trainButton);
		// ---------------------
		
		// Create Obstacle button
		Button obstacleButton = new Button(this) {
			@Override
			public void onClickEnd()
			{
				obstaclePressed();
			}
		};
		obstacleButton.setPosition(608, 518);
		obstacleButton.setSize(180, 70);
		obstacleButton.setTexture(buttonText);
		Add(obstacleButton);
		// ---------------------
		
		// Create Resource button
		Button resourceButton = new Button(this) {
			@Override
			public void onClickEnd()
			{
				resourcePressed();
			}
		};
		resourceButton.setPosition(798, 518);
		resourceButton.setSize(180, 70);
		resourceButton.setTexture(buttonText);
		Add(resourceButton);
		// ---------------------
	}
	
	public void trainPressed()
	{
		System.out.println("trainPressed");
		
		// Create trains texture and set shop window background to be trains.
		Texture trainsText = new Texture("Shop_Trains.png");
		shop.setTexture(trainsText);
		// ---------------------
		
		// Create trains scrollpane background texture and assign to the scrollpane
		Texture scrollPaneBackgroundText = new Texture("Trains_Scrollpane_Background.png");
		scrollPaneBackground.setTexture(scrollPaneBackgroundText);
		// ---------------------
		
	}
	
	public void obstaclePressed()
	{
		System.out.println("obstaclePressed");
		
		// Create obstacles texture and set shop window background to be obstacles.
		Texture obstaclesText = new Texture("Shop_Obstacles.png");
		shop.setTexture(obstaclesText);
		// ---------------------
		
		// Create obstacles scrollpane background texture and assign to the scrollpane
		Texture scrollPaneBackgroundText = new Texture("Obstacles_Scrollpane_Background.png");
		scrollPaneBackground.setTexture(scrollPaneBackgroundText);
		// ---------------------
		
	}
	
	public void resourcePressed()
	{
		System.out.println("resourcePressed");
		
		// Create resources texture and set shop window background to be resources.
		Texture resourcesText = new Texture("Shop_Resources.png");
		shop.setTexture(resourcesText);
		// ---------------------
		
		// Create resources scrollpane background texture and assign to the scrollpane
		Texture scrollPaneBackgroundText = new Texture("Obstacles_Scrollpane_Background.png");
		scrollPaneBackground.setTexture(scrollPaneBackgroundText);
		// ---------------------
		
	}
	
	@Override
	public void shopToolbarPressed() 
	{
		System.out.println("shopToolbarPressed");
		//DO Nothing
	}
	
	public void steamPressed()
	{
		System.out.println("steamPressed");
		
	}
	
	public void dieselPressed()
	{
		System.out.println("dieselPressed");
	}
	
	public void electricPressed()
	{
		System.out.println("electricPressed");
	}
	
	public void nuclearPressed()
	{
		System.out.println("nuclearPressed");
	}
	
	public void magLevPressed()
	{
		System.out.println("magLevPressed");
	}
	
	public void kingPressed()
	{
		System.out.println("kingPressed");
	}
}
