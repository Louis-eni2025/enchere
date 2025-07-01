package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private NamedParameterJdbcTemplate jdbc;

    private String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE noUtilisateur=:noUtilisateur";
    private String SELECT_BY_EMAIL = "SELECT * FROM utilisateurs WHERE email = :email";
    private String INSERT_USER = "INSERT INTO utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(:pseudo,:nom, :prenom, :email,:telephone,:rue,:codePostal, :ville, :motDePasse,:credit,:administrateur)";
    private String UPDATE = "UPDATE utilisateurs SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, codePostal = :codePostal, ville = :ville, motDePasse = :motDePasse, credit = :credit, administrateur = :administrateur WHERE noUtilisateur=:noUtilisateur";
    private String DELETE = "DELETE FROM utilisateurs WHERE noUtilisateur=:noUtilisateur";

    public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void create(Utilisateur utilisateur) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("telephone", utilisateur.getTelephone());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("codePostal", utilisateur.getCodePostal());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("motDePasse", utilisateur.getMotDePasse());
        map.addValue("credit", utilisateur.getCredit());
        map.addValue("administrateur", utilisateur.isAdministrateur());

        jdbc.update(INSERT_USER, map, keyHolder);

        if(keyHolder.getKey() != null) {
            utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
        }
    }

    @Override
    public Utilisateur read(int id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("noUtilisateur", id);

        return jdbc.queryForObject(SELECT_BY_ID, map, new UtilisateurRowMapper());
    }

    @Override
    public Utilisateur read(String email) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);
        return jdbc.queryForObject(SELECT_BY_EMAIL, map, new UtilisateurRowMapper());
    }

    @Override
    public void update(Utilisateur utilisateur) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("telephone", utilisateur.getTelephone());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("codePostal", utilisateur.getCodePostal());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("motDePasse", utilisateur.getMotDePasse());
        map.addValue("credit", utilisateur.getCredit());
        map.addValue("administrateur", utilisateur.isAdministrateur());

        // On ajoute l'identifiant de l'utilisateur pour la clause WHERE
        map.addValue("noUtilisateur", utilisateur.getNoUtilisateur());

        jdbc.update(UPDATE, map);
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("noUtilisateur", id);

        jdbc.update(DELETE, map);

    }

    private class UtilisateurRowMapper implements RowMapper<Utilisateur> {

        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(rs.getInt("noUtilisateur"));
            utilisateur.setPseudo(rs.getString("pseudo"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setTelephone(rs.getString("telephone"));
            utilisateur.setRue(rs.getString("rue"));
            utilisateur.setCodePostal(rs.getString("codePostal"));
            utilisateur.setVille(rs.getString("ville"));
            utilisateur.setMotDePasse(rs.getString("motDePasse"));
            utilisateur.setCredit(rs.getInt("credit"));
            utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

            return utilisateur;
        }
    }

}
