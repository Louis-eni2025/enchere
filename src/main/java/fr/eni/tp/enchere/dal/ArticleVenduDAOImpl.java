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
public class ArticleVenduDAOImpl implements ArticleVenduDAO {

    private NamedParameterJdbcTemplate jdbc;

    private String SELECT_ALL_BY_RECHERCHE = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie from ARTICLES_VENDUS WHERE nom_article LIKE :recherche";
    private String SELECT_ALL_BY_CATEGORIE_AND_RECHERCHE = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie  from ARTICLES_VENDUS WHERE nom_article LIKE :recherche AND no_categorie = :no_categorie";
    private String SELECT_ALL_BY_CATEGORIE = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie from ARTICLES_VENDUS WHERE no_categorie = :no_categorie";
    private String SELECT_ALL_CAT = "select * from CATEGORIES";
    private String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres," +
            " prix_initial, prix_vente, " +
            "no_utilisateur, no_categorie from ARTICLES_VENDUS";
//    private String SELECT_ALL_CAT = "SELECT no_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie from CATEGORIES";
    private String SELECT_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = :no_article";
    private String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES(:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
    private String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article = :nom_article, description = :description," +
                            "date_debut_encheres = :date_debut_encheres, date_fin_encheres = :date_fin_encheres," +
                            "prix_initial = :prix_initial, prix_vente = :prix_vente, no_utilisateur = :no_utilisateur," +
                            "no_categorie = :no_categorie WHERE no_article = :no_article";
    private String DELETE = "DELETE ARTICLES_VENDUS WHERE no_article = :no_article";

    public ArticleVenduDAOImpl(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ArticleVendu create(ArticleVendu articleVendu ) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nom_article", articleVendu.getNomArticle());
        map.addValue("description", articleVendu.getDescription());
        map.addValue("date_debut_encheres", articleVendu.getDateDebutEnchere());
        map.addValue("date_fin_encheres", articleVendu.getDateFinEnchere());
        map.addValue("prix_initial", articleVendu.getMiseAPrix());
        map.addValue("prix_vente", articleVendu.getPrixVente());
        map.addValue("no_utilisateur", articleVendu.getUtilisateur().getNoUtilisateur());
        map.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());

        jdbc.update(INSERT, map, keyHolder);

        if(keyHolder.getKey() != null) {
            articleVendu.setNoArticle(keyHolder.getKey().intValue());
        }
    }

    @Override
    public List<ArticleVendu> findAll(){
        return jdbc.getJdbcTemplate().query(SELECT_ALL, new ArticleVenduRowMapper());
    }

//    /!\ N'a pas sa place dans cette classe, mais dans CategorieDAO
//    public List<ArticleVendu> findAllCategorie(){
//        return jdbc.getJdbcTemplate().query(SELECT_ALL_CAT, new ArticleVenduRowMapper());
//    }
    @Override
    public List<ArticleVendu> findAllByRecherche(String recherche) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("recherche",  "%"+recherche+"%");

        return jdbc.query(SELECT_ALL_BY_RECHERCHE, map, new ArticleVenduRowMapper());
    }

    @Override
    public List<ArticleVendu> findAllByCategorieAndRecherche(Integer idCategorie, String recherche) {

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_categorie", idCategorie);
        map.addValue("recherche",  "%"+recherche+"%");

        return jdbc.query(SELECT_ALL_BY_CATEGORIE_AND_RECHERCHE, map, new ArticleVenduRowMapper());


    }

    @Override
    public List<ArticleVendu> findAllByCategorie(Integer categorieId) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_categorie", categorieId);

        return jdbc.query(SELECT_ALL_BY_CATEGORIE, map, new ArticleVenduRowMapper());
    }

    @Override
    public ArticleVendu readById(int no_article) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_article", no_article);

        return jdbc.queryForObject(SELECT_BY_ID, map, new ArticleVenduRowMapper());
    }

    @Override
    public void update(ArticleVendu articleVendu) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nom_article", articleVendu.getNomArticle());
        map.addValue("description", articleVendu.getDescription());
        map.addValue("date_debut_encheres", articleVendu.getDateDebutEnchere());
        map.addValue("date_fin_encheres", articleVendu.getDateFinEnchere());
        map.addValue("prix_initial", articleVendu.getMiseAPrix());
        map.addValue("prix_vente", articleVendu.getPrixVente());
        map.addValue("no_utilisateur", articleVendu.getUtilisateur().getNoUtilisateur());
        map.addValue("no_categorie", articleVendu.getCategorie().getNoCategorie());

        jdbc.update(UPDATE, map);
    }

    @Override
    public void delete(int id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("no_article", id);

        jdbc.update(DELETE, map);
    }

    private class ArticleVenduRowMapper implements RowMapper<ArticleVendu> {

        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
            final ArticleVendu article = new ArticleVendu();
            article.setNoArticle(rs.getInt("no_article"));
            article.setNomArticle(rs.getString("nom_article"));
            article.setDescription(rs.getString("description"));
            article.setDateDebutEnchere(rs.getDate("date_debut_encheres"));
            article.setDateFinEnchere(rs.getDate("date_fin_encheres"));
            article.setMiseAPrix(rs.getInt("prix_initial"));
            article.setPrixVente(rs.getInt("prix_vente"));

            final Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
            article.setUtilisateur(utilisateur);

            final Categorie categorie = new Categorie();
            categorie.setNoCategorie(rs.getInt("no_categorie"));
            article.setCategorie(categorie);


            return article;
        }
    }
}
