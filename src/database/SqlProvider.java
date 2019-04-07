package database;

import java.sql.*;

public class SqlProvider {
    private Connection con = null;

    private static final String DRIVER = "org.sqlite.JDBC";

    private  static SqlProvider instance = null;

    private static final String WORKER_TABLE_NAME = "";

    public static SqlProvider getInstance(){
        if(instance == null)
            instance = new SqlProvider();

        return instance;
    }

    private SqlProvider(){

        try{
            Class.forName(DRIVER).newInstance();

            String url = "jdbc:sqlite:.sqlite";
            con = DriverManager.getConnection(url);

            setUpTable();

        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setUpTable(){ //todo: write setup table code
        Statement statement = null;
        try {
            statement = createStatement();
            /*if(statement.execute("create table if not exists "+TABLE_NAME+" (\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , \"Name\" VARCHAR, \"Surname\" VARCHAR, \"Email\" VARCHAR, \"PhotoPath\" INTEGER, \"password\" VARCHAR, \"Earnings\" DOUBLE, \"FarmAddress\" VARCHAR)")){
                System.out.println(TABLE_NAME+" Table Created");
            }else{
                System.out.println(TABLE_NAME+" Table already created");
            }
            System.out.println(TABLE_NAME+" Table exists");*/
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(TABLE_NAME+" Table not initialised");
        }finally {
            close(statement);
        }
    }

    private Statement createStatement() throws SQLException {
        return con.createStatement();
    }

    private PreparedStatement prepareStatement(String query) throws SQLException {
        return con.prepareStatement(query);
    }

    private void close(Statement st, ResultSet rs){
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Statement st){
        close(st, null);
    }

}
