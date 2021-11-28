package main.java.model.dao;

import main.java.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.model.bean.Temperatura;


public class TemperaturaDAO {
    public boolean save (Temperatura temperatura){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = connection.prepareStatement("INSERT INTO temperatura (valortemp) values (?)");
            stmt.setDouble(1, temperatura.getValor());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {

            System.err.println("Erro ao salvar!" + e);

            return false;
        }finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
