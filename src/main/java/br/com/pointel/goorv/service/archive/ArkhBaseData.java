package br.com.pointel.goorv.service.archive;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emuvi
 */
public class ArkhBaseData implements Closeable {

    private final ArkhBase arkhBase;
    private final Connection connection;

    public ArkhBaseData(ArkhBase arkhBase) throws Exception {
        this.arkhBase = arkhBase;
        this.connection = DriverManager.getConnection("jdbc:sqlite:"
                + new File(this.arkhBase.root, "arkhbase.sdb").getAbsolutePath());
        this.initDatabase();
    }

    public ArkhBaseUnit getByPlace(String place) throws Exception {
        var select = this.connection.prepareStatement(
                "SELECT place, modified, verifier "
                + "FROM files "
                + "WHERE place = ?");
        select.setString(1, place);
        var returned = select.executeQuery();
        if (returned.next()) {
            return new ArkhBaseUnit(
                    returned.getString("place"),
                    returned.getLong("modified"),
                    returned.getString("verifier")
            );
        } else {
            return null;
        }
    }

    public List<ArkhBaseUnit> getByVerifier(String verifier) throws Exception {
        var select = this.connection.prepareStatement(
                "SELECT place, modified, verifier "
                + "FROM files "
                + "WHERE verifier = ?");
        select.setString(1, verifier);
        var returned = select.executeQuery();
        var results = new ArrayList<ArkhBaseUnit>();
        while (returned.next()) {
            results.add(new ArkhBaseUnit(
                    returned.getString("place"),
                    returned.getLong("modified"),
                    returned.getString("verifier")
            ));
        }
        return results;
    }

    public List<ArkhBaseUnit> getAll() throws Exception {
        var select = this.connection.prepareStatement(
                "SELECT place, modified, verifier "
                + "FROM files");
        var returned = select.executeQuery();
        var results = new ArrayList<ArkhBaseUnit>();
        while (returned.next()) {
            results.add(new ArkhBaseUnit(
                    returned.getString("place"),
                    returned.getLong("modified"),
                    returned.getString("verifier")
            ));
        }
        return results;
    }

    public List<String> getAllPlaces() throws Exception {
        var select = this.connection.prepareStatement(
                "SELECT place "
                + "FROM files");
        var returned = select.executeQuery();
        var results = new ArrayList<String>();
        while (returned.next()) {
            results.add(returned.getString("place"));
        }
        return results;
    }

    public void putFile(ArkhBaseUnit file) throws Exception {
        this.putFile(file.place, file.modified, file.verifier);
    }

    public void putFile(String place, Long modified, String verifier) throws Exception {
        var delete = this.connection.prepareStatement(
                "DELETE FROM files "
                + "WHERE place = ?");
        delete.setString(1, place);
        delete.executeUpdate();
        var insert = this.connection.prepareStatement(
                "INSERT INTO files "
                + "(place, modified, verifier) "
                + "VALUES (?, ?, ?)");
        insert.setString(1, place);
        insert.setLong(2, modified);
        insert.setString(3, verifier);
        var results = insert.executeUpdate();
        if (results == 0) {
            throw new Exception("Could not put the file.");
        }
    }

    public void delFolder(String place) throws Exception {
        var delete = this.connection.prepareStatement(
                "DELETE FROM files "
                + "WHERE place LIKE ?");
        delete.setString(1, place + "%");
        delete.executeUpdate();
    }

    public void delFile(String place) throws Exception {
        var delete = this.connection.prepareStatement(
                "DELETE FROM files "
                + "WHERE place = ?");
        delete.setString(1, place);
        delete.executeUpdate();
    }

    public void moveFolder(String fromPlace, String toPlace) throws Exception {
        var select = this.connection.prepareStatement(
                "SELECT place "
                + "FROM files "
                + "WHERE place LIKE ?");
        select.setString(1, fromPlace + "%");
        var returned = select.executeQuery();
        while (returned.next()) {
            var oldPlace = returned.getString("place");
            var newPlace = toPlace + oldPlace.substring(fromPlace.length());
            var update = this.connection.prepareStatement(
                "UPDATE files "
                + "SET place = ? "
                + "WHERE place = ?");
            update.setString(1, newPlace);
            update.setString(2, oldPlace);
            update.executeUpdate();
        }
    }

    public void moveFile(String fromPlace, String toPlace) throws Exception {
        var update = this.connection.prepareStatement(
                "UPDATE files "
                + "SET place = ? "
                + "WHERE place = ?");
        update.setString(1, toPlace);
        update.setString(2, fromPlace);
        update.executeUpdate();
    }

    private void initDatabase() throws Exception {
        this.connection.createStatement().execute(
                "CREATE TABLE "
                + "IF NOT EXISTS "
                + "files (place TEXT PRIMARY KEY, "
                + "modified INTEGER, verifier TEXT)");
        this.connection.createStatement().execute(
                "CREATE INDEX "
                + "IF NOT EXISTS "
                + "files_verifier ON "
                + "files (verifier)");
    }

    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
