package Other;

/**
 * Created by DSizov on 28.09.2017.
 */
public class Helper {
    public static String formatToJson(String response){
        String test = response.replaceAll("(\\w+)=(\\w+)", "\"$1\":\"$2\"");
        return test;
    }
}
