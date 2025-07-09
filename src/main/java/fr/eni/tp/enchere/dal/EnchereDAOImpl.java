package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Enchere;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnchereDAOImpl implements EnchereDAO {

    private NamedParameterJdbcTemplate jdbc;

    private String INSERT = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) " +
                            "VALUES(:date_enchere, :montant_enchere, :no_article, :no_utilisateur)";

    private String SELECT_BY_ID = "SELECT date_enchere, montant_enchere, no_article, no_utilisateur FROM ENCHERES " +
                                  "WHERE no_enchere = :no_enchere";

    private String UPDATE = "UPDATE ENCHERES SET date_enchere = :date_enchere, montant_enchere = :montant_enchere," +
                            "no_article = :no_article, no_utilisateur = :no_utilisateur WHERE no_enchere = :no_enchere";

    private String DELETE = "DELETE ENCHERES WHERE no_enchere = :no_enchere";

    @Override
    public Enchere create(Enchere enchere) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("date_enchere", enchere.getDateEnchere());
        map.addValue("montant_enchere", enchere.getMontantEnchere());
        map.addValue("no_article", enchere.getArticleVendu());
        map.addValue("no_utilisateur", enchere.getUtilisateur());

        jdbc.update(INSERT, map, keyHolder);

        if(keyHolder.getKey() != null) {
            enchere.setNoEnchere(keyHolder.getKey().intValue());
        }
        return enchere;
    }

    @Override
    public void update(Enchere enchere) {
        MapSqlParameterSource map = new MapSqlParameterSource();

        map.addValue("date_enchere", enchere.getDateEnchere());
        map.addValue("montant_enchere", enchere.getMontantEnchere());
        map.addValue("no_article", enchere.getArticleVendu());
        map.addValue("no_utilisateur", enchere.getUtilisateur());
        map.addValue("no_enchere", enchere.getNoEnchere());

        jdbc.update(UPDATE, map);
    }

    @Override
    public void delete(int idEnchere) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_enchere", idEnchere);

        jdbc.update(DELETE, map);
    }

    @Override
    public Enchere findById(int idEnchere) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_enchere", idEnchere);

        return jdbc.queryForObject(SELECT_BY_ID, map, new EnchereRowMapper());
    }

    private class EnchereRowMapper implements RowMapper<Enchere> {

        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {

            final Enchere enchere = new Enchere();
            enchere.setNoEnchere(rs.getInt("no_enchere"));
            enchere.setDateEnchere(rs.getDate("date_enchere"));
            enchere.setMontantEnchere(rs.getInt("montant_enchere"));

            final ArticleVendu articleVendu = new ArticleVendu();
            articleVendu.setNoArticle(rs.getInt("no_article"));
            enchere.setArticleVendu(articleVendu);

            final Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
            enchere.setUtilisateur(utilisateur);

            return enchere;
        }
    }
}
