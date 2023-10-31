using MySql.Data.MySqlClient;

namespace sante.Models;
public class Personne {
    public String NumeroCin {get; set;}
    public String Nom{get; set;}
    public String Prenom{get; set;}
    public String Datedenaissance {get; set;}
    public double Age{get; set;}
    public String Adresse{get; set;}

    public Personne(){}
    public Personne(String numerocin,String nom,String prenom,String datedenaissance,double age,String adresse){
        this.NumeroCin = numerocin;
        this.Nom = nom;
        this.Prenom = prenom;
        this.Datedenaissance = datedenaissance;
        this.Age = age;
        this.Adresse = adresse;        
    }

    

    public List<Personne> getAllPersonne(){
        String sql = "SELECT * FROM personne";
        MySqlConnection liaisonBase = null;
        List<Personne> listePersonne = new List<Personne>();
        try{        
            Connexion connexion = new Connexion ();
            liaisonBase = connexion.createLiaisonBase();
            liaisonBase.Open();
            MySqlCommand cmd = new MySqlCommand(sql, liaisonBase);
            MySqlDataReader reader = cmd.ExecuteReader();
            while(reader.Read()){
                Personne personne = new Personne(reader.GetString(0),reader.GetString(1),reader.GetString(2),reader.GetString(3),reader.GetDouble(4),reader.GetString(5));
                listePersonne.Add(personne);
            }
        }catch(Exception e){
            Console.WriteLine(e.Message);
        }finally{
            liaisonBase.Close();
        }
        return listePersonne;
    }
}