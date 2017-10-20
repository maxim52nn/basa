package test;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by DaiBogh on 10.10.17.
 */
public interface IntegerTree extends Serializable{
    ArrayList<Long> search(Integer value);

}
