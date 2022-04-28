package com.mood.mood.config;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InsertDataBDD {

    private static Logger LOGGER = Logger.getLogger(InsertDataBDD.class.getName());

    //@Value("${datasource.url}")
    public static String Url = "jdbc:postgresql://localhost:5432/mood?createDatabaseIfNotExit=true";

    //@Value("${datasource.username}")
    public static String Username = "root";

    //@Value("${datasource.password}")
    public static String Password = "root";

    public static void main(String[] args) {

        /**
         * **************************ROLES*****************************************
         */

        String queryRolesAdmin =  "INSERT INTO roles (id, title) values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryRolesAdmin)) {
            LOGGER.log(Level.INFO, "Insert ROLE ADMIN");

            pst.setInt(1, 1);
            pst.setString(2, "ROLE_ADMIN");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryRolesEditor =  "INSERT INTO roles (id, title) values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryRolesEditor)) {
            pst.setInt(1, 2);
            pst.setString(2, "ROLE_EDITOR");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryRolesModerator =  "INSERT INTO roles (id, title) values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryRolesModerator)) {

            pst.setInt(1, 3);
            pst.setString(2, "ROLE_MODERATOR");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryRolesUser =  "INSERT INTO roles (id, title) values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryRolesUser)) {

            pst.setInt(1, 4);
            pst.setString(2, "ROLE_USER");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryRolesEstablishment =  "INSERT INTO roles (id, title) values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryRolesEstablishment)) {

            pst.setInt(1, 5);
            pst.setString(2, "ROLE_ESTABLISHMENT");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************MOOD*****************************************
         */
        String queryMood =  "INSERT INTO categories (id, title, description) values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryMood)) {
            LOGGER.log(Level.INFO, "Insert CATEGORY MOOD");

            pst.setInt(1, 1);
            pst.setString(2, "beer");
            pst.setString(3, "ambiance posée");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************LOCALISATION*****************************************
         */


        String queryAdminLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryAdminLocalosation)) {
            LOGGER.log(Level.INFO, "Insert LOCALISATION Admin GEOLOC");

            pst.setInt(1, 1);
            pst.setDouble(2, Double.parseDouble("49.183839"));
            pst.setDouble(3, Double.parseDouble("-0.359033"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryEstablishmentCentreCommercialLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCentreCommercialLocalosation)) {

            pst.setInt(1, 2);
            pst.setDouble(2, Double.parseDouble("49.21074"));
            pst.setDouble(3, Double.parseDouble("-0.364667"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryEstablishmentCampingSandrayaLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCampingSandrayaLocalosation)) {

            pst.setInt(1, 3);
            pst.setDouble(2, Double.parseDouble("49.325009"));
            pst.setDouble(3, Double.parseDouble("-0.388346"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentCreteilSoleilLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCreteilSoleilLocalosation)) {

            pst.setInt(1, 4);
            pst.setDouble(2, Double.parseDouble("48.779024"));
            pst.setDouble(3, Double.parseDouble("2.454933"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************IMAGES*****************************************
         */

        String queryDefaultImage =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryDefaultImage)) {
            LOGGER.log(Level.INFO, "Insert IMAGE Default image");

            File img = new File("src/main/resources/image/default_image.png");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 1);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "default_image.png");
                pst.setString(4, "image/png");
                pst.setInt(5, 7);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateDefaultImage =  "UPDATE images SET (data_name, mime_type) = (?, ?) WHERE id=1";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateDefaultImage)) {
            pst.setString(1, "default_image.png");
            pst.setString(2, "image/png");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************USER*****************************************
         */
        String queryUserAdmin =  "INSERT INTO users (id,  name, firstname, birthdate, email, password, phone, localisation_id, mood_id, role_id)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUserAdmin)) {

            LOGGER.log(Level.INFO, "Insert User Admin");

            String date="2001-01-08";

            pst.setInt(1, 1);
            pst.setString(2, "toto");
            pst.setString(3, "titi");
            pst.setDate(4, Date.valueOf(date));
            pst.setString(5, "toto@gmail.com");
            pst.setString(6, "1234");
            pst.setString(7,"0625252525");
            pst.setInt(8,1);
            pst.setInt(9,1);
            pst.setInt(10,1);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * ****************************INSERT USER ADMIN IMAGE***********************************
         */
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             Statement st = con.createStatement()) {

            LOGGER.log(Level.INFO, "Insert Admin IMAGE");

            con.setAutoCommit(false);

            st.addBatch("INSERT INTO user_image (id,  user_id)  values (1,1)");

            int counts[] = st.executeBatch();

            st.executeUpdate("UPDATE users SET image_id = 1 "
                    + "WHERE Id = 1");

            con.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************ESTABLISHEMENT*****************************************
         */

        String queryEstablishmentCentreCommercial =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
            try (Connection con = DriverManager.getConnection(Url, Username, Password);
        PreparedStatement pst = con.prepareStatement(queryEstablishmentCentreCommercial)) {

                LOGGER.log(Level.INFO, "Insert Establisment ");

            pst.setInt(1, 1);
            pst.setString(2, "Achats en magasin");
            pst.setString(3, "Centre commercial Côte de Nacre");
            pst.setBoolean(4, false);
            pst.setInt(5, 1);
            pst.setInt(6, 2);
            pst.executeUpdate();

        } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentCampingSadaya =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCampingSadaya)) {

            pst.setInt(1, 2);
            pst.setString(2, "Emplacements et chalets à louer dans vaste camping avec parc aquatique couvert, restaurant et aire de jeux.");
            pst.setString(3, "Camping Sandaya Côte de Nacre");
            pst.setBoolean(4, true);
            pst.setInt(5, 1);
            pst.setInt(6, 3);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryEstablishmentCreteilSoleil =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCreteilSoleil)) {

            pst.setInt(1, 3);
            pst.setString(2, "Ce grand centre commercial équipé en wi-fi propose le prêt de poussettes et est doté d'un cinéma de 12 salles.");
            pst.setString(3, "Créteil Soleil");
            pst.setBoolean(4, true);
            pst.setInt(5, 1);
            pst.setInt(6, 4);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        /**
         * *************************************************************************************************************************
         */

        /**
         * //////////////////////////////////////////////////////////////PROCEDURE & TRIGGER/////////////////////////////////////////////////////////
         */

        /**
         * **************************************************************************************************************************
         */

        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             Statement st = con.createStatement()) {

            LOGGER.log(Level.INFO, "CREATE TRIGGER");

            con.setAutoCommit(false);

            st.addBatch("DROP PROCEDURE IF EXISTS prc_default_image;");

            con.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
