package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HttpJsonDynamicUnitTest {
    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    
    @Autowired    
    private MockMvc mockMvc;

    /**
     *
     * @throws Exception
     *
     * It tests finding a country by phone_number
     */
    @Test
    @Order(1)
    public void findProductByISD() throws Exception {
       
        String res = "[{\"country\":\"Canada\",\"country_code\":\"CA\",\"isd\":\"+1\"},{\"country\":\"United States\",\"country_code\":\"US\",\"isd\":\"+1\"}]";

        assertTrue(
                ResultMatcher.matchJson(
                        mockMvc.perform(get("/phone_number/+18777481585"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        res,
                        true
                )
        );

        res = "[{\"country\":\"Benin (Former Dahomey)\",\"country_code\":\"BJ\",\"isd\":\"+229\"}]";
        assertTrue(
                ResultMatcher.matchJson(
                        mockMvc.perform(get("/phone_number/+2298777481585"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        res,
                        true
                )
        );
    }

    @Test
    @Order(2)
    public void findProductByNonExistingId() throws Exception {
        /**
         *
         * Find product by non-existing id 25
         */
        mockMvc.perform(get("/products/87576487787554548"))
                .andExpect(status().isNotFound());
    }






    
    
}
