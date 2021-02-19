package ee.bcs.valiit.tasks;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class StringMatcher {
    public static void main(String[] args) {
        String mainName = "Õlu Corona Extra Beer 4,5%vol 0,355l pudel";
        String[] lst = {"Corona Extra hele õlu 4.5% 0.355L",
                "Hele õlu CORONA Extra 4.5% 355ml",
                "Corona Extra, 355 ml pudel",
                "Corona Extra õlu, 355 ml",
                "Pastapliiats M/G Corona 0,38 sinine",
                "Õlu Corona Extra, , 330 ml",
                "Õlu CORONA Extra 4.5% 6x330ml prk"
        };
        for (String s : lst) {
            System.out.println(compareNames(mainName, s));
        }
    }
    public static boolean compareNames (String mainName, String compareName) {
        String replacedMain = mainName.replace(",", "").replace(".", "").toLowerCase(Locale.ROOT);
        String replacedCompare = compareName.replace(",", "").replace(".", "").toLowerCase(Locale.ROOT);
        String [] compareList = replacedCompare.split(" ");
        int countTrue = 0;
        int length = compareList.length;
        for (String s : compareList) {
            if (replacedMain.contains(s)) {
                countTrue++;
            }
        }
        double countT = countTrue;
        double len = length;

        double precent =( countT / len ) * 100;
        System.out.println(precent);
        if (precent > 70) {
            return true;
        }
        return false;
    }
}
