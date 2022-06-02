package com.example.pokemondefence.framework.map;

public class TiledLayer {
    private final TiledMap map;

    ////////////////////////////////////////////////////////////
    // from tmj
    public int x, y, width, height;
    public int[] data;
    ////////////////////////////////////////////////////////////

    TiledLayer(TiledMap map) {
        this.map = map;
    }

    public int getTileAt(int x, int y) {
        try {
            return data[y * width + x];
        } catch (Exception e) {
            return 0;
        }
    }
}
