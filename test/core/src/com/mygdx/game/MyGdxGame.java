package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;

	private Texture turtleTexture;
	private float turtleX;
	private float turtleY;
	private Rectangle turtleRectangle;

	private Texture starfishTexture;
	private float starfishX;
	private float starfishY;
	private Rectangle starfishRectangle;

	private Texture oceanTexture;
	private Texture winMessageTexture;

	private boolean isWin;

	@Override
	public void create () {
		batch = new SpriteBatch();

		turtleTexture = new Texture("turtle-1.png");
		turtleX = 20;
		turtleY = 20;
		turtleRectangle = new Rectangle(turtleX, turtleY,
				turtleTexture.getWidth(), turtleTexture.getHeight());

		starfishTexture = new Texture("starfish.png");
		starfishX = 380;
		starfishY = 380;
		starfishRectangle = new Rectangle(starfishX, starfishY,
        starfishTexture.getWidth(), starfishTexture.getHeight());

		oceanTexture = new Texture("water.jpg");
		winMessageTexture = new Texture("you-win.png");

		isWin = false;
	}

	@Override
	public void render () {
	  // process user input
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) turtleX--;
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) turtleX++;
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) turtleY++;
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) turtleY--;

    // update turtle rectangle location
    turtleRectangle.setPosition(turtleX, turtleY);

    // check win condition
    if (turtleRectangle.overlaps(starfishRectangle)) isWin = true;

    // clear screen
    Gdx.gl.glClearColor(0,0,0,1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    // draw graphic
    batch.begin();
    batch.draw(oceanTexture, 0, 0);
    if (isWin) {
      batch.draw(winMessageTexture, 180, 180);
      batch.end();
      return;
    }
    batch.draw(starfishTexture, starfishX, starfishY);
    batch.draw(turtleTexture, turtleX, turtleY);
    batch.end();
	}
	
	@Override
	public void dispose () {
		System.out.println("Dispose-------------------");
		batch.dispose();
//		img.dispose();
	}
}
