import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;

public class TestGeoServiceImpl {

    static GeoService geoService;

    @BeforeAll
    public static void initSuite() {
        geoService = mock(GeoServiceImpl.class);

        when(geoService.byIp(LOCALHOST)).thenReturn(new Location(null, null, null, 0));
        when(geoService.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", RUSSIA, "Lenina", 15));
        when(geoService.byIp(NEW_YORK_IP)).thenReturn(new Location("New York", USA, " 10th Avenue", 32));
        when(geoService.byIp(startsWith("172."))).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(geoService.byIp(startsWith("96."))).thenReturn(new Location("New York", Country.USA, null, 0));

        System.out.println("Start tests 'GeoServiceImpl':");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Tests 'GeoServiceImpl' complete!");
    }

    @Test
    @DisplayName("Test localhost")
    public void test_localhost() {
        assertNull(geoService.byIp("127.0.0.1").getCountry());
    }

    @ParameterizedTest
    @DisplayName("Test RUSSIA IP ")
    @ValueSource(strings = {"172.2.2.2", "172.3.3.3"})
    public void test_russia_ip(String ip) {
        assertEquals(RUSSIA, geoService.byIp(ip).getCountry());
        System.out.println("Test IP " + ip + " complete!");
    }

    @ParameterizedTest
    @DisplayName("Test USA IP ")
    @ValueSource(strings = {"96.2.2.2", "96.3.3.3"})
    public void test_usa_ip(String ip) {
        assertEquals(USA, geoService.byIp(ip).getCountry());
        System.out.println("Test IP " + ip + " complete!");
    }
}