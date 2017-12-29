package me.xdrop.jrand.data

class AssetLoaderTest extends GroovyTestCase {
    void testNullHandler() {
        assertTrue AssetLoader.loadList("No exists").size() == 0
    }
}
