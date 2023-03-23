package budget;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import budget.purchases.Purchase;
import budget.purchases.PurchaseType;
import budget.purchases.PurchasesData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class FileManager {
    Gson gson = new Gson();
    private final File fileName = new File("purchases.txt");
    private static PurchasesData downloadedPurchases;

    public void saveData(Map<PurchaseType, List<Purchase>> allPurchases, double balance) {
        Map<PurchaseType, List<Purchase>> purchases = new HashMap<>(allPurchases);
        purchases.remove(PurchaseType.ALL);
        PurchasesData purchasesData = new PurchasesData(purchases, balance);
        try (FileWriter fileWriter = new FileWriter(this.fileName)){
            Gson gson = new Gson();
            gson.toJson(purchasesData, fileWriter);
            System.out.println("Purchases were saved! - create new file");
        } catch (IOException e) {
            System.out.println("Cannot create the file: " + fileName.getPath());
        }
    }

    public void loadData() {
        try (FileReader fileReader = new FileReader(this.fileName)) {
            PurchasesData purchasesData = gson.fromJson(fileReader, PurchasesData.class);
            downloadedPurchases = purchasesData;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PurchasesData getDownloadedPurchasesList() {
        return downloadedPurchases;
    }


    private JsonObject createJson(Map<PurchaseType, List<Purchase>> purchaseTypeListMap, double balance) {
        JsonObject purchasesListJson = new JsonObject();
        for (Map.Entry<PurchaseType, List<Purchase>> entry : purchaseTypeListMap.entrySet()) {
            PurchaseType type = entry.getKey();
            List<Purchase> list = entry.getValue();
            if (type != PurchaseType.ALL){
                JsonArray jsonArray = new Gson().toJsonTree(list).getAsJsonArray();
                purchasesListJson.add(type.getLabel(), jsonArray);
            }
        }
        purchasesListJson.addProperty("balance", balance);
        return purchasesListJson;
    }

}
