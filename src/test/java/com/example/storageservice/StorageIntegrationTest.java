package com.example.storageservice;

import com.example.storageservice.allProducts.AllProductsService;
import com.example.storageservice.model.DeliveryInfoList;
import com.example.storageservice.storage.Storage;
import com.example.storageservice.storage.StorageController;
import com.example.storageservice.storage.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(StorageController.class)
public class StorageIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StorageService storageService;

    @MockBean
    private AllProductsService allProductsService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void shouldReturnAllDeliveryInfo() throws Exception {
        UUID testItemId = UUID.randomUUID();

        List<Storage> storage = new ArrayList<>();
        storage.add(new Storage(testItemId, 5465785L, 5, "Berlin"));
        DeliveryInfoList dil = new DeliveryInfoList(storage);
        when(storageService.getAll()).thenReturn(dil);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/storage/all"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageList[0].itemId").value(testItemId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageList[0].deliveryTime").value(5465785L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageList[0].amount").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageList[0].location").value("Berlin"));
    }

    @Test
    public void shouldReturnDeliveryInfoForSpecifiedId() throws Exception {
        UUID testItemId_1 = UUID.randomUUID();
        UUID testItemId_2 = UUID.randomUUID();

        List<Storage> storage = new ArrayList<>();
        storage.add(new Storage(testItemId_1, 5465785L, 5, "Berlin"));
        storage.add(new Storage(testItemId_2, 4475785L, 3, "Moscow"));
        DeliveryInfoList dil = new DeliveryInfoList(storage);
        when(storageService.getDeliveryInfoByID(testItemId_2)).thenReturn(dil.getStorageList().get(1));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/storage/" + testItemId_2))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemId").value(testItemId_2.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deliveryTime").value(4475785L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Moscow"));
    }

}
