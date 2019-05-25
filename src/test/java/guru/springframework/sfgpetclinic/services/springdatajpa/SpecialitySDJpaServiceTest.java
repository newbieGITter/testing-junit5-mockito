package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
public class SpecialitySDJpaServiceTest {

	@InjectMocks
	private SpecialitySDJpaService service;
	
	@Mock
	private SpecialtyRepository specialtyRepo;
	
	@Test
	public void testDelete() {
		Speciality speciality = new Speciality();
		service.delete(speciality);
		
		//Mockito.verify(specialtyRepo).delete(speciality);
		Mockito.verify(specialtyRepo, atLeastOnce()).delete(speciality);
		
	}

	@Test
	public void testDeleteById() {
		service.deleteById(1L);
		service.deleteById(1L);
		
		Mockito.verify(specialtyRepo, times(2)).deleteById(1L);
		Mockito.verify(specialtyRepo, Mockito.atMost(3)).deleteById(1L);
		Mockito.verify(specialtyRepo,  times(0)).delete(new Speciality());
	}

}
