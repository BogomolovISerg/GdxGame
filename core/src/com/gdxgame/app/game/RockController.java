package com.gdxgame.app.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gdxgame.app.game.helpers.ObjectPool;
import com.gdxgame.app.screen.utils.Assets;

public class RockController extends ObjectPool<Rock> {
    private GameController gc;

    @Override
    protected Rock newObject() {
        return new Rock(gc);
    }

    public RockController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Rock a = activeList.get(i);
            a.render(batch);
        }
    }

    public void setup(float x, float y, float vx, float vy, float scale){
        getActiveElement().activate(x, y, vx, vy, scale);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
