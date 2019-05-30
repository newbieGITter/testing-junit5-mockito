package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
	public void testFindById() {
		Speciality speciality = new Speciality();
		
		Mockito.when(specialtyRepo.findById(1l)).thenReturn(Optional.of(speciality));
		
		Speciality specialityValue = service.findById(1l);
		
		assertThat(specialityValue).isNotNull();
		Mockito.verify(specialtyRepo, times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	public void testFindByIdBddTest() {
		Speciality speciality = new Speciality();
		
		// Given
		//Mockito.when(specialtyRepo.findById(1l)).thenReturn(Optional.of(speciality));
		BDDMockito.given(specialtyRepo.findById(1L)).willReturn(Optional.of(speciality));
		
		// When
		Speciality specialityValue = service.findById(1l);
		
		// Then
		assertThat(specialityValue).isNotNull();
		//Mockito.verify(specialtyRepo, times(1)).findById(Mockito.anyLong());
		BDDMockito.then(specialtyRepo).should(times(1)).findById(Mockito.anyLong());
		BDDMockito.then(specialtyRepo).shouldHaveNoMoreInteractions();
	}
	
	@Test
	public void testDeleteByObject() {
		// Given
		Speciality speciality = new Speciality();
		
		// When
		service.delete(speciality);
		
		// Then
		//Mockito.verify(specialtyRepo).delete(Mockito.any(Speciality.class));
		BDDMockito.then(specialtyRepo).should(times(1)).delete(Mockito.any(Speciality.class));
	}
	
	@Test
	public void testDelete() {
		// Given
		Speciality speciality = new Speciality();
		
		// When
		service.delete(speciality);
		
		// Then
		//Mockito.verify(specialtyRepo).delete(speciality);
		// Mockito.verify(specialtyRepo, atLeastOnce()).delete(speciality);
		BDDMockito.then(specialtyRepo).should(times(1)).delete(speciality);
		
	}

	@Test
	public void testDeleteById() {
		
		// When
		service.deleteById(1L);
		service.deleteById(1L);
		
		// Then		
		Mockito.verify(specialtyRepo, times(2)).deleteById(1L);
		//Mockito.verify(specialtyRepo, Mockito.atMost(3)).deleteById(1L);
		//Mockito.verify(specialtyRepo,  times(0)).delete(Mockito.any(Speciality.class));
		BDDMockito.then(specialtyRepo).should(atLeastOnce()).deleteById(1L);
		BDDMockito.then(specialtyRepo).should(times(2)).deleteById(1L);
	}
	
	@Test
	public void testDoThrow() {
		doThrow(new RuntimeException("Some exception")).when(specialtyRepo).delete(Mockito.any());
		
		assertThrows(RuntimeException.class, () -> specialtyRepo.delete(new Speciality()));
		
		BDDMockito.then(specialtyRepo).should(times(1)).delete(Mockito.any());
	}
	
	@Test
	public void testFindByIdThrows() {
		BDDMockito.given(specialtyRepo.findById(1L)).willThrow(new RuntimeException("Some exception"));
		
		assertThrows(RuntimeException.class, () -> service.findById(1L));
		
		BDDMockito.then(specialtyRepo).should(times(1)).findById(1L);
	}

}
