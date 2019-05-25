package guru.springframework.sfgpetclinic;

import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InlineMockTest {
	
	@Test
	public void testInlineMock() {
		@SuppressWarnings("unchecked")
		Map<String, Object> mapMock = mock(Map.class);
		
		Assertions.assertEquals(mapMock.size(), 0);
	}

}
