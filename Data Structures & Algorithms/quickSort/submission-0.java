// Definition for a pair.
// class Pair {
//     int key;
//     String value;
//
//     public Pair(int key, String value) {
//         this.key = key;
//         this.value = value;
//     }
// }
class Solution {

    public List<Pair> sort(List<Pair> pairs, int s, int e) {
        System.out.println("From " + s + " to " + e);

        if (e==s || e<s) {
            return pairs;
        }

        Pair pivot = pairs.get(e);
        int swap = s;

        for (int i = s; i < e; i++){
            if (pairs.get(i).key < pivot.key){
                Pair temp = pairs.get(swap);
                pairs.set(swap, pairs.get(i));
                pairs.set(i, temp);
                swap++;
            }
        }

        pairs.set(e, pairs.get(swap));
        pairs.set(swap, pivot);

        sort(pairs, s, swap - 1);
        sort(pairs, swap + 1, e);

        return pairs;

    }

    public List<Pair> quickSort(List<Pair> pairs) {
        return sort(pairs, 0, pairs.size() - 1);
    }
}
