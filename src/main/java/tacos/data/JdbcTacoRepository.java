/*
 * package tacos.data;
 * 
 * import java.sql.Timestamp; import java.sql.Types; import java.util.Arrays;
 * import java.util.Date; import java.util.List;
 * 
 * import org.springframework.jdbc.core.JdbcTemplate; import
 * org.springframework.jdbc.core.PreparedStatementCreator; import
 * org.springframework.jdbc.core.PreparedStatementCreatorFactory; import
 * org.springframework.jdbc.support.GeneratedKeyHolder; import
 * org.springframework.jdbc.support.KeyHolder; import
 * org.springframework.stereotype.Repository;
 * 
 * import tacos.Ingredient; import tacos.Taco;
 * 
 * @Repository public class JdbcTacoRepository implements TacoRepository{
 * private JdbcTemplate jdbc;
 * 
 * public JdbcTacoRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }
 * 
 * @Override public Taco save(Taco taco) { // TODO Auto-generated method stub
 * long tacoId = saveTacoInfo(taco);
 * 
 * taco.setId(tacoId); List<Ingredient> ingredients = taco.getIngredients();
 * for(Ingredient ingredient : ingredients) { saveIngredientToTaco(ingredient,
 * tacoId); } return taco; }
 * 
 * private void saveIngredientToTaco(Ingredient ingredient, long tacoId) { //
 * TODO Auto-generated method stub
 * 
 * jdbc.update("insert into Taco_Ingredients (taco, ingredient)" +
 * "values (?, ?)", tacoId, ingredient.getId()); }
 * 
 * private long saveTacoInfo(Taco taco) { // TODO Auto-generated method stub
 * taco.setCreatedAt(new Date()); PreparedStatementCreator psc = new
 * PreparedStatementCreatorFactory("insert into Taco (name, createdAt) values (?, ?)"
 * , Types.VARCHAR, Types.TIMESTAMP)
 * .newPreparedStatementCreator(Arrays.asList(taco.getName(), new
 * Timestamp(taco.getCreatedAt().getTime())));
 * 
 * KeyHolder keyHolder = new GeneratedKeyHolder(); jdbc.update(psc, keyHolder);
 * long tacoId = keyHolder.getKey().longValue(); return tacoId; }
 * 
 * }
 */