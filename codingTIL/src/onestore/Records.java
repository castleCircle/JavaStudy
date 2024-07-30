package onestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Records {

  static class ItemReorderRate {

    String productId;
    double reorderRate;

    ItemReorderRate(String productId, double reorderRate) {
      this.productId = productId;
      this.reorderRate = reorderRate;
    }
  }

  public static void main(String[] args) throws ParseException {
    String[] records = {
        "2020-02-02 uid1 pid1",
        "2020-02-26 uid1 pid1",
        "2020-02-26 uid2 pid1",
        "2020-02-27 uid3 pid2",
        "2020-02-28 uid4 pid2",
        "2020-02-29 uid3 pid3",
        "2020-03-01 uid4 pid3",
        "2020-03-03 uid1 pid1",
        "2020-03-04 uid2 pid1",
        "2020-03-05 uid3 pid2",
        "2020-03-05 uid3 pid3",
        "2020-03-05 uid3 pid3",
        "2020-03-06 uid1 pid4"
    };
    int k = 10;
    String date = "2020-03-05";

    String[] result = solution(records, k, date);
    System.out.println(Arrays.toString(result));

    String[] records2 = {
        "2020-01-01 uid1000 pid5000"
    };
    int k2 = 10;
    String date2 = "2020-01-11";

    String[] result2 = solution(records2, k2, date2);
    System.out.println(Arrays.toString(result2));

    String[] records3 = {
        "2020-02-02 uid141 pid141",
        "2020-02-03 uid141 pid32",
        "2020-02-04 uid32 pid32",
        "2020-02-05 uid32 pid141"
    };
    int k3 = 10;
    String date3 = "2020-02-05";

    String[] result3 = solution(records3, k3, date3);
    System.out.println(Arrays.toString(result3));
  }


  public static String[] solution(String[] records, int k, String date) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date endDate = sdf.parse(date);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(endDate);
    calendar.add(Calendar.DATE, -k + 1);
    Date startDate = calendar.getTime();

    Map<String, Set<String>> productToUsers = new HashMap<>();
    Map<String, Integer> productToRepeatCustomers = new HashMap<>();

    for (String record : records) {
      String[] parts = record.split(" ");
      Date purchaseDate = sdf.parse(parts[0]);
      String userId = parts[1];
      String productId = parts[2];

      if (!purchaseDate.before(startDate) && !purchaseDate.after(endDate)) {
        productToUsers.putIfAbsent(productId, new HashSet<>());
        productToUsers.get(productId).add(userId);

        productToRepeatCustomers.putIfAbsent(productId, 0);
        if (productToUsers.get(productId).contains(userId)) {
          productToRepeatCustomers.put(productId, productToRepeatCustomers.get(productId) + 1);
        }
      }
    }

    final List<ItemReorderRate> reorderRates = new ArrayList<>();
    for (String productId : productToUsers.keySet()) {
      int totalCustomers = productToUsers.get(productId).size();
      int repeatCustomers = productToRepeatCustomers.getOrDefault(productId, 0);
      double reorderRate = (double) repeatCustomers / totalCustomers * 100;
      reorderRates.add(new ItemReorderRate(productId, reorderRate));
    }

    reorderRates.sort((a, b) -> {
      if (Double.compare(b.reorderRate, a.reorderRate) != 0) {
        return Double.compare(b.reorderRate, a.reorderRate);
      } else {
        return extractNumber(a.productId) - extractNumber(b.productId);
      }
    });

    if (reorderRates.isEmpty()) {
      return new String[]{"no result"};
    }

    String[] result = new String[reorderRates.size()];
    for (int i = 0; i < reorderRates.size(); i++) {
      result[i] = reorderRates.get(i).productId;
    }

    return result;
  }

  private static int extractNumber(String productId) {
    return Integer.parseInt(productId.replaceAll("\\D", ""));
  }

}
