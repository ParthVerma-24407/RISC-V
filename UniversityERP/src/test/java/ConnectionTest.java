import edu.univ.erp.data.DBmanager;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

// This class is just for testing. It is not part of the main app.
public class ConnectionTest {

    @Test
    public void testDatabaseConnections() {
        System.out.println("------------------------------------------------");
        System.out.println("TEST STARTING: Attempting to connect to MySQL...");
        System.out.println("------------------------------------------------");

        // 1. Try to connect to Auth DB
        try (Connection conn = DBmanager.getConnection_toAuth()) {
            if (conn != null) {
                System.out.println("✅ SUCCESS: Connected to 'univ_auth' database!");
            }
        } catch (Exception e) {
            System.out.println("❌ FAILED: Could not connect to 'univ_auth'.");
            System.out.println("Reason: " + e.getMessage());
        }

        // 2. Try to connect to ERP DB
        try (Connection conn = DBmanager.getConnection_toErp()) {
            if (conn != null) {
                System.out.println("✅ SUCCESS: Connected to 'univ_erp' database!");
            }
        } catch (Exception e) {
            System.out.println("❌ FAILED: Could not connect to 'univ_erp'.");
            System.out.println("Reason: " + e.getMessage());
        }

        System.out.println("------------------------------------------------");
        System.out.println("TEST FINISHED");
    }
}