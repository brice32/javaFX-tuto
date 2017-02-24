package projet_test.model;

import java.sql.*;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UtilisateurConnect {

	public static ObservableList<Utilisateur> listUtilisateur(Connection connection) {
		ObservableList<Utilisateur> utilisateurData = FXCollections.observableArrayList();

		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(
					"SELECT id,firstName,lastName,street,postalCode,city,DATE_FORMAT(birthday,GET_FORMAT(DATE,'EUR')) AS birthday FROM utilisateur");
			while (result.next()) {
				// System.out.println(result.getString("firstName"));
				utilisateurData.add(new Utilisateur(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return utilisateurData;
	}

	public static void utlisateurDelete(Connection connection, Utilisateur utilisateur) {
		try {
			// Statement statement = connection.createStatement();
			// System.out.print("DELETE FROM `utilisateur` WHERE
			// `id`="+utilisateur.getId());
			// statement.executeQuery("DELETE FROM `utilisateur` WHERE
			// `id`="+utilisateur.getId());
			PreparedStatement statement = connection.prepareStatement("DELETE FROM utilisateur WHERE id=?");
			statement.setInt(1, utilisateur.getId());
			// PreparedStatement statement = connection.prepareStatement("DELETE
			// FROM utilisateur WHERE id=?");
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void utilisateurCreate(Connection connection, Utilisateur utilisateur) {
		try {
			String sql = "INSERT INTO utilisateur VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, utilisateur.getId());
			statement.setString(2, utilisateur.getFirstName());
			statement.setString(3, utilisateur.getLastName());
			statement.setString(4, utilisateur.getStreet());
			statement.setInt(5, utilisateur.getPostalCode());
			statement.setString(6, utilisateur.getCity());
			LocalDate birthday = utilisateur.getBirthday();
			statement.setString(7, birthday.toString());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void utilisateurModifier(Connection connection, Utilisateur utilisateur) {
		try {
			String sql = "UPDATE utilisateur SET firstName = ?,lastName=?,street=?,postalCode=?,city=?,birthday=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, utilisateur.getFirstName());
			statement.setString(2, utilisateur.getLastName());
			statement.setString(3, utilisateur.getStreet());
			statement.setInt(4, utilisateur.getPostalCode());
			statement.setString(5, utilisateur.getCity());
			LocalDate birthday = utilisateur.getBirthday();
			statement.setString(6, birthday.toString());
			statement.setInt(7, utilisateur.getId());
			statement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
