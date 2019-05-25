package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.repositories.VetRepository;

@ExtendWith(MockitoExtension.class)
public class VetSDJpaServiceTest {
	
	@InjectMocks
	private VetSDJpaService service;
	
	@Mock
	private VetRepository vetRepo;
	
	@Test
	public void testDeleteById() {
		service.deleteById(1L);
		
		Mockito.verify(vetRepo, times(1)).deleteById(1L);
	}

}
