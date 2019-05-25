package guru.springframework.sfgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
public class SpecialitySDJpaServiceTest {

	@InjectMocks
	private SpecialitySDJpaService service;
	
	@Mock
	private SpecialtyRepository specialtyRepo;
	
	@Disabled
	@Test
	public void testDelete() {
		service.delete(new Speciality());
		
	}

	@Test
	public void testDeleteById() {
		service.deleteById(1L);
	}

}
