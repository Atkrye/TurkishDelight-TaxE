package com.turkishdelight.taxe;

import java.util.EmptyStackException;
import java.util.Stack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.turkishdelight.taxe.scenes.GameScene;
import com.turkishdelight.taxe.scenes.MainMenuScene;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	private Scene currentScene;
	//These values are used to conform the width and height of the window across both this application and the taxe-desktop application
	public static final int targetWindowsWidth = 1024;
	public static final int targetWindowsHeight = 768;
	
	//The values store the z values of various layers in the game. SpriteComponents are then sorted by the zorder before being drawn
	//To create layering
	//The map is always the lowest layer
	public static Game activeGame;
	
	public static final int backgroundZ = 0;
	//Locations and routes are displayed in the layer above the map
	public static final int locationZ = 1;
	//Trains and obstacles are displayed in the layer above the locations and routes
	public static final int objectsZ = 2;
	//The gui is displayed in the layer above the objects
	public static final int guiZ = 3;
	//The shop is displayed as the top layer
	public static final int shopZ = 3;
	//The goals window is displayed in the same layer
	public static final int goalsZ = 3;
	//The current resources window is displayed in the same layer
	public static final int currentResourcesZ = 3;
	public static final int mainZ =5;
	
	public static Stack<Scene> scenes = new Stack<Scene>();
	
	public void create () {
		System.out.println("Game created");
		activeGame = this;
		batch = new SpriteBatch();
		System.out.println("Setting intital scene");
		//By default we set out scene to the main menu

		setLocalScene(new GameScene(new Player(), new Player()));
		setLocalScene(new MainMenuScene());
		//setLocalScene(new ShopScene());

	}

	public void setLocalScene(Scene newScene)
	{
		this.currentScene = newScene;
		Gdx.input.setInputProcessor(newScene);
	}
	
	public static void setScene(Scene newScene)
	{
		activeGame.setLocalScene(newScene);
		System.out.println("Current Scene:" + newScene.getClass().getSimpleName().toString());
	}
	
	public static void popScene()
	{
		try
		{
			setScene(scenes.pop());
		}
		catch(EmptyStackException e)
		{
			//We are at the bottom of the stack
			setScene(activeGame.currentScene);
		}
	}
	
	public static void pushScene(Scene s)
	{
		scenes.push(s);
		setScene(s);
	}
	
	
	//LibGdxs default update method is named render, it calls an update method before rendering the game
	@Override
	public void render () {
		//Ensure our components object is correctly set up
		Update();
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//draw our components object
		currentScene.Draw(batch);
		batch.end();
	}
	
	//This method updates the scene
	public void Update()
	{
		currentScene.UpdateGraphics();
		currentScene.Update();
	}
}