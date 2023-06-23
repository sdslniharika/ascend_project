package demos;

import java.util.Comparator;

public class KeyCustomerIdComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer e1, Integer e2) {
        return e1-e2;
    }
}
