package database;

import database.tables.CityTable;
import database.tables.RoleTable;
import database.tables.WorkerTable;

import java.sql.*;

public class WorkerDatabaseManager {
    private Connection con = null;

    private static final String DRIVER = "org.sqlite.JDBC";

    private  static WorkerDatabaseManager instance = null;

    private static final String WORKER_TABLE_NAME = "worker";
    private static final String ROLE_TABLE_NAME = "role";
    private static final String CITY_TABLE_NAME = "city";

    public static WorkerDatabaseManager getInstance(){
        if(instance == null)
            instance = new WorkerDatabaseManager();

        return instance;
    }

    private WorkerDatabaseManager(){

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
        setUpCityTable();
        setUpRoleTable();
        setUpWorkerTable();
    }

    private void setUpWorkerTable(){
        Statement statement = null;
        try {
            statement = createStatement();
            String query = "create table if not exists \""+WORKER_TABLE_NAME+"\" (\n" +
                    "\t\""+ WorkerTable.ID +"\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                    "\t\""+WorkerTable.NAME+"\"\tTEXT NOT NULL,\n" +
                    "\t\""+WorkerTable.ADDRESS1+"\"\tTEXT,\n" +
                    "\t\""+WorkerTable.CITY_ID+"\"\tINTEGER NOT NULL,\n" +
                    "\t\""+WorkerTable.TELEPHONE_NUM+"\"\tTEXT,\n" +
                    "\t\""+WorkerTable.ROLE_ID+"\"\tINTEGER NOT NULL,\n" +
                    "\t\""+WorkerTable.EMAIL+"\"\tTEXT NOT NULL,\n" +
                    "\t\""+WorkerTable.ADDRESS_LOCATION+"\"\tTEXT,\n" +
                    "\tFOREIGN KEY(\"city_id\") REFERENCES \""+CITY_TABLE_NAME+"\"(\""+ CityTable.ID +"\"),\n" +
                    "\tFOREIGN KEY(\"role_id\") REFERENCES \""+ROLE_TABLE_NAME+"\"(\""+ RoleTable.ID +"\")\n" +
                    ");";
            if(statement.execute(query)){
                System.out.println(WORKER_TABLE_NAME+" Table Created");
            }else{
                System.out.println(WORKER_TABLE_NAME+" Table already created");
            }
            System.out.println(WORKER_TABLE_NAME+" Table exists");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(WORKER_TABLE_NAME+" Table not initialised");
        }finally {
            close(statement);
        }
    }

    private void setUpRoleTable(){
        Statement statement = null;
        try {
            statement = createStatement();
            String query = "create table if not exists \""+ROLE_TABLE_NAME+"\" (\n" +
                    "\t\""+RoleTable.ID+"\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                    "\t\""+RoleTable.NAME+"\"\tTEXT NOT NULL UNIQUE\n" +
                    ");";
            if(statement.execute(query)){
                System.out.println(ROLE_TABLE_NAME+" Table Created");
            }else{
                System.out.println(ROLE_TABLE_NAME+" Table already created");
            }
            System.out.println(ROLE_TABLE_NAME+" Table exists");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(ROLE_TABLE_NAME+" Table not initialised");
        }finally {
            close(statement);
        }
    }

    private void setUpCityTable(){
        Statement statement = null;
        try {
            statement = createStatement();
            String query = "create table if not exists \""+CITY_TABLE_NAME+"\" (\n" +
                    "\t\""+CityTable.ID+"\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\""+CityTable.NAME+"\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\t\""+CityTable.COUNTRY+"\"\tTEXT NOT NULL\n" +
                    ");";
            if(statement.execute(query)){
                System.out.println(CITY_TABLE_NAME+" Table Created");
            }else{
                System.out.println(CITY_TABLE_NAME+" Table already created");
            }
            System.out.println(CITY_TABLE_NAME+" Table exists");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(CITY_TABLE_NAME+" Table not initialised");
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
