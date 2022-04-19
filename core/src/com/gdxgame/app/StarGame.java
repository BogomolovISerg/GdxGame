package com.gdxgame.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdxgame.app.game.Background;
import com.gdxgame.app.game.Hero;
import com.gdxgame.app.screen.GameScreen;
import com.gdxgame.app.screen.ScreenManager;

public class StarGame extends Game {
	private SpriteBatch batch;


	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.getInstance().init(this,batch);
		ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		getScreen().render(dt);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
