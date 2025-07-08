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

    private final NamedParameterJdbcTemplate jdbc;

    private String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur=:noUtilisateur";
    private String SELECT_BY_EMAIL = "SELECT * FROM utilisateurs WHERE email = :email";
    private String INSERT_USER = "INSERT INTO utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(:pseudo,:nom, :prenom, :email,:telephone,:rue,:codePostal, :ville, :motDePasse,:credit,:administrateur)";

    private String UPDATE = "UPDATE utilisateurs SET pseudo = :pseudo, nom = :nom, prenom = :prenom, " +
            "email = :email, telephone = :telephone, rue = :rue, code_postal = :codePostal, ville = :ville" +
            " WHERE email=:emailAuth";

    private String DELETE = "DELETE FROM utilisateurs WHERE no_utilisateur=:noUtilisateur";
    private String COMPARE_PSEUDO = "select count(*) from utilisateurs where pseudo = :pseudo";
    private String COMPARE_MAIL = "select count(*) from utilisateurs where email = :email";
    private String COMPARE_PHONE = "select count(*) from utilisateurs where telephone = :telephone";
    private String SELECT_MDP = "SELECT mot_de_passe FROM utilisateurs WHERE email=:email";
    private String SELECT_PSEUDO = "SELECT pseudo FROM utilisateurs WHERE email=:email";

    public UtilisateurDAOImpl( NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.jdbc = namedParameterJdbcTemplate;
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
    public void update(Utilisateur utilisateur, String email) {
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

        map.addValue("emailAuth", email);

        jdbc.update(UPDATE, map);
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("noUtilisateur", id);

        jdbc.update(DELETE, map);
    }

    //Verification pseudo,email ou telephone déja existant

    @Override
    public boolean pseudoExist(String pseudo) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", pseudo);

        Integer count = jdbc.queryForObject(COMPARE_PSEUDO, map, Integer.class);

        if(count != null && count > 0) {
            return true; //pseudo dispo
        }else{
            return false; //pseudo déja use
        }
    }

    @Override
    public boolean emailExist(String email) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);

        Integer count = jdbc.queryForObject(COMPARE_MAIL, map, Integer.class);

        if(count != null && count > 0) {
            return true; //pseudo dispo
        }else{
            return false; //pseudo déja use
        }
    }

    @Override
    public boolean telephoneExist(String telephone) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("telephone", telephone);

        Integer count = jdbc.queryForObject(COMPARE_PHONE, map, Integer.class);

        if(count != null && count > 0) {
            return true; //pseudo dispo
        }else{
            return false; //pseudo déja use
        }
    }

    @Override
    public String passwordSelect(String email) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);
        String mdp = jdbc.queryForObject(SELECT_MDP, map, String.class);

        return mdp;

    }

    @Override
    public boolean validPseudo(String email, String pseudo) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);
        String pseudoAncien = jdbc.queryForObject(SELECT_PSEUDO, map, String.class);
        boolean meme = pseudo.equals(pseudoAncien);

        return meme || !pseudoExist(pseudo);
    }


    private class UtilisateurRowMapper implements RowMapper<Utilisateur> {

        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
            utilisateur.setPseudo(rs.getString("pseudo"));
            utilisateur.setNom(rs.getString("nom"));
            utilisateur.setPrenom(rs.getString("prenom"));
            utilisateur.setEmail(rs.getString("email"));
            utilisateur.setTelephone(rs.getString("telephone"));
            utilisateur.setRue(rs.getString("rue"));
            utilisateur.setCodePostal(rs.getString("code_postal"));
            utilisateur.setVille(rs.getString("ville"));
            utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
            utilisateur.setCredit(rs.getInt("credit"));
            utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

            return utilisateur;
        }
    }
}
