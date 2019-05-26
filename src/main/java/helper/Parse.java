package helper;

import java.util.Optional;
import java.util.Scanner;

public class Parse {
    static public Optional<Long> parseLong(String input){
        Scanner in = new Scanner(input);
        return in.hasNextLong() ? Optional.ofNullable(in.nextLong()) : Optional.empty();
    }
}
