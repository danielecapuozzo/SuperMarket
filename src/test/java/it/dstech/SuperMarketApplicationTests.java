package it.dstech;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.dstech.models.Categoria;
import it.dstech.models.Prodotto;
import it.dstech.models.Unita;
import it.dstech.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SuperMarketApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void saveOrUpdateProdotto() throws JsonProcessingException, Exception {

		List<Prodotto> lista = new ArrayList<>();

		Categoria categoria=Categoria.ALIMENTI;
		
		Unita unita = Unita.PEZZO;
					
		LocalDate localDate = LocalDate.of(2017,04,28);

		Prodotto prodotto = new Prodotto("pollo","aia", localDate ,categoria, 10, 1, unita,220,210,224,"img", 217);

		lista.add(prodotto);
		

		mockMvc.perform(get("/prodotto/save").content(mapper.writeValueAsString(lista))
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isCreated()).andDo(print());
	}
	
	@Test
	public void findAllTest() throws Exception {

		mockMvc.perform(get("/prodotto/findAll")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
	}
	
	@Test
	public void findByMarcaTest() throws Exception {

		mockMvc.perform(get("/prodotto/findByCategoria/alimenti")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
	}
	
	
}
