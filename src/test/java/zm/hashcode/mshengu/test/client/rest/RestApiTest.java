/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.client.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.conf.WebConfig;
import zm.hashcode.mshengu.client.rest.api.controllers.ApiRestController;
import zm.hashcode.mshengu.services.products.SiteService;

/**
 *
 * @author boniface
 */
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class RestApiTest {

    @Autowired
    private SiteService siteService;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @BeforeMethod
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ApiRestController()).build();
    }

    //  @Test
    public void getSites() throws Exception {
        this.mockMvc.perform(get("/api/sites/2"))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(forwardedUrl("/index"));
    }

//    @Test
    public void postEmptyData() throws Exception {
        this.mockMvc.perform(post("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("formDTO", "messageFromUser"))
                .andExpect(forwardedUrl("/home"));
    }

//    @Test
    public void postSomething() throws Exception {

        this.mockMvc.perform(post("/").param("messageFromUser", "hello"))
                .andDo(print())
                .andExpect(status().isMovedTemporarily()) // 302 redirect
                .andExpect(model().hasNoErrors())
                .andExpect(flash().attributeExists("message"))
                .andExpect(redirectedUrl("/"));
    }
}
