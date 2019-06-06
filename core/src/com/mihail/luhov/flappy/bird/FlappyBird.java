package com.mihail.luhov.flappy.bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mihail.luhov.flappy.bird.states.GameStateManager;
import com.mihail.luhov.flappy.bird.states.MenuState;

public class FlappyBird extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 700;
	public static final String TITLE = "Flappy Bird";

	private GameStateManager gsm;
	private SpriteBatch batch;
	public static int highscore;
	private static Preferences preferences;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		this.preferences = Gdx.app.getPreferences("highscorePreferences");
		if (preferences.contains("highscore"))
		{
			this.highscore = preferences.getInteger("highscore");
		}
		else
		{
			updateHighscore(0);
			this.highscore = 0;
		}
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public static void updateHighscore(int newHighscore)
	{
		if (newHighscore > highscore)
		{
			highscore = newHighscore;
			preferences.putInteger("highscore", newHighscore);
			preferences.flush();
		}
	}
}
