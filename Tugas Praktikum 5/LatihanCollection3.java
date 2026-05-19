import java.util.TreeMap;

public class LatihanCollection3 {
    public static void main(String[] args) {
        TreeMap<String, String> pengembangApp = new TreeMap<>();

        pengembangApp.put("Microsoft", "Bill Gates");
        pengembangApp.put("Apple", "Steven Paul Jobs");
        pengembangApp.put("Linux", "Linux Benedict Torvalds");
        pengembangApp.put("Facebook", "Mark Zuckerberg");
        pengembangApp.put("Twitter", "Jack Dorsey");
        pengembangApp.put("Instagram", "Kevin Systrom");

        int nomor = 1;
        for (String key : pengembangApp.keySet()) {
            String value = pengembangApp.get(key);

            System.out.println(nomor + ". " + key.toUpperCase() + " dikembangkan oleh " + value);
            nomor++;
        }
    }
}
