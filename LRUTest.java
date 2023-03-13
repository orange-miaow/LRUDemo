package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

class LRUTest {

    @Test
    void doLRU() {
        List<Snapshoot> snapshootList =
                LRU.doLRU(3, new int[]{7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1});
        LRU.printSnapshootList(snapshootList);
    }
}