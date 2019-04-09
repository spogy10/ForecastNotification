package database;

import database.tables.CityTable;
import database.tables.RoleTable;
import database.tables.WorkerTable;
import model.City;
import model.Role;
import model.Worker;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

            String url = "jdbc:sqlite:WorkerDB.db";
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

    private void setUpTable(){
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

    public List<Worker> retrieveAllWorkers(){
        LinkedList<Worker> workersList = new LinkedList<>();
        String columns = generateColumnNames(new String[]{WORKER_TABLE_NAME+".*", CITY_TABLE_NAME+"."+CityTable.NAME+" AS city_name", CITY_TABLE_NAME+"."+CityTable.COUNTRY+" AS country", ROLE_TABLE_NAME+"."+RoleTable.NAME+" AS role"});
        String query = "SELECT "+columns+" FROM "+WORKER_TABLE_NAME+
                " INNER JOIN "+CITY_TABLE_NAME+" ON "+CITY_TABLE_NAME+"."+CityTable.ID+" = "+WORKER_TABLE_NAME+"."+WorkerTable.CITY_ID+
                " INNER JOIN "+ROLE_TABLE_NAME+" ON "+ROLE_TABLE_NAME+"."+RoleTable.ID+" = "+WORKER_TABLE_NAME+"."+WorkerTable.ROLE_ID+";";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Statement s = createStatement();

            ps = prepareStatement(query);
            rs = ps.executeQuery();
            Worker w;
            City c;
            Role r;
            while(rs.next()){
                w = new Worker(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7));
                c = new City(rs.getInt(4), rs.getString(8), rs.getString(9));
                r = new Role(rs.getInt(6), rs.getString(10));
                w.setCity(c);
                w.setRole(r);
                workersList.add(w);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("Error getting worker list");
        }finally {
            close(ps, rs);
        }


        return workersList;
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

    private static String generateColumnNames(String[] columns)
    {
        StringBuilder columnString = new StringBuilder();
        boolean firstRecord = true;

        for (String name : columns)
        {
            if (!firstRecord)
            {
                columnString.append(", ");
            }
            columnString.append(name);

            firstRecord = false;
        }

        return columnString.toString();
    }

}
