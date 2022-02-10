package com.example.storageservice;

import com.example.storageservice.allProducts.AllProductsService;
import com.example.storageservice.storage.StorageInfoList;
import com.example.storageservice.storage.StorageInfo;
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
public class StorageInfoIntegrationTest {

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
    public void shouldReturnAllStorageInfo() throws Exception {
        UUID testItemId = UUID.randomUUID();

        List<StorageInfo> storageInfo = new ArrayList<>();
        storageInfo.add(new StorageInfo(testItemId, 5465785L, 5, "Berlin"));
        StorageInfoList dil = new StorageInfoList(storageInfo);
        when(storageService.getAll()).thenReturn(dil);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/storage/all"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageInfoList[0].itemId").value(testItemId.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageInfoList[0].deliveryTime").value(5465785L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageInfoList[0].amount").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.storageInfoList[0].location").value("Berlin"));
    }

    @Test
    public void shouldReturnStorageInfoForSpecifiedId() throws Exception {
        UUID testItemId_1 = UUID.randomUUID();
        UUID testItemId_2 = UUID.randomUUID();

        List<StorageInfo> storageInfo = new ArrayList<>();
        storageInfo.add(new StorageInfo(testItemId_1, 5465785L, 5, "Berlin"));
        storageInfo.add(new StorageInfo(testItemId_2, 4475785L, 3, "Moscow"));
        StorageInfoList dil = new StorageInfoList(storageInfo);
        when(storageService.getStorageInfoByID(testItemId_2)).thenReturn(dil.getStorageInfoList().get(1));

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
