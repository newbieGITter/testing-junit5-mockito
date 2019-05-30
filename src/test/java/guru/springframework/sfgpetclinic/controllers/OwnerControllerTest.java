package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	
	@InjectMocks
	private OwnerController controller;
	
	@Mock
	private BindingResult result;
	
	@Mock
	private OwnerService ownerService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testProcessCreationForm_withErrors() {
		// Given
		BDDMockito.given(result.hasErrors()).willReturn(Boolean.TRUE);
		
		// When
		String viewName = controller.processCreationForm(owner(), result);
		
		// Then
		assertThat(viewName).isEqualTo(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
	}
	
	@Test
	void testProcessCreationForm_withNoErrors() {
		// Given 
		BDDMockito.given(result.hasErrors()).willReturn(Boolean.FALSE);
		Owner owner = owner();
		BDDMockito.given(ownerService.save(Mockito.any(Owner.class))).willReturn(owner);
		
		// When		
		String viewName = controller.processCreationForm(owner, result);
		
		// Then
		assertThat(viewName).isEqualTo("redirect:/owners/" + owner.getId());
		BDDMockito.then(ownerService).should(times(1)).save(Mockito.any(Owner.class));
	}

	private Owner owner() {
		Owner owner = new Owner(1L, "Jack", "Ma");
		owner.setCity("Bangalore");
		return owner;
	}

}
