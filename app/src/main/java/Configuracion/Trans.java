package Configuracion;

public class Trans
{
    //Version Database
    public static final int Version = 1;
    //Nombre de la base de datos
    public static final String DBname= "PM012P";

    //tabla persona
    public static final String TablePersonas = "personas";

    //propiedades
    public static final String id = "id";

    public static final String nombres = "nombres";

    public static final String apellidos = "apellidos";

    public static final String edad = "edad";

    public static final String correo = "correo";

    public static final String foto = "foto";


    //  DDL Create
    public static final String CreateTablePersonas = "CREATE TABLE " + TablePersonas + " ( " +
            "id INTERGER PRIMARY KEY AUTOINCREMET, nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, foto TEXT";

    public static final String selectAllPerson = "SELECT * FROM " + TablePersonas ;

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS " + TablePersonas ;
}

