package com.clemens.core;

import java.security.SecureRandom;

/**
 * @author Clemens/Shindu
 * @version 1.0
 *
 */

public class Calculator {

    /**
     * The main algorithm, here the data gets calculated.
     *
     * @param cards        defines the total number of cards you can collect.
     * @param cardsPerPack defines the number of cards included in each pack.
     * @param times        defines how often this algorithm should run as more it runs, as accurate is the result data.
     * @param albums       defines how many albums you have where you can put your cards.
     * @return it gives back the result data this data is used in {@link com.clemens.ui.UI} for displaying the table.
     */

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

    /**
     * This method checks in which album the given card value is not.
     *
     * @param arr    the array with all albums and its given cards.
     * @param my     this is the value of the card.
     * @param albums the number of albums.
     * @return the album where the card does not exist if in every album is the card, then it returns -1.
     */

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

    /**
     * This method checks if a given int array contains an int.
     *
     * @param arr the int array.
     * @param my  the int which the algorithm searches for.
     * @return an boolean weather it has found the given int in the array.
     */

    private static boolean contains(int[] arr, int my) {
        for (int anArr : arr) {
            if (anArr == my) {
                return true;
            }
        }
        return false;
    }
}
