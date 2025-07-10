package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.ArticleVendu;
import fr.eni.tp.enchere.bo.Categorie;
import fr.eni.tp.enchere.bo.Retrait;
import fr.eni.tp.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RetraitDAOImpl implements RetraitDAO {

    private NamedParameterJdbcTemplate jdbc;

    private String INSERT_RETRAITS = "INSERT INTO RETRAITS(no_article,rue, code_postal, ville) " +
                                     " VALUES (:no_article,:rue, :code_postal, :ville)";


    public RetraitDAOImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void createRetrait(Retrait retrait) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource map = new MapSqlParameterSource();


        map.addValue("no_article", retrait.getArticleVendu().getNoArticle());
        map.addValue("rue", retrait.getRue());
        map.addValue("code_postal", retrait.getCodePostal());
        map.addValue("ville", retrait.getVille());

        jdbc.update(INSERT_RETRAITS, map, keyHolder);

        if (keyHolder.getKey() != null) {
            retrait.setNoRetrait(keyHolder.getKey().intValue());
        }


    }



    private class RetraitRowMapper implements RowMapper<Retrait> {

        @Override
        public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Retrait retrait = new Retrait();
            retrait.setNoRetrait(rs.getInt("no_retrait"));
            retrait.setRue(rs.getString("rue"));
            retrait.setCodePostal(rs.getString("code_postal"));
            retrait.setVille(rs.getString("ville"));


            return retrait;
        }
    }
}
