package org.example;

import java.util.ArrayList;
import java.util.List;

public class LRU {

    private static int getFreeBlockBin(int[] blockBins) {
        for (int i = 0; i < blockBins.length; i++) {
            if (blockBins[i] == -1) {
                return i;
            }
        }

        return -1;
    }

    private static int getPageBlockBin(int[] blockBins, int pageNo) {
        for (int i = 0; i < blockBins.length; i++) {
            if (blockBins[i] == pageNo) {
                return i;
            }
        }

        return -1;
    }

    private static int getReplacedPageBlockBin(int[] pagesUnusedTime) {
        int maxIndex = 0;
        for (int i = 1; i < pagesUnusedTime.length; i++) {
            if (pagesUnusedTime[maxIndex] < pagesUnusedTime[i]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private static void updatePagesUnusedTime(int[] pagesUnusedTime, int newPageBlockBin) {
        for (int i = 0; i < pagesUnusedTime.length; i++) {
            if (i != newPageBlockBin) {
                if (pagesUnusedTime[i] != -1) {
                    pagesUnusedTime[i] += 1;
                }
            } else {
                pagesUnusedTime[i] = 0;
            }
        }
    }

    public static List<Snapshoot> doLRU(int blockCount, int[] pages) {
        if (blockCount < 1) {
            return null;
        }

        if ((pages == null) || (pages.length == 0)) {
            return null;
        }

        int[] pagesUnusedTime = new int[blockCount];
        for (int i = 0; i < pagesUnusedTime.length; i++) {
            pagesUnusedTime[i] = -1;
        }
        int[] blockBins = new int[blockCount];
        for (int i = 0; i < blockBins.length; i++) {
            blockBins[i] = -1;
        }
        int pageCount = pages.length;

        List<Snapshoot> snapshootList = new ArrayList<>(pageCount);

        for (int i = 0; i < pageCount; i++) {
            int pageNo = pages[i];

            int freeBlockBin = getFreeBlockBin(blockBins);
            if (freeBlockBin != -1) {
                // if there is a free block bin
                blockBins[freeBlockBin] = pageNo;
                // update pagesUnusedTime
                updatePagesUnusedTime(pagesUnusedTime, freeBlockBin);
            } else {
                // there is no free block bin

                int pageBlockBin = getPageBlockBin(blockBins, pageNo);
                if (pageBlockBin != -1) {
                    // update pagesUnusedTime
                    updatePagesUnusedTime(pagesUnusedTime, pageBlockBin);
                } else {
                    // replace
                    int replacedPageBlockBin = getReplacedPageBlockBin(pagesUnusedTime);
                    blockBins[replacedPageBlockBin] = pageNo;
                    // update pagesUnusedTime
                    updatePagesUnusedTime(pagesUnusedTime, replacedPageBlockBin);
                }

            }

            Snapshoot snapshoot = new Snapshoot(pageNo, blockBins);
            snapshootList.add(snapshoot);
        }

        return snapshootList;
    }

    public static void printSnapshootList(List<Snapshoot> snapshootList) {
        if ((snapshootList == null) || (snapshootList.size() == 0)) {
            System.out.println("cannot print LRU snapshoot list");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Access\t|");

        for (Snapshoot snapshoot : snapshootList) {
            stringBuilder.append(snapshoot.getPageNo() + "\t|");
        }
        stringBuilder.append("\n");

        Snapshoot firstSnapshoot = snapshootList.get(0);
        int blockBinsCount = firstSnapshoot.getBlockBins().length;
        for (int i = 0; i < blockBinsCount; i++) {
            stringBuilder.append("Block " + (i + 1) + "\t|");

            for (Snapshoot snapshoot : snapshootList) {
                stringBuilder.append(snapshoot.getBlockBins()[i] + "\t|");
            }

            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }
}

class Snapshoot {
    private int pageNo;
    private int[] blockBins;

    public Snapshoot(int pageNo, int[] blockBins) {
        this.pageNo = pageNo;
        this.blockBins = new int[blockBins.length];

        for (int i = 0; i < blockBins.length; i++) {
            this.blockBins[i] = blockBins[i];
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int[] getBlockBins() {
        return blockBins;
    }

    public void setBlockBins(int[] blockBins) {
        this.blockBins = blockBins;
    }
}
