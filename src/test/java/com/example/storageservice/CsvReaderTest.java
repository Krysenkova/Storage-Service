package com.example.storageservice;

import com.example.storageservice.allProducts.ProductAllInfo;
import com.example.storageservice.utils.CsvReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CsvReaderTest {
    private UUID testItemId_1 = UUID.randomUUID();
    private UUID testItemId_2 = UUID.randomUUID();
    private UUID testItemId_3 = UUID.randomUUID();

    private String filename = "testFile.csv";
    private String s1 = testItemId_1 + "; Fairy Lights; Bright chain of LED lights for indoor and outdoor use. 150 cm; glass; colourful; 0.1 kg.; 12.0; 14.280000000000001; 86400000; 9; Berlin, Germany";
    private String s2 = testItemId_2 + "; Candy cane; Traditional sugar candy in a form of a cane; sugar; red and white; 0.05 kg.; 2.0; 2.38; 86400000; 11; Berlin, Germany";
    private String s3 = testItemId_3 + "; Candle Arch; Electric arch with five candles; wood; beige; 0.2 kg.; 6.0; 7.140000000000001; 86400000; 13; Berlin, Germany";

    @Test
    void readInfoFromFileWithOneProductTest() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(s1);
        writer.close();

        CsvReader reader = new CsvReader();
        List<ProductAllInfo> productsFromFile = reader.readAllInfo(filename);
        ProductAllInfo expected = new ProductAllInfo(testItemId_1, "Fairy Lights", "Bright chain of LED lights for indoor and outdoor use. 150 cm", "glass", "colourful", "0.1 kg.", 12.0, 14.280000000000001, 86400000L, 9, "Berlin, Germany");
        Assertions.assertEquals(expected.toString(), productsFromFile.get(0).toString());
    }

    @Test
    void readInfoFromFileWithSeveralProductsTest() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(s1 + "\n" + s2 + "\n" + s3);
        writer.close();

        CsvReader reader = new CsvReader();
        List<ProductAllInfo> productsFromFile = reader.readAllInfo(filename);
        System.out.println(productsFromFile);
        Assertions.assertEquals(3, productsFromFile.size());
        Assertions.assertEquals("colourful", productsFromFile.get(0).getColour());
        Assertions.assertEquals("Candy cane", productsFromFile.get(1).getName());
        Assertions.assertEquals("wood", productsFromFile.get(2).getMaterial());
    }

    @AfterAll
    static void deleteTestFile() {
        File file = new File("testFile.csv");
        boolean success= file.delete();
    }
}
