namespace sante.Models;
using MySql.Data.MySqlClient;

public class Connexion{
    public MySqlConnection createLiaisonBase(){
        string connectionString = "Server=localhost;Database=sante;Uid=root;";
        MySqlConnection connection = null;
        try
        {
            connection = new MySqlConnection(connectionString);
        }
        catch (MySqlException ex)
        {
            Console.WriteLine("Erreur de connexion : " + ex.Message);
        }
        return connection;
    }
}