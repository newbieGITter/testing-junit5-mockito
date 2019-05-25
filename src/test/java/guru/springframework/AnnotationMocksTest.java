package guru.springframework;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AnnotationMocksTest {
	
	@Mock
	Map<String, Object> mapMock;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testMock() {
		mapMock.put("key", "foo");
	}

}
