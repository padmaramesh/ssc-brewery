package guru.sfg.brewery.web.controllers;

import guru.sfg.brewery.repositories.BeerInventoryRepository;
import guru.sfg.brewery.repositories.BeerRepository;
import guru.sfg.brewery.repositories.CustomerRepository;
import guru.sfg.brewery.services.BeerService;
import guru.sfg.brewery.services.BreweryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class BeerControllerIT extends BaseIT {

    @Test
    void initCreateFormBcrypt()throws Exception{
        mockMvc.perform(get("/beers/new")
                .with(httpBasic("spring","guru")))
                .andExpect(status().isOk())
                .andExpect(view().name("beers/createBeer"));
    }

    @Test
    void initCreateFormSha256()throws Exception{
        mockMvc.perform(get("/beers/new")
                .with(httpBasic("ramesh","padma")))
                .andExpect(status().isOk())
                .andExpect(view().name("beers/createBeer"));
    }


    @Test
    void initCreateForm()throws Exception{
        mockMvc.perform(get("/beers/new")
                .with(httpBasic("user","ramesh")))
                .andExpect(status().isOk())
                .andExpect(view().name("beers/createBeer"));
    }

    @WithMockUser("spring")
    @Test
    void findBeers() throws Exception{
        mockMvc.perform(get("/beers/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("beers/findBeers"))
                .andExpect(model().attributeExists("beer"));
    }

    @Test
    void findBeersUsingHttpBasic()throws Exception{
        mockMvc.perform(get("/beers/find").with(httpBasic("ramesh","padma")))
                .andExpect(status().isOk())
                .andExpect(view().name("beers/findBeers"))
                .andExpect(model().attributeExists("beer"));
    }
}
