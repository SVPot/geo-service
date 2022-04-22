import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class TestLocalizationServiceImpl {

    static LocalizationService localizationService;

    @BeforeAll
    public static void initSuite() {

        localizationService = mock(LocalizationServiceImpl.class);

        when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        when(localizationService.locale(USA)).thenReturn("Welcome");

        System.out.println("Start tests 'LocalizationServiceImpl':");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Tests 'LocalizationServiceImpl' complete!");
    }

    @Test
    @DisplayName("Test locale() for RUSSIA:")
    public void russiaLocaleTest() {
        assertEquals(localizationService.locale(RUSSIA), "Добро пожаловать");
    }

    @Test
    @DisplayName("Test locale() for USA:")
    public void usaLocaleTest() {
        assertEquals(localizationService.locale(USA), "Welcome");
    }
}