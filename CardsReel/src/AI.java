// Copyright - All rights reserved to A.S.

import java.util.List;

public class AI{
    private int profit;
    private int[][] arr;

    public AI(int numOfCards) {
        this.profit = 0;

    }

    public int getProfit() {
        return profit;
    }

    public void chooseCard(List<Card> cards) {
        if (cards.size() == 1) {
            profit += cards.get(0).getValue();
            cards.remove(0);
        } else {
            fillArray(cards);
            int idx = returnBestCardIndex(arr, cards);
            profit += cards.get(idx).getValue();
            cards.remove(idx);
        }
    }

    private int returnBestCardIndex(int[][] arr, List<Card> cards) {
        int i = 0, j = cards.size() - 1;
        if (arr[i][j] == ValuesSum(cards, i, j) - arr[i + 1][j]) {
            return i;
        }
        return j;

    }

    public void fillArray(List<Card> cards) {
        arr = new int[cards.size()][cards.size()];
        int i = 0, j = 0, l = 0;
        for (int k = 0; k < cards.size(); k++) {
            i = 0;
            j = l;
            while (j - i == k && i < cards.size() && j < cards.size()) {
                if (i - j == 0) {
                    arr[i][j] = cards.get(i).getValue();
                } else {
                    arr[i][j] = ValuesSum(cards, i, j) - Math.min(arr[i + 1][j], arr[i][j - 1]);
                }
                i++;
                j++;
            }
            l++;
        }
    }

    public int ValuesSum(List<Card> cards, int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += cards.get(k).getValue();
        }
        return sum;
    }
}
