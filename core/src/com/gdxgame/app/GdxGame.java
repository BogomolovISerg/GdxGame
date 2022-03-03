
package com.gdxgame.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private BackgroundGdx background;
    private Hero hero;
    private Rock rock;

    public Hero getHero() {
        return hero;
    }

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new BackgroundGdx(this);
        hero = new Hero();
        rock = new Rock();
    }

    @Override
    public void render () {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        ScreenUtils.clear(0, 0, 0.5f, 1);
        batch.begin();
        background.render(batch);
        hero.render(batch);
        rock.render(batch);
        batch.end();
    }

    public void update(float dt){
        background.update(dt);
        hero.update(dt);
        rock.update(dt);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}