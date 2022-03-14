import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    @Test
    void dikstra() {
    Network N=new Network();
    N.initialize();
    assertEquals(40,N.dikstra(0,5));

    }
}