package com.mood.mood.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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


    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); ;

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
        String queryMoodDrink =  "INSERT INTO categories (id, title, description) values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryMoodDrink)) {
            LOGGER.log(Level.INFO, "Insert CATEGORY MOOD");

            pst.setInt(1, 1);
            pst.setString(2, "drinks");
            pst.setString(3, "Apéro entre amis");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryMoodParty =  "INSERT INTO categories (id, title, description) values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryMoodParty)) {
            LOGGER.log(Level.INFO, "Insert CATEGORY MOOD");

            pst.setInt(1, 2);
            pst.setString(2, "party");
            pst.setString(3, "Pour faire la fête");
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryMoodChill =  "INSERT INTO categories (id, title, description) values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryMoodChill)) {
            LOGGER.log(Level.INFO, "Insert CATEGORY MOOD");

            pst.setInt(1, 3);
            pst.setString(2, "chill");
            pst.setString(3, "Pour une soirée a deux");
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
        String queryEstablishmentBluebirdLocalisation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentBluebirdLocalisation)) {

            pst.setInt(1, 5);
            pst.setDouble(2, Double.parseDouble("48.8513512"));
            pst.setDouble(3, Double.parseDouble("2.3816897"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentLeGlosterLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentLeGlosterLocalosation)) {

            pst.setInt(1, 6);
            pst.setDouble(2, Double.parseDouble("48.8550134"));
            pst.setDouble(3, Double.parseDouble("2.3044934"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentPinkMamaLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentPinkMamaLocalosation)) {

            pst.setInt(1, 7);
            pst.setDouble(2, Double.parseDouble("48.8812443"));
            pst.setDouble(3, Double.parseDouble("2.3360385"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentDragonPheonixLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentDragonPheonixLocalosation)) {

            pst.setInt(1, 8);
            pst.setDouble(2, Double.parseDouble("48.8645715"));
            pst.setDouble(3, Double.parseDouble("2.3540433"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentCrocodileVertLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCrocodileVertLocalosation)) {

            pst.setInt(1, 9);
            pst.setDouble(2, Double.parseDouble("48.8354932"));
            pst.setDouble(3, Double.parseDouble("2.2892815"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentSupersonicLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentSupersonicLocalosation)) {

            pst.setInt(1, 10);
            pst.setDouble(2, Double.parseDouble("48.8502409"));
            pst.setDouble(3, Double.parseDouble("2.3697225"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentCafeEsplanadeLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCafeEsplanadeLocalosation)) {

            pst.setInt(1, 11);
            pst.setDouble(2, Double.parseDouble("48.8582878"));
            pst.setDouble(3, Double.parseDouble("2.3110675"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentBarTribecaLocalosation =  "INSERT INTO localisation (id, latitude, longitude)  values (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentBarTribecaLocalosation)) {

            pst.setInt(1, 12);
            pst.setDouble(2, Double.parseDouble("48.8568273"));
            pst.setDouble(3, Double.parseDouble("2.3062925"));

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }


        /**
         * **************************IMAGES USER*****************************************
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
            pst.setString(6, passwordEncoder.encode("1234"));
            pst.setString(7,"0625252525");
            pst.setInt(8,1);
            pst.setInt(9,1);
            pst.setInt(10,1);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUser1 =  "INSERT INTO users (id,  name, firstname, birthdate, email, password, phone, localisation_id, mood_id, role_id)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUser1)) {

            LOGGER.log(Level.INFO, "Insert User ROLE USER");

            String date="1999-08-11";

            pst.setInt(1, 2);
            pst.setString(2, "Luffy");
            pst.setString(3, "Monkey D.");
            pst.setDate(4, Date.valueOf(date));
            pst.setString(5, "luffy@taro.jp");
            pst.setString(6, passwordEncoder.encode("mugiwara"));
            pst.setString(7,"0629834546");
            pst.setInt(8,12);
            pst.setInt(9,1);
            pst.setInt(10,4);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryUser2 =  "INSERT INTO users (id,  name, firstname, birthdate, email, password, phone, localisation_id, mood_id, role_id)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUser2)) {

            LOGGER.log(Level.INFO, "Insert User ROLE USER");

            String date="1990-08-10";

            pst.setInt(1, 3);
            pst.setString(2, "Sangoku");
            pst.setString(3, "Vegeta");
            pst.setDate(4, Date.valueOf(date));
            pst.setString(5, "dragonb@ll.lz");
            pst.setString(6, passwordEncoder.encode("chichi"));
            pst.setString(7,"0629834946");
            pst.setInt(8,7);
            pst.setInt(9,2);
            pst.setInt(10,4);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryUser3 =  "INSERT INTO users (id,  name, firstname, birthdate, email, password, phone, localisation_id, mood_id, role_id)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUser3)) {

            LOGGER.log(Level.INFO, "Insert User ROLE USER");

            String date="1979-06-11";

            pst.setInt(1, 4);
            pst.setString(2, "Ichigo");
            pst.setString(3, "kurosaki");
            pst.setDate(4, Date.valueOf(date));
            pst.setString(5, "bleach@shinigami.jm");
            pst.setString(6, passwordEncoder.encode("Rukia"));
            pst.setString(7,"0629467985");
            pst.setInt(8,2);
            pst.setInt(9,3);
            pst.setInt(10,4);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * ****************************INSERT USER IMAGE***********************************
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


        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             Statement st = con.createStatement()) {

            LOGGER.log(Level.INFO, "Insert USER DEFAULT IMAGE");

            con.setAutoCommit(false);

            st.addBatch("INSERT INTO user_image (id,  user_id)  values (1,2)");

            //int counts[] = st.executeBatch();

            st.executeUpdate("UPDATE users SET image_id = 1 "
                    + "WHERE Id = 2");

            con.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             Statement st = con.createStatement()) {

            LOGGER.log(Level.INFO, "Insert USER DEFAULT IMAGE");

            con.setAutoCommit(false);

            st.addBatch("INSERT INTO user_image (id,  user_id)  values (1,3)");

            //int counts[] = st.executeBatch();

            st.executeUpdate("UPDATE users SET image_id = 1 "
                    + "WHERE Id = 3");

            con.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             Statement st = con.createStatement()) {

            LOGGER.log(Level.INFO, "Insert USER DEFAULT IMAGE");

            con.setAutoCommit(false);

            st.addBatch("INSERT INTO user_image (id,  user_id)  values (1,4)");

            //int counts[] = st.executeBatch();

            st.executeUpdate("UPDATE users SET image_id = 1 "
                    + "WHERE Id = 4");

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
        String queryEstablishmentBluebird =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentBluebird)) {

            pst.setInt(1, 4);
            pst.setString(2, "Dans une ambiance feutrée, les barmen vous servent au bar des cocktails singuliers ainsi que des classiques revisités, que vous dégusterez à la lueur des bougies. Le gin est mis à l’honneur au Bluebird mais vous trouverez aussi une liste de cocktails revisités pour plaire à tous les goûts et à tous les mélanges de saveurs. Ainsi vous rencontrerez aussi sur la carte des cocktails à base de rhum, tequila, vodka, alcools délicatement associés et incorporés dans chacun des cocktails issus d’une création unique.");
            pst.setString(3, "Bluebird");
            pst.setBoolean(4, true);
            pst.setInt(5, 1);
            pst.setInt(6, 5);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentLeGloster =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentLeGloster)) {

            pst.setInt(1, 5);
            pst.setString(2, "Ouvert récemment, Le Gloster est le tout premier bar à cocktails 100% digitalisé. Les propriétaires proposent ainsi un concept original où vous payez vos consommations sans monnaie, grâce à un système de cashless, un paiement 100% dématérialisé. Comment ça marche ? Avec votre smartphone, grâce à l’application Barnaby, laquelle permet, si vous ne la connaissez pas encore, de dénicher les meilleures adresses où boire un verre à Paris, Lyon et Bruxelles.");
            pst.setString(3, "Le Gloster");
            pst.setBoolean(4, true);
            pst.setInt(5, 2);
            pst.setInt(6, 6);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentPinkMama =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentPinkMama)) {

            pst.setInt(1, 6);
            pst.setString(2, "L’équipe du Big Mamma a encore frappé ! Cette fois, c’est à Pigalle qu’un nouvel établissement s’installe, dans un superbe immeuble de briques roses.\n" +
                    "\n" +
                    "Le groupe Big Mamma possède plusieurs établissements parisiens dont la notoriété n’est plus à faire : East Mamma, Ober Mamma, Mamma Primi, Big Love Caffè, Popolare… tant de noms qui sont bien connus du public. La nouvelle enseigne baptisée Pink Mamma est située dans le quartier de Pigalle. Cette fois-ci l’équipe a visé haut, puisque le restaurant se déploie sur quatre étages !");
            pst.setString(3, "Pink Mama");
            pst.setBoolean(4, true);
            pst.setInt(5, 1);
            pst.setInt(6, 7);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentDragonPhenix =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentDragonPhenix)) {

            pst.setInt(1, 7);
            pst.setString(2, "Formé au Long Bar du Sanderson à Londres, puis à l’Hôtel Jules & Jim à Paris, le bartender portugais Ricardo Vicente a ouvert son propre établissement en mars dernier qu’il a baptisé le Dragon et Phénix. Situé dans le quartier des Arts-et-Métiers, ce bar à cocktails marie à la perfection mets sucrés/salés et savants breuvages, le tout dans une ambiance relaxante.");
            pst.setString(3, "Dragon & Phénix");
            pst.setBoolean(4, true);
            pst.setInt(5, 3);
            pst.setInt(6, 8);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentCrocodileVert =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCrocodileVert)) {

            pst.setInt(1, 8);
            pst.setString(2, "Au Crocodile Vert, la carte reflète également les saveurs d’un autre continent. Les tapas restent certainement l’une des plus emblématiques préparations concoctées par l’établissement, mais d’autres spécialités cubaines sont également à déguster sans modération. Tortillas, accras de morue et calamars grillés sont entre autres des assiettes fortement recommandées après avoir siroté en apéritif un des nombreux cocktails maison, avec en chef de file l’excellent mojito ainsi qu’une large variété de rhums arrangés. Ouvert tous les jours de la semaine, le Crocodile Vert accueille même les novices chaque dimanche, pour s’imprégner des pas de salsa.");
            pst.setString(3, "Crocodile Vert");
            pst.setBoolean(4, true);
            pst.setInt(5, 1);
            pst.setInt(6, 9);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentSupersonic =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentSupersonic)) {

            pst.setInt(1, 9);
            pst.setString(2, "Musique live et bonne ambiance !");
            pst.setString(3, "Supersonic");
            pst.setBoolean(4, true);
            pst.setInt(5, 2);
            pst.setInt(6, 10);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentCafeEsplanade =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCafeEsplanade)) {

            pst.setInt(1, 10);
            pst.setString(2, "Le Café de l’Esplanade figure parmi les établissements incontournables à ne surtout pas rater lors d’un séjour à Paris. Installé au 52 Rue Fabert 75007 Paris, il ouvre ses portes tous les jours de la semaine, sans exception dès 8 h du matin, pour ne fermer qu’à 2 h du matin. Le Bar du Café de l’Esplanade est également un établissement souvent fréquenté par de hautes personnalités en quête d’un endroit simple, calme et sympathique pour se détendre entre amis.");
            pst.setString(3, "Le Café de l'Esplanade");
            pst.setBoolean(4, true);
            pst.setInt(5, 3);
            pst.setInt(6, 11);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryEstablishmentBarTribeca =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentBarTribeca)) {

            pst.setInt(1, 11);
            pst.setString(2, "Idéalement placé dans un quartier parisien du septième arrondissement, le Bar Tribeca accueille une clientèle très éclectique de son ouverture à 11 heures jusqu’à la fin des services à 23 heures. Le personnel ne chôme pas non plus le dimanche, l’établissement étant ouvert de 11 heures à 17 heures. En plus de se trouver dans une rue piétonne, ce bar à cocktails est très accessible. L’enseigne se trouve à quelques pas de deux stations de métro à savoir La Tour Maubourg et École Militaire.");
            pst.setString(3, "Bar Tribeca");
            pst.setBoolean(4, true);
            pst.setInt(5, 3);
            pst.setInt(6, 12);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************IMAGES ESTABLISHMENT*****************************************
         */

        String queryImageBluebird =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryImageBluebird)) {
            LOGGER.log(Level.INFO, "Insert IMAGE establishment");

            File img = new File("src/main/resources/image/establishment_image/bluebird.jpg");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 2);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "bluebird.jpg");
                pst.setString(4, "image/jpg");
                pst.setInt(5, 10);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateImageBluebird =  "INSERT INTO establishment_image (id,  establishment_id)  values (?,?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateImageBluebird)) {

            LOGGER.log(Level.INFO, "Insert IMAGE establishment image bluebird");

            pst.setInt(1, 2);
            pst.setInt(2, 4);

            pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryImageGloster =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryImageGloster)) {
            LOGGER.log(Level.INFO, "Insert IMAGE Default image");

            File img = new File("src/main/resources/image/establishment_image/le-gloster.jpg");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 3);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "le-gloster.jpg");
                pst.setString(4, "image/jpg");
                pst.setInt(5, 10);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateImageGloster =  "INSERT INTO establishment_image (id,  establishment_id)  values (?,?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateImageGloster)) {
            LOGGER.log(Level.INFO, "Insert IMAGE establishment image gloster");
            pst.setInt(1, 3);
            pst.setInt(2, 5);

            pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryImagepinkMama =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryImagepinkMama)) {
            LOGGER.log(Level.INFO, "Insert IMAGE Default image");

            File img = new File("src/main/resources/image/establishment_image/pink-mamma.jpg");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 4);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "pink-mamma.jpg");
                pst.setString(4, "image/jpg");
                pst.setInt(5, 10);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateImagepinkMama =  "INSERT INTO establishment_image (id,  establishment_id)  values (?,?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateImagepinkMama)) {
            LOGGER.log(Level.INFO, "Insert IMAGE establishment image pink mamma");
            pst.setInt(1, 4);
            pst.setInt(2, 6);

            pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryImageDragonPhenix =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryImageDragonPhenix)) {
            LOGGER.log(Level.INFO, "Insert IMAGE Default image");

            File img = new File("src/main/resources/image/establishment_image/dragon-phenix.jpg");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 5);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "dragon-phenix.jpg");
                pst.setString(4, "image/jpg");
                pst.setInt(5, 10);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateImageDragonPhenix =  "INSERT INTO establishment_image (id,  establishment_id)  values (?,?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateImageDragonPhenix)) {
            pst.setInt(1, 5);
            pst.setInt(2, 7);

            pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryImageCrocodile =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryImageCrocodile)) {
            LOGGER.log(Level.INFO, "Insert IMAGE Default image");

            File img = new File("src/main/resources/image/establishment_image/bar-crocodile-vert.jpg");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 6);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "crocodile-vert.jpg");
                pst.setString(4, "image/jpg");
                pst.setInt(5, 10);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateImageCrocodile =  "INSERT INTO establishment_image (id,  establishment_id)  values (?,?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateImageCrocodile)) {
            pst.setInt(1, 6);
            pst.setInt(2, 8);

            pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryImageEsplanade =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryImageEsplanade)) {
            LOGGER.log(Level.INFO, "Insert IMAGE Default image");

            File img = new File("src/main/resources/image/establishment_image/bar-cafe-de-l-esplanade.jpg");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 7);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "cafe-de-l-esplanade.jpg");
                pst.setString(4, "image/jpg");
                pst.setInt(5, 10);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateImageEsplanade =  "INSERT INTO establishment_image (id,  establishment_id)  values (?,?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateImageEsplanade)) {
            pst.setInt(1, 7);
            pst.setInt(2, 10);

            pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryImageTribeca =  "INSERT INTO images (id, data64, data_name, mime_type, size_image)  values (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryImageTribeca)) {
            LOGGER.log(Level.INFO, "Insert IMAGE Default image");

            File img = new File("src/main/resources/image/establishment_image/bar-tribeca.jpg");

            try (FileInputStream fin = new FileInputStream(img)) {

                pst.setInt(1, 8);
                pst.setBinaryStream(2, fin,(int) img.length());
                pst.setString(3, "bar-tribeca.jpg");
                pst.setString(4, "image/jpg");
                pst.setInt(5, 10);

                pst.executeUpdate();
            } catch (IOException ex) {
                LOGGER.log(
                        Level.SEVERE, ex.getMessage(), ex);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUpdateImageTribeca =  "INSERT INTO establishment_image (id,  establishment_id)  values (?,?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryUpdateImageTribeca)) {
            pst.setInt(1, 8);
            pst.setInt(2, 11);

            pst.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }


            /**
             * **************************Comment*****************************************
             */
        String queryCommentPinkMama =  "INSERT INTO comment (id, content, created_date, group_type, status, title, establishment_id, user_id)  values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryCommentPinkMama)) {

            LOGGER.log(Level.INFO, "Insert User Comment");

            String date="2022-03-26 19:13:28";

            pst.setInt(1, 1);
            pst.setString(2, "wonderful night");
            pst.setTimestamp(3, Timestamp.valueOf(date));
            pst.setInt(4, 0);
            pst.setBoolean(5, false);
            pst.setString(6,"Great live music bar");
            pst.setInt(7,4);
            pst.setInt(8,1);

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryCommentBluebird =  "INSERT INTO comment (id, content, created_date, group_type, status, title, establishment_id, user_id)  values (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryCommentBluebird)) {

            String date="2022-03-26 19:13:57";

            pst.setInt(1, 2);
            pst.setString(2, "great time there !!");
            pst.setTimestamp(3, Timestamp.valueOf(date));
            pst.setInt(4, 0);
            pst.setBoolean(5, false);
            pst.setString(6,"Awesome bar !");
            pst.setInt(7,4);
            pst.setInt(8,1);

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************Note*****************************************
         */
        String queryNoteDragonPhoenix =  "INSERT INTO note (id, value, establishment_id, user_id)  values (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryNoteDragonPhoenix)) {

            LOGGER.log(Level.INFO, "Insert Note Establishment");


            pst.setInt(1, 1);
            pst.setInt(2, 5);
            pst.setInt(3,1);
            pst.setInt(4,1);

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryNoteDragonPhoenix2 =  "INSERT INTO note (id, value, establishment_id, user_id)  values (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryNoteDragonPhoenix2)) {

            pst.setInt(1, 2);
            pst.setInt(2, 3);
            pst.setInt(3,1);
            pst.setInt(4,2);

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryNoteDragonPhoenix3 =  "INSERT INTO note (id, value, establishment_id, user_id)  values (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryNoteDragonPhoenix3)) {

            pst.setInt(1, 3);
            pst.setInt(2, 4);
            pst.setInt(3,1);
            pst.setInt(4,3);

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryNoteDragonPhoenix4 =  "INSERT INTO note (id, value, establishment_id, user_id)  values (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryNoteDragonPhoenix4)) {

            pst.setInt(1, 4);
            pst.setInt(2, 2);
            pst.setInt(3,1);
            pst.setInt(4,4);

            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************GROUP*****************************************
         */
        String queryGroupsLuffy =  "INSERT INTO groups (id, created_date, title, updated_date)  values (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryGroupsLuffy)) {

            LOGGER.log(Level.INFO, "Insert Groups");

            String create_date="2022-03-10 23:59:59";
            String update_date="2022-03-10 23:59:59";

            pst.setInt(1, 1);
            pst.setTimestamp(2, Timestamp.valueOf(create_date));
            pst.setString(3, "Chapeau de Paille");
            pst.setTimestamp(4, Timestamp.valueOf(update_date));


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryGroupsHollow =  "INSERT INTO groups (id, created_date, title, updated_date)  values (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryGroupsHollow)) {

            String create_date="2022-02-28 23:59:59";
            String update_date="2022-02-28 23:59:59";

            pst.setInt(1, 2);
            pst.setTimestamp(2, Timestamp.valueOf(create_date));
            pst.setString(3, "Arrancar");
            pst.setTimestamp(4, Timestamp.valueOf(update_date));


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************GROUP USERS*****************************************
         */
        String queryGroupsLuffyMembers =  "INSERT INTO users_groups (user_id, group_id)  values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryGroupsLuffyMembers)) {

            LOGGER.log(Level.INFO, "Insert Groups Mermbers");


            pst.setInt(1, 1);
            pst.setInt(2, 1);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryGroupsLuffyMembers2 =  "INSERT INTO users_groups (user_id, group_id)  values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryGroupsLuffyMembers2)) {

            pst.setInt(1, 2);
            pst.setInt(2, 1);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryGroupsLuffyMembers3 =  "INSERT INTO users_groups (user_id, group_id)  values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryGroupsLuffyMembers3)) {

            pst.setInt(1, 3);
            pst.setInt(2, 1);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryGroupsBleachMembers =  "INSERT INTO users_groups (user_id, group_id)  values (?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryGroupsBleachMembers)) {

            pst.setInt(1, 4);
            pst.setInt(2, 2);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************INVITATION*****************************************
         */
        String queryInvitaion =  "INSERT INTO invitation (dtype, id, status, group_id, organizer_id, receiver_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryInvitaion)) {

            LOGGER.log(Level.INFO, "Insert Invitation to group");

            pst.setString(1, "Invitation");
            pst.setInt(2, 1);
            pst.setInt(3, 0);
            pst.setInt(4, 1);
            pst.setInt(5, 1);
            pst.setInt(6, 2);



            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryInvitaion2 =   "INSERT INTO invitation (dtype, id, status, group_id, organizer_id, receiver_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryInvitaion2)) {

            LOGGER.log(Level.INFO, "Insert Invitation");


            pst.setString(1, "Invitation");
            pst.setInt(2, 3);
            pst.setInt(3, 1);
            pst.setInt(4, 1);
            pst.setInt(5, 1);
            pst.setInt(6, 4);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        /**
         * **************************INVITATION EVENTS*****************************************
         */
        String queryInvitaionEvents =  "INSERT INTO invitation (dtype, id, status, invitation_date, group_id, organizer_id, establishment_id)  values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryInvitaionEvents)) {

            LOGGER.log(Level.INFO, "Insert Invitation to events");

            String invitaiton_date="2022-03-13 17:16:30";

            pst.setString(1, "InvitationEvenement");
            pst.setInt(2, 2);
            pst.setInt(3, 0);
            pst.setTimestamp(4, Timestamp.valueOf(invitaiton_date));
            pst.setInt(5, 2);
            pst.setInt(6, 1);
            pst.setInt(7, 1);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryInvitaionEvents2 =  "INSERT INTO invitation (dtype, id, status, invitation_date, group_id, organizer_id, establishment_id)  values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryInvitaionEvents2)) {


            String invitaiton_date="2022-03-26 00:00:00";

            pst.setString(1, "InvitationEvenement");
            pst.setInt(2, 4);
            pst.setInt(3, 0);
            pst.setTimestamp(4, Timestamp.valueOf(invitaiton_date));
            pst.setInt(5, 2);
            pst.setInt(6, 1);
            pst.setInt(7, 1);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryInvitaionEvents3 =  "INSERT INTO invitation (dtype, id, status, invitation_date, group_id, organizer_id, establishment_id)  values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryInvitaionEvents3)) {


            String invitaiton_date="2022-03-26 00:00:00";

            pst.setString(1, "InvitationEvenement");
            pst.setInt(2, 5);
            pst.setInt(3, 0);
            pst.setTimestamp(4, Timestamp.valueOf(invitaiton_date));
            pst.setInt(5, 2);
            pst.setInt(6, 1);
            pst.setInt(7, 1);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryInvitaionEvents4 =  "INSERT INTO invitation (dtype, id, status, invitation_date, group_id, organizer_id, establishment_id)  values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryInvitaionEvents4)) {


            String invitaiton_date="2022-03-26 00:00:00";

            pst.setString(1, "InvitationEvenement");
            pst.setInt(2, 6);
            pst.setInt(3, 0);
            pst.setTimestamp(4, Timestamp.valueOf(invitaiton_date));
            pst.setInt(5, 2);
            pst.setInt(6, 1);
            pst.setInt(7, 1);


            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryInvitaionEvents5 =  "INSERT INTO invitation (dtype, id, status, invitation_date, group_id, organizer_id, establishment_id)  values (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryInvitaionEvents5)) {


            String invitaiton_date="2022-03-26 00:00:00";

            pst.setString(1, "InvitationEvenement");
            pst.setInt(2, 7);
            pst.setInt(3, 0);
            pst.setTimestamp(4, Timestamp.valueOf(invitaiton_date));
            pst.setInt(5, 2);
            pst.setInt(6, 1);
            pst.setInt(7, 1);


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
            LOGGER.log(Level.INFO, "TRIGGER DEFAULT IMAGE");

            con.setAutoCommit(false);

            //st.addBatch("DROP PROCEDURE IF EXISTS prc_default_image;");
            /*st.executeUpdate(
                    "CREATE OR REPLACE PROCEDURE sp_delete_image ( " +
                            "   imageId IN NUMBER ) " +
                            "AS  " +
                            "BEGIN " +
                            "    DELETE images FOR " +
                            "    UPDATE users SET image_id = 1 WHERE id = imageId; " +
                            "END;"
            );*/

            con.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             Statement st = con.createStatement()) {

            LOGGER.log(Level.INFO, "CREATE TRIGGER");
            LOGGER.log(Level.INFO, "TRIGGER USER DETAIL");

            con.setAutoCommit(false);

            //st.addBatch("DROP PROCEDURE IF EXISTS prc_default_image;");
            /*st.executeUpdate(
                    "CREATE OR REPLACE PROCEDURE pr_without_pwd_collumn ( " +
                            ")" +
                            "BEGIN " +
                            "    SELECT id integer,  name character, firstname character, birthdate date, email character,  phone integer, localisation_id integer, mood_id integer, role_id integer; " +
                            "    FROM users u " +
                            "    WHERE u.users_id = userId; " +
                            "    RETURN id,  name, firstname, birthdate, email,  phone, localisation_id, mood_id, role_id; " +
                            "END"
            );*/

            con.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }



}
