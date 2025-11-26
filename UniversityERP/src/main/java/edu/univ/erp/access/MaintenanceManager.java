package edu.univ.erp.access;

import edu.univ.erp.data.db.ERPDBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MaintenanceManager
 *
 * - Stores a single global flag "maintenance_mode" in the settings table.
 * - value = 'ON' or 'OFF'
 *
 * Required table (in ERP DB):
 *
 *   CREATE TABLE IF NOT EXISTS settings (
 *       key   TEXT PRIMARY KEY,
 *       value TEXT
 *   );
 *
 * No bonus features here, just a simple global toggle.
 */
public final class MaintenanceManager {

    private static final String KEY_MAINTENANCE = "maintenance_mode";
    private static final String VALUE_ON  = "ON";
    private static final String VALUE_OFF = "OFF";

    private MaintenanceManager() {
        // utility class
    }

    /**
     * Check if maintenance mode is currently ON.
     *
     * If any DB error happens, it defaults to false (OFF).
     */
    public static boolean isMaintenanceMode() {
        String sql = "SELECT value FROM settings WHERE key = ?";

        try (Connection conn = ERPDBConnect.getConnection_toErp();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, KEY_MAINTENANCE);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String value = rs.getString("value");
                    return VALUE_ON.equalsIgnoreCase(value);
                } else {
                    // No row found -> treat as OFF
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error checking maintenance mode: " + e.getMessage());
            return false; // fail-safe: assume OFF so system remains usable
        }
    }

    /**
     * Set maintenance mode to ON or OFF.
     *
     * @param on true -> ON, false -> OFF
     * @return true if update/insert succeeded, false otherwise
     */
    public static boolean setMaintenanceMode(boolean on) {
        String newValue = on ? VALUE_ON : VALUE_OFF;

        // We will try UPDATE first, if no row updated, then INSERT.
        String updateSql = "UPDATE settings SET value = ? WHERE key = ?";
        String insertSql = "INSERT INTO settings(key, value) VALUES(?, ?)";

        try (Connection conn = ERPDBConnect.getConnection_toErp()) {

            // Try UPDATE
            try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                psUpdate.setString(1, newValue);
                psUpdate.setString(2, KEY_MAINTENANCE);

                int affected = psUpdate.executeUpdate();
                if (affected > 0) {
                    return true; // updated existing row
                }
            }

            // No existing row, do INSERT
            try (PreparedStatement psInsert = conn.prepareStatement(insertSql)) {
                psInsert.setString(1, KEY_MAINTENANCE);
                psInsert.setString(2, newValue);
                int inserted = psInsert.executeUpdate();
                return inserted > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error setting maintenance mode: " + e.getMessage());
            return false;
        }
    }

    /**
     * Convenience method for UI: returns "ON" or "OFF".
     */
    public static String getMaintenanceStatusLabel() {
        return isMaintenanceMode() ? VALUE_ON : VALUE_OFF;
    }
}
