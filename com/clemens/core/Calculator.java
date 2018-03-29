package com.clemens.core;

import java.security.SecureRandom;

public class Calculator {

    public static double[][] getRes(final int cards, final int cardsPerPack, final int times, final int albums) {

        final SecureRandom random = new SecureRandom();
        int avg[][] = new int[albums][cards];
        for (int j = 0; j < times; j++) {
            int temp[][] = new int[albums][cards];
            int foundAlbumCards[] = new int[albums];
            int foundCards = 0;
            int pack = 1;
            do {
                int nextPack[] = new int[cardsPerPack];
                for (int i = 0; i < cardsPerPack; i++) {
                    if (foundCards >= cards * albums) break;
                    int nextCard;
                    do {
                        nextCard = random.nextInt(cards + 1);
                    } while (contains(nextPack, nextCard));
                    nextPack[i] = nextCard;
                    int albumToFill = contains(temp, nextCard, albums);

                    if (albumToFill >= 0) {
                        avg[albumToFill][foundAlbumCards[albumToFill]] += pack;
                        temp[albumToFill][nextCard - 1] = 1;
                        foundAlbumCards[albumToFill]++;
                        foundCards++;
                    }
                }
                pack++;
            } while (foundCards < cards * albums);
        }
        double[][] res = new double[cards][albums];
        for (int i = 0; i < albums; i++) {
            for (int j = 0; j < cards; j++) {
                res[j][i] = (double) avg[i][j] / (double) times;
            }
        }
        return res;

    }

    private static int contains(int[][] arr, int my, int albums) {

        for (int j = 0; j < albums; j++) {
            boolean found = false;
            if (arr[j][my - 1] == 1) {
                found = true;
            }
            if (!found) return j;
        }
        return -1;
    }

    private static boolean contains(int[] arr, int my) {
        for (int anArr : arr) {
            if (anArr == my) {
                return true;
            }
        }
        return false;
    }
}
