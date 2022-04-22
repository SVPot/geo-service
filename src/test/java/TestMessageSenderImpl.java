import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class TestMessageSenderImpl {

    static GeoService geoService;
    static LocalizationService localizationService;
    static MessageSender messageSender;

    @BeforeAll
    public static void initSuite() {

        geoService = mock(GeoServiceImpl.class);

        when(geoService.byIp(startsWith("172."))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(geoService.byIp(startsWith("96."))).thenReturn(new Location("New York", Country.USA, null, 0));

        localizationService = mock(LocalizationServiceImpl.class);

        when(localizationService.locale(RUSSIA)).thenReturn("Добро пожаловать");
        when(localizationService.locale(USA)).thenReturn("Welcome");

        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Test for 'MessageSenderImpl' complete!");
    }

    @Test
    @DisplayName("Test send() for RUSSIA IP:")
    public void sendRussiaIpTest() {

        Map<String, String> headers = new HashMap<>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.1.1.1");

        assertEquals("Добро пожаловать", messageSender.send(headers));
        System.out.println();
    }

    @Test
    @DisplayName("Test send() for USA IP:")
    public void sendUsaIpTest() {

        Map<String, String> headers = new HashMap<>();

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.1.1.1");

        assertEquals("Welcome", messageSender.send(headers));
        System.out.println();
    }
}