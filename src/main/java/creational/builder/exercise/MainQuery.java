package creational.builder.exercise;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 🧱 <b>PATRÓN BUILDER</b>
 * <p>
 * Es un patrón de diseño creacional que nos permite construir objetos complejos
 * paso a paso. El patrón nos permite producir distintos tipos y representaciones
 * de un objeto empleando el mismo código de construcción.
 * </p>
 * * 📌 <b>REQUERIMIENTOS:</b>
 * <ul>
 * <li><b>constructor(String table)</b></li>
 * <li><b>select(String[] fields)</b>: Si no se pasa ningún campo, seleccionar (*)</li>
 * <li><b>where(String condition)</b>: Opcional</li>
 * <li><b>orderBy(String field, String order)</b>: Opcional</li>
 * <li><b>limit(int limit)</b>: Opcional</li>
 * <li><b>execute()</b>: Retorna la consulta SQL final</li>
 * </ul>
 * * 💻 <b>EJEMPLO DE USO:</b>
 * <pre>{@code
 * String usersQuery = new QueryBuilder("users")
 * .select("id", "name", "email")
 * .where("age > 18")
 * .where("country = 'Cri'")
 * .orderBy("name", "ASC")
 * .limit(10)
 * .execute();
 *
 * System.out.println(usersQuery);
 * // SELECT id, name, email FROM users WHERE age > 18 AND country = 'Cri' ORDER BY name ASC LIMIT 10;
 * }</pre>
 */

public class MainQuery {

     void main() {
        Query query = Query.builder()
                .table("users")
                .select("id", "name", "email")
                .where("age > 18")
                .where("country = 'Cri'")
                .orderBy("name", "ASC")
                .limit(10)
                .execute();

        IO.println(query);
    }
}

class Query {
    private final String select;
    private final List<String> where;
    private final List<String> orderBy;
    private final int limit;
    private final String table;

    private Query(QueryBuilder builder) {
        this.select = builder.select;
        this.where = builder.where;
        this.orderBy = builder.orderBy;
        this.limit = builder.limit;
        this.table = builder.table;
    }

    public static QueryBuilder builder() {
        return new QueryBuilder();
    }

    public static class QueryBuilder {
        private String select;
        private List<String> where = new ArrayList<>();
        private List<String> orderBy = new ArrayList<>();
        private int limit = 0;
        private String table;

        public QueryBuilder select(String... args) {
            this.select = String.join(", ", args);
            return this;
        }

        public QueryBuilder where(String where) {
            this.where.add(where);
            return this;
        }

        public QueryBuilder orderBy(String... args) {
            this.orderBy.addAll(Arrays.asList(args));
            return this;
        }

        public QueryBuilder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public QueryBuilder table(String table) {
            this.table = table;
            return this;
        }

        Query execute() {
            return new Query(this);
        }
    }

    @Override
    public String toString() {

        String response = "";
        String joinConditionWhere = "";
        String orderBy = "";

        if (this.where == null || this.where.isEmpty()) {
            response = "SELECT " + this.select + " FROM " + this.table + ";";
        }

        if (this.where.size() > 1) {
            joinConditionWhere = String.join(" AND ", this.where);
            response = "SELECT " + this.select + " FROM " + this.table + " WHERE " + joinConditionWhere + ";";
        } else {
            response = "SELECT "+ this.select + " FROM " + this.table + " WHERE " + this.where.get(0) + ";";
        }

        if (!this.orderBy.isEmpty()) {
            String joinConditionOrderBy = String.join(" ", this.orderBy);
            orderBy = " ORDER BY " + joinConditionOrderBy;
            response = "SELECT " + this.select + " FROM " + this.table + " WHERE " + joinConditionWhere + orderBy + ";";
        }

        if (this.limit > 0) {
            response = "SELECT " + this.select + " FROM " + this.table + " WHERE " + joinConditionWhere + orderBy + " LIMIT " + this.limit + ";";
        }

        return response;
    }
}
