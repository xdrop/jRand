package me.xdrop.jrand.data

class AssetLoaderTest extends GroovyTestCase {
    void testNullHandler() {
        assertTrue AssetLoader.loadAsset("No exists").size() == 0
    }
}
