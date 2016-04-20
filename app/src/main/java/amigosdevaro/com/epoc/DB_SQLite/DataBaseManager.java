package amigosdevaro.com.epoc.DB_SQLite;

/**
 * Created by Alberto on 20/04/2016.
 */
public class DataBaseManager {

    public static final String TABLE_NAME_P = "pacientes";
    public static final String CN_OID_P = "oid_p";
    public static final String CN_FEV_P = "fev";
    public static final String CN_DISREA_P = "disrea";
    public static final String CN_ACTFISICA_P = "actfisica";
    public static final String CN_HOSPITALIZACIONES_P = "hospitalizaciones";

    public static final String CREATE_TABLE_PACIENTES = "create table " +TABLE_NAME_P+ " ("
            +CN_OID_P+ " integer primary key autoincrement,"
            +CN_FEV_P+ " real,"
            +CN_DISREA_P+ " integer,"
            +CN_ACTFISICA_P+ " integer,"
            +CN_HOSPITALIZACIONES_P+ " integer);";

    public static final String TABLE_NAME_SO = "satoxigeno";
    public static final String CN_OID_SO = "oid_so";
    public static final String CN_OID_PSO = "oid_p"
    public static final String CN_FECHA_SO = "fecha";
    public static final String CN_VALOR_SO = "valor";

    public static final String CREATE_TABLE_SATOXIGENO = "create table " +TABLE_NAME_SO+ " ("
            +CN_OID_SO+ " integer primary key autoincrement,"
            +CN_OID_PSO+ " integer,"
            +CN_FECHA_SO+ " numeric,"
            +CN_VALOR_SO+ " integer, " +
            "FOREIGN KEY("+CN_OID_PSO+") REFERENCES "+TABLE_NAME_P+"("+CN_OID_P+"));";

    public static final String TABLE_NAME_POS = "posologias";
    public static final String CN_OID_POS = "oid_pos";
    public static final String CN_ADMINISTRACIONFARMACO_POS = "administracionFarmaco"

    public static final String CREATE_TABLE_POSOLOGIAS = "create table " +TABLE_NAME_POS+ " ("
            +CN_OID_POS+ " integer primary key autoincrement,"
            +CN_ADMINISTRACIONFARMACO_POS+ " text);";

    public static final String TABLE_NAME_F = "farmacos";
    public static final String CN_OID_F = "oid_f";
    public static final String CN_OID_PF = "oid_p"
    public static final String CN_OID_POSF = "oid_pos";
    public static final String CN_NOMBRE_F = "nombre";
    public static final String CN_TIPO_F = "tipo";

    public static final String CREATE_TABLE_FARMACOS = "create table " +TABLE_NAME_F+ " ("
            +CN_OID_F+ " integer primary key autoincrement,"
            +CN_OID_PF+ " integer,"
            +CN_OID_POSF+ " integer,"
            +CN_NOMBRE_F+ " text,"
            +CN_TIPO_F+ " text, " +
            "FOREIGN KEY("+CN_OID_PF+") REFERENCES "+TABLE_NAME_P+"("+CN_OID_P+"), "+
            "FOREIGN KEY("+CN_OID_POSF+") REFERENCES "+TABLE_NAME_POS+"("+CN_OID_POS+"));";

    public static final String TABLE_NAME_DYH = "diasyhoras";
    public static final String CN_OID_DH = "oid_dh";
    public static final String CN_OID_POSDH = "oid_pos"
    public static final String CN_DIA_DH = "dia";
    public static final String CN_HORA_DH = "hora";

    public static final String CREATE_TABLE_DIASYHORAS = "create table " +TABLE_NAME_DYH+ " ("
            +CN_OID_DH+ " integer primary key autoincrement,"
            +CN_OID_POSDH+ " integer,"
            +CN_DIA_DH+ " text,"
            +CN_HORA_DH+ " text," +
            "FOREIGN KEY("+CN_OID_POSDH+") REFERENCES "+TABLE_NAME_POS+"("+CN_OID_POS+"));";

    public static final String TABLE_NAME_D = "descompensaciones";
    public static final String CN_OID_D = "oid_d";
    public static final String CN_OID_PD = "oid_p"
    public static final String CN_OID_SOD = "oid_so"
    public static final String CN_FECHA_D = "fecha";
    public static final String CN_DISREA_D = "disrea";
    public static final String CN_TOS_D = "tos";
    public static final String CN_FEV_D = "fev";
    public static final String CN_EXPECTORACION_D = "expectoracion";
    public static final String CN_RUIDOSRESPIRATORIOS_D = "ruidosRespiratorios";
    public static final String CN_HINCHAZONTOBILLOS_D = "hinchazonTobillos";
    public static final String CN_DOLORPECHO_D = "dolorPecho";
    public static final String CN_ESTARIRRITABLE_D = "estarIrritable";
    public static final String CN_DESORIENTACION_D = "desorientacion";
    public static final String CN_DOLORCABEZA_D = "dolorCabeza";
    public static final String CN_SOMNOLENCIA_D = "somnolencia";

    public static final String CREATE_TABLE_DESCOMPENSACIONES = "create table " +TABLE_NAME_D+ " ("
            +CN_OID_D+ " integer primary key autoincrement,"
            +CN_OID_PD+ " integer,"
            +CN_OID_SOD+ " integer,"
            +CN_FECHA_D+ " numeric,"
            +CN_DISREA_D+ " integer,"
            +CN_TOS_D+ " numeric,"
            +CN_FEV_D+ " real,"
            +CN_EXPECTORACION_D+ " numeric,"
            +CN_RUIDOSRESPIRATORIOS_D+ " numeric,"
            +CN_HINCHAZONTOBILLOS_D+ " numeric,"
            +CN_DOLORPECHO_D+ " numeric,"
            +CN_ESTARIRRITABLE_D+ " numeric,"
            +CN_DESORIENTACION_D+ " numeric,"
            +CN_DOLORCABEZA_D+ " numeric,"
            +CN_SOMNOLENCIA_D+ " numeric," +
            "FOREIGN KEY("+CN_OID_PD+") REFERENCES "+TABLE_NAME_P+"("+CN_OID_P+"), "+
            "FOREIGN KEY("+CN_OID_SOD+") REFERENCES "+TABLE_NAME_SO+"("+CN_OID_SO+"));";

}