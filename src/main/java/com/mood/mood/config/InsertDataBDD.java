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
            pst.setString(6, "mugiwara");
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
            pst.setString(6, "chichi");
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
            pst.setString(6, "Rukia");
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

            st.addBatch("INSERT INTO user_image (id,  user_id)  values (1,1)");

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

            st.addBatch("INSERT INTO user_image (id,  user_id)  values (1,1)");

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

            st.addBatch("INSERT INTO user_image (id,  user_id)  values (1,1)");

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
            pst.setString(2, "Dans une ambiance feutr├⌐e, les barmen vous servent au bar des cocktails singuliers ainsi que des classiques revisit├⌐s, que vous d├⌐gusterez ├á la lueur des bougies. Le gin est mis ├á lΓÇÖhonneur au Bluebird mais vous trouverez aussi une liste de cocktails revisit├⌐s pour plaire ├á tous les go├╗ts et ├á tous les m├⌐langes de saveurs. Ainsi vous rencontrerez aussi sur la carte des cocktails ├á base de rhum, tequila, vodka, alcools d├⌐licatement associ├⌐s et incorpor├⌐s dans chacun des cocktails issus dΓÇÖune cr├⌐ation unique.");
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
            pst.setString(2, "Ouvert r├⌐cemment, Le Gloster est le tout premier bar ├á cocktails 100% digitalis├⌐. Les propri├⌐taires proposent ainsi un concept original o├╣ vous payez vos consommations sans monnaie, gr├óce ├á un syst├¿me de cashless, un paiement 100% d├⌐mat├⌐rialis├⌐. Comment ├ºa marche ? Avec votre smartphone, gr├óce ├á lΓÇÖapplication Barnaby, laquelle permet, si vous ne la connaissez pas encore, de d├⌐nicher les meilleures adresses o├╣ boire un verre ├á Paris, Lyon et Bruxelles.");
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
            pst.setString(2, "LΓÇÖ├⌐quipe du Big Mamma a encore frapp├⌐ ! Cette fois, cΓÇÖest ├á Pigalle quΓÇÖun nouvel ├⌐tablissement sΓÇÖinstalle, dans un superbe immeuble de briques roses.\\nLe groupe Big Mamma poss├¿de plusieurs ├⌐tablissements parisiens dont la notori├⌐t├⌐ nΓÇÖest plus ├á faire : East Mamma, Ober Mamma, Mamma Primi, Big Love Caff├¿, PopolareΓÇª tant de noms qui sont bien connus du public. La nouvelle enseigne baptis├⌐e Pink Mamma est situ├⌐e dans le quartier de Pigalle. Cette fois-ci lΓÇÖ├⌐quipe a vis├⌐ haut, puisque le restaurant se d├⌐ploie sur quatre ├⌐tages !");
            pst.setString(3, "Pink Mama");
            pst.setBoolean(4, true);
            pst.setInt(5, 1);
            pst.setInt(6, 7);
            pst.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        String queryEstablishmentDragonPheonix =  "INSERT INTO establishment (id, description, name, status, category_id, localisation_id)  values (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement pst = con.prepareStatement(queryEstablishmentCreteilSoleil)) {

            pst.setInt(1, 7);
            pst.setString(2, "Form├⌐ au Long Bar du Sanderson ├á Londres, puis ├á lΓÇÖH├┤tel Jules & Jim ├á Paris, le bartender portugais Ricardo Vicente a ouvert son propre ├⌐tablissement en mars dernier quΓÇÖil a baptis├⌐ le Dragon et Ph├⌐nix. Situ├⌐ dans le quartier des Arts-et-M├⌐tiers, ce bar ├á cocktails marie ├á la perfection mets sucr├⌐s/sal├⌐s et savants breuvages, le tout dans une ambiance relaxante.");
            pst.setString(3, "Dragon & Phéonix");
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
            pst.setString(2, "Au Crocodile Vert, la carte refl├¿te ├⌐galement les saveurs dΓÇÖun autre continent. Les tapas restent certainement lΓÇÖune des plus embl├⌐matiques pr├⌐parations concoct├⌐es par lΓÇÖ├⌐tablissement, mais dΓÇÖautres sp├⌐cialit├⌐s cubaines sont ├⌐galement ├á d├⌐guster sans mod├⌐ration. Tortillas, accras de morue et calamars grill├⌐s sont entre autres des assiettes fortement recommand├⌐es apr├¿s avoir sirot├⌐ en ap├⌐ritif un des nombreux cocktails maison, avec en chef de file lΓÇÖexcellent mojito ainsi quΓÇÖune large vari├⌐t├⌐ de rhums arrang├⌐s. Ouvert tous les jours de la semaine, le Crocodile Vert accueille m├¬me les novices chaque dimanche, pour sΓÇÖimpr├⌐gner des pas de salsa.");
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
            pst.setString(2, "Le Caf├⌐ de lΓÇÖEsplanade figure parmi les ├⌐tablissements incontournables ├á ne surtout pas rater lors dΓÇÖun s├⌐jour ├á Paris. Install├⌐ au 52 Rue Fabert 75007 Paris, il ouvre ses portes tous les jours de la semaine, sans exception d├¿s 8 h du matin, pour ne fermer quΓÇÖ├á 2 h du matin. Le Bar du Caf├⌐ de lΓÇÖEsplanade est ├⌐galement un ├⌐tablissement souvent fr├⌐quent├⌐ par de hautes personnalit├⌐s en qu├¬te dΓÇÖun endroit simple, calme et sympathique pour se d├⌐tendre entre amis.");
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
            pst.setString(2, "Id├⌐alement plac├⌐ dans un quartier parisien du septi├¿me arrondissement, le Bar Tribeca accueille une client├¿le tr├¿s ├⌐clectique de son ouverture ├á 11 heures jusquΓÇÖ├á la fin des services ├á 23 heures. Le personnel ne ch├┤me pas non plus le dimanche, lΓÇÖ├⌐tablissement ├⌐tant ouvert de 11 heures ├á 17 heures. En plus de se trouver dans une rue pi├⌐tonne, ce bar ├á cocktails est tr├¿s accessible. LΓÇÖenseigne se trouve ├á quelques pas de deux stations de m├⌐tro ├á savoir La Tour Maubourg et ├ëcole Militaire.");
            pst.setString(3, "Bar Tribeca");
            pst.setBoolean(4, true);
            pst.setInt(5, 3);
            pst.setInt(6, 12);
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

            con.setAutoCommit(false);

            st.addBatch("DROP PROCEDURE IF EXISTS prc_default_image;");

            con.commit();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
