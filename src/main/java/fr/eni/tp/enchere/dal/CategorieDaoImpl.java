package fr.eni.tp.enchere.dal;

import fr.eni.tp.enchere.bo.Categorie;
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
public class CategorieDaoImpl implements CategorieDAO {

    private final NamedParameterJdbcTemplate jdbc;

    private String SELECT_BY_ID = "SELECT * FROM categories WHERE no_categorie=:noCategorie";
    private String SELECT_ALL = "SELECT * FROM categories";

    public CategorieDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.jdbc = namedParameterJdbcTemplate;
    }

    @Override
    public Categorie readById(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noCategorie", id);
        return jdbc.queryForObject(SELECT_BY_ID, namedParameters, new CategorieRowMapper());
    }

    @Override
    public List<Categorie> findAllCategories() {
        return jdbc.getJdbcTemplate().query(SELECT_ALL, new CategorieRowMapper());
    }


    private class CategorieRowMapper implements RowMapper<Categorie> {

        @Override
        public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Categorie cat = new Categorie();
            cat.setNoCategorie(rs.getInt("no_categorie"));
            cat.setLibelle(rs.getString("libelle"));
            return cat;
        }
    }

}
