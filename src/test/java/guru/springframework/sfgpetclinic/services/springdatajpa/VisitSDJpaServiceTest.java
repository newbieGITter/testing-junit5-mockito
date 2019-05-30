package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;

@ExtendWith(MockitoExtension.class)
public class VisitSDJpaServiceTest {

	@InjectMocks
	private VisitSDJpaService service;
	
	@Mock
	private VisitRepository visitRepo;

	@Test
	public void testFindAll() {
		Visit visit = new Visit();
		Set<Visit> visits = new HashSet<>();
		visits.add(visit);
		
		// Given
		//Mockito.when(visitRepo.findAll()).thenReturn(visits);
		BDDMockito.given(visitRepo.findAll()).willReturn(visits);
		
		Set<Visit> returnedSetOfVisits = service.findAll();
		
		Assertions.assertEquals(returnedSetOfVisits.size(), 1L);;
		//Mockito.verify(visitRepo, times(1)).findAll();
		BDDMockito.then(visitRepo).should(times(1)).findAll();
	}
	
	@Test
	public void testFindById() {
		Visit visit = new Visit();
		
		Long visitId = 1L;
		Mockito.when(visitRepo.findById(visitId)).thenReturn(Optional.of(visit));
		
		Visit returnedVisit = service.findById(visitId);
		
		//Mockito.verify(visitRepo, times(1)).findById(visitId);
		BDDMockito.then(visitRepo).should(times(1)).findById(Mockito.anyLong());
		assertThat(returnedVisit).isNotNull();
	}
	
	@Test
	public void testSave(){ 
		Visit visit = new Visit();
		
		Mockito.when(visitRepo.save(Mockito.any(Visit.class))).thenReturn(visit);
		
		Visit savedVisit = service.save(visit);
		
		//Mockito.verify(visitRepo, times(1)).save(Mockito.any(Visit.class));
		BDDMockito.then(visitRepo).should(times(1)).save(Mockito.any(Visit.class));
		assertThat(savedVisit).isNotNull();
	}
	
	@Test
	public void testDelete() {
		Visit visit = new Visit();
		
		service.delete(visit);
		
		Mockito.verify(visitRepo, times(1)).delete(visit);
	}

	@Test
	public void testDeleteById() {
		long visitId = 1L;
		
		service.deleteById(visitId);
		
		Mockito.verify(visitRepo, times(1)).deleteById(visitId);
		BDDMockito.then(visitRepo).should(times(1)).deleteById(visitId);
	}

}
