package com.briomax.briobpm.common.core;

/**
 * El objetivo de la clase Constants.java es proporcionar constantes para diferentes valores utilizados en el sistema.
 * Incluye constantes para control, estatus, y otros fines.
 *
 * @author Alexis Zamora
 * @ver 1.0
 * @fecha Feb 19, 2024 4:12:01 PM
 * @since JDK 1.8
 */
public class Constants {


	/*
	 * ========================
	 * =        CONTROL        =
	 * ========================
	 */

	/** La Constante SI. */
	public static final String SI = "SI";
	
	/** La Constante NO. */
	public static final String NO = "NO";
	
	/** La Constante NO. */
	public static final String VACIO = " ";

	
	/*
	 * ========================
	 * =        ESTATUS        =
	 * ========================
	 */
	
	/** La Constante OK. */
	public static final String OK = "OK";
	
	/** La Constante ERROR. */
	public static final String ERROR = "ERROR";
	
	/** La Constante NORMAL. */
	public static final String NORMAL = "NORMAL";

	/** La Constante REGISTRADO. */
	public static final String REGISTRADO = "REGISTRADO";
	
	/** La Constante VERDADERO. */
	public static final String VERDADERO = "V";
	
	/** La Constante FALSO. */
	public static final String FALSO = "F";
	
	/** La Constante ACTUAL. */
	public static final String ACTUAL = "ACT";
	
	/** La Constante SIGUIENTE. */
	public static final String SIGUIENTE = "SIG";
	
	/** La Constante HABILITADO. */
	public static final String HABILITADO = "HABILITADO";
	
	/** La Constante INFORMACION. */
	public static final String INFORMACION = "INFORMACION";
	
	/*
	 * ========================
	 * =         TIEMPO         =
	 * ========================
	 */
	
	/** La Constante SEGUNDOS. */
	public static final String SEGUNDOS = "SEGUNDOS";
	
	/** La Constante MINUTOS. */
	public static final String MINUTOS = "MINUTOS";
	
	/** La Constante HORAS. */
	public static final String HORAS = "HORAS";
	
	/** La Constante DIAS. */
	public static final String DIAS = "DIAS";
	
	/** La Constante SEMANAS. */
	public static final String SEMANAS = "SEMANAS";

	/** La Constante MESES. */
	public static final String MESES = "MESES";
	
	/*
	 * ========================
	 * =    TIPO VARIABLE      =
	 * ========================
	 */
	
	/** La Constante ALFANUMERICO. */
	public static final String ALFANUMERICO = "ALFANUMERICO";
	
	/** La Constante ENTERO. */
	public static final String ENTERO = "ENTERO";
	
	/** La Constante DECIMAL. */
	public static final String DECIMAL = "DECIMAL";

	/** La Constante FECHA. */
	public static final String FECHA = "FECHA";

	/** La Constante SOLICITAR. */
	public static final String IMAGEN = "IMAGEN";
	
	/*
	 * ========================
	 * =   ACCIONES ACTIVIDAD  =
	 * ========================
	 */
	
	/** La Constante LEE_ACCIONES_ACTIVIDAD. */
	public static final String LEE_ACCIONES_ACTIVIDAD = "LEE_ACCIONES_ACTIVIDAD";
	
	/** La Constgante REGISTRO. */
	public static final String REGISTRO = "REGISTRO";
	
	/** La Constante TERMINADA. */
	public static final String TERMINADA = "TERMINADA";
	
	/** La Constante CANCELADA. */
	public static final String CANCELADA = "CANCELADA";
	
	/*
	 * ========================
	 * =   VARIABLES PROCESO  =
	 * ========================
	 */
	
	/** La Constante MAXIMO. */
	public static final String MAXIMO = "MAXIMO";
	
	/** La Constante MINIMO. */
	public static final String MINIMO = "MINIMO";
	
	/** La Constante SUMA. */
	public static final String SUMA = "SUMA";

	/** La Constante PROMEDIO. */
	public static final String PROMEDIO = "PROMEDIO";
	
	/** La Constante VPRO_01_FECHA_VIGENCIA_DOCUMENTO. */
	public static final String VPRO_01_FECHA_VIGENCIA_DOCUMENTO = "VPRO_01_FECHA_VIGENCIA_DOCUMENTO";
    
	/*
	 * ========================
	 * =      CONDICIONALES     =
	 * ========================
	 */
	
    /** La Constante ACTIVIDAD-USUARIO. */
    public static final String ACTIVIDAD_USUARIO = "ACTIVIDAD-USUARIO";
    
    /** La Constante ACTIVIDAD-USUARIO-TEMPORIZACION. */
    public static final String ACTIVIDAD_USUARIO_TEMPORIZACION = "ACTIVIDAD-USUARIO-TEMPORIZACION";

    /** La Constante COMPUERTA_EXCLUSIVA_INICIO. */
    public static final String COMPUERTA_EXCLUSIVA_INICIO = "COMPUERTA-EXCLUSIVA-INICIO";

    /** La Constante COMPUERTA_EXCLUSIVA_CIERRE. */
    public static final String COMPUERTA_EXCLUSIVA_CIERRE = "COMPUERTA-EXCLUSIVA-CIERRE";
    
    /** La Constante COMPUERTA_INCLUSIVA_CIERRE. */
    public static final String COMPUERTA_INCLUSIVA_CIERRE = "COMPUERTA-INCLUSIVA-CIERRE";

    /** La Constante COMPUERTA_INCLUSIVA_INICIO. */
    public static final String COMPUERTA_INCLUSIVA_INICIO = "COMPUERTA-INCLUSIVA-INICIO";

    /** La Constante COMPUERTA_PARALELA_CIERRE. */
    public static final String COMPUERTA_PARALELA_CIERRE = "COMPUERTA-PARALELA-CIERRE";

    /** La Constante COMPUERTA_PARALELA_INICIO. */
    public static final String COMPUERTA_PARALELA_INICIO = "COMPUERTA-PARALELA-INICIO";
    
    /** La Constante EVENTO_FIN. */
    public static final String EVENTO_FIN = "EVENTO-FIN";

    /** La Constante EVENTO_INICIO_MENSAJE. */
    public static final String EVENTO_FIN_MENSAJE = "EVENTO-FIN-MENSAJE";
    
    /** La Constante EVENTO_INICIO. */
    public static final String EVENTO_INICIO = "EVENTO-INICIO";
    
    /** La Constante EVENTO_INICIO_MENSAJE. */
    public static final String EVENTO_INICIO_MENSAJE = "EVENTO-INICIO-MENSAJE";
    
    /** La Constante EVENTO-INTERMEDIO-ENVIO". */
    public static final String EVENTO_INTERMEDIO_ENVIO = "EVENTO-INTERMEDIO-ENVIO";
    
    /** La Constante EVENTO-INTERMEDIO-RECEPCION. */
    public static final String EVENTO_INTERMEDIO_RECEPCION = "EVENTO-INTERMEDIO-RECEPCION";
    
    /** La Constante EVENTO_INTERMEDIO_TEMPORAL. */
    public static final String EVENTO_INTERMEDIO_TEMPORAL = "EVENTO-INTERMEDIO-TEMPORAL";
    
    /** La Constante MENSAJE. */
    public static final String ORIGEN_MENSAJE = "MENSAJE";
    
    /** La Constante MENSAJE. */
    public static final String ORIGEN_MENSAJE_SERVICIO = "MENSAJE-SERVICIO";
    
    /** La Constante TEMPORIZADOR. */
    public static final String TEMPORIZADOR = "TEMPORIZADOR";
    
    /*
     * ========================
     * =       PREFIJOS         =
     * ========================
     */
    
    /** La Constante DA. */
    public static final String DA = "DA.";
    
    /** La Constante VDOC. */
    public static final String VDOC = "VDOC";
    
    /** La Constante VENT. */
    public static final String VENT = "VENT";
    
    /** La Constante VLOC. */
    public static final String VLOC = "VLOC";
    
    /** La Constante VPRO". */
    public static final String VPRO = "VPRO";
    
    /** La Constante VSIS. */
    public static final String VSIS = "VSIS";
    
    /*
     * ========================
     * =  VARIABLES SECCION   =
     * ========================
     */
    
    /** La Constante VPRO_01_SECUENCIA_DOCUMENTO. */
    public static final String VPRO_01_SECUENCIA_DOCUMENTO = "VPRO_01_SECUENCIA_DOCUMENTO";
    
    /** La Constante VPRO_01_DESCRIPCION_DOCUMENTO. */
    public static final String VPRO_01_DESCRIPCION_DOCUMENTO = "VPRO_01_DESCRIPCION_DOCUMENTO";
    
    /** La Constante VPRO_01_ARCHIVO_DOCUMENTO". */
    public static final String VPRO_01_ARCHIVO_DOCUMENTO = "VPRO_01_ARCHIVO_DOCUMENTO";
    
    /** La Constante VPRO_01_REQUERIDO_DOCUMENTO. */
    public static final String VPRO_01_REQUERIDO_DOCUMENTO = "VPRO_01_REQUERIDO_DOCUMENTO";
    
    /*
     * ========================
     * =  VALORES VARIABLES PARA MENSAJE  =
     * ========================
     */
    
    /** La Constante ACCION. */
    public static final String ACCION = "@ACCION@|";
    
    /** La Constante ANIO. */
    public static final String ANIO = "@ANIO@|";
    
    /** La Constante MES. */
    public static final String MES = "@MES@|";

    /** La Constante SENTENCIA. */
    public static final String SENTENCIA =  "@SENTENCIA@|";
    
    /** La Constante COMENTARIO. */
    public static final String COMENTARIO = "@COMENTARIO@|";
    
    /** La Constante COMPLETADA. */
    public static final String COMPLETADA = "@COMPLETADA@|";
    
    /** La Constante CONDICION. */
    public static final String CONDICION = "@CONDICION@|";
    
    /** La Constante CONCEPTO. */
    public static final String CONCEPTO = "@CONCEPTO@|";

    /** La Constante CVE_ENTIDAD. */
    public static final String CVE_ENTIDAD = "@CVE_ENTIDAD@|";

    /** La Constante CVE_IDIOMA. */
    public static final String CVE_IDIOMA = "@CVE_IDIOMA@|";

    /** La Constante CVE_INSTANCIA. */
    public static final String CVE_INSTANCIA = "@CVE_INSTANCIA@|";

    /** La Constante CVE_LOCALIDAD. */
    public static final String CVE_LOCALIDAD = "@CVE_LOCALIDAD@|";

    /** La Constante CVE_NODO. */
    public static final String CVE_NODO = "@CVE_NODO@|";

    /** La Constante CVE_NODO_COMPUERTA. */
    public static final String CVE_NODO_COMPUERTA = "@CVE_NODO_COMPUERTA@|";
    
    /** La Constante CVE_NODO_CREAR. */
    public static final String CVE_NODO_CREAR = "@CVE_NODO_CREAR@|";

    /** La Constante CVE_NODO_FIN. */
    public static final String CVE_NODO_FIN = "@CVE_NODO_FIN@|";

    /** La Constante CVE_NODO_INICIO. */
    public static final String CVE_NODO_INICIO = "@CVE_NODO_INICIO@|";
    
    /** La Constante CVE_INSTANCIA_ORIGEN. */
    public static final String CVE_INSTANCIA_ORIGEN = "@CVE_INSTANCIA_ORIGEN@|";

    /** La Constante CVE_NODO_ORIGEN. */
    public static final String CVE_NODO_ORIGEN = "@CVE_NODO_ORIGEN@|";

    /** La Constante CVE_PROCESO_ORIGEN. */
    public static final String CVE_PROCESO_ORIGEN = "@CVE_PROCESO_ORIGEN@|";

    /** La Constante ESTADO. */
    public static final String ESTADO = "@ESTADO@|";

    /** La Constante FECHA_BLOQUEA. */
    public static final String FECHA_BLOQUEA = "@FECHA_BLOQUEA@|";
    
    /** La Constante CVE_ENTIDAD_DESTINO. */
    public static final String CVE_ENTIDAD_DESTINO = "@CVE_ENTIDAD_DESTINO@|";
    
    /** La Constante CVE_ENTIDAD_ORIGEN. */
    public static final String CVE_ENTIDAD_ORIGEN = "@CVE_ENTIDAD_ORIGEN@|";
    
    /** La Constante CVE_DATO. */
    public static final String CVE_DATO = "@CVE_DATO@|";
    
    /** La Constante CVE_PROCESO. */
    public static final String CVE_PROCESO = "@CVE_PROCESO@|";

    /** La Constante CVE_PROCESO_DESTINO. */
    public static final String CVE_PROCESO_DESTINO = "@CVE_PROCESO_DESTINO@|";

    /** La Constante CVE_VARIABLE. */
    public static final String CVE_VARIABLE = "@CVE_VARIABLE@|";

    /** La Constante CVE_USUARIO. */
    public static final String CVE_USUARIO = "@CVE_USUARIO@|";

    /** La Constante FECHA_CREACION. */
    public static final String FECHA_CREACION = "@FECHA_CREACION@|";

	/** La Constante FECHA_ESTADO_ACTUAL. */
	public static final String FECHA_ESTADO_ACTUAL = "@FECHA_ESTADO_ACTUAL@|";
	
	/** La Constante FECHA_FIN_ESPERA. */
	public static final String FECHA_FIN_ESPERA = "@FECHA_FIN_ESPERA@|";
	
	/** La Constante FECHA_LIMITE. */
	public static final String FECHA_LIMITE = "@FECHA_LIMITE@|";
	    
	/** La Constante FOLIO_MENSAJE_ENVIO. */
	public static final String FOLIO_MENSAJE_ENVIO = "@FOLIO_MENSAJE_ENVIO@|";
	
	/** La Constante FORMULA. */
	public static final String FORMULA = "@FORMULA@|";
	
	/** La Constante ID_NODO. */
    public static final String ID_NODO = "@ID_NODO@|";
    
    /** La Constante ID_NODO_CREAR. */
    public static final String ID_NODO_COMPUERTA = "@ID_NODO_COMPUERTA@|";

    /** La Constante ID_NODO_CREAR. */
    public static final String ID_NODO_CREAR = "@ID_NODO_CREAR@|";

    /** La Constante ID_NODO_FIN. */
    public static final String ID_NODO_FIN = "@ID_NODO_FIN@|";

    /** La Constante ID_NODO_INICIO. */
    public static final String ID_NODO_INICIO = "@ID_NODO_INICIO@|";
   
    /** La Constante ID_NODO_ORIGEN. */
    public static final String ID_NODO_ORIGEN = "@ID_NODO_ORIGEN@|";
    
    /** La Constante NOMBRE_ARCHIVO. */
    public static final String NOMBRE_ARCHIVO = "@NOMBRE_ARCHIVO@|";
    
    /** La Constante ORIGEN. */
    public static final String ORIGEN = "@ORIGEN@|";
    
    /** La Constante ORIGEN_DATO. */
    public static final String ORIGEN_DATO = "@ORIGEN_DATO@|";
    
    /** La Constante ROL_BLOQUEA. */
    public static final String ROL_BLOQUEA = "@ROL_BLOQUEA@|";

    /** La Constante ROL_CREADOR. */
    public static final String ROL_CREADOR = "@ROL_CREADOR@|";

    /** La Constante SECUENCIA_DOCUMENTO. */
    public static final String SECUENCIA_DOCUMENTO = "@SECUENCIA_DOCUMENTO@|";
    
    /** La Constante SECUENCIA_NODO. */
    public static final String SECUENCIA_NODO = "@SECUENCIA_NODO@|";
   
    /** La Constante SECUENCIA_NODO_COMPUERTA. */
    public static final String SECUENCIA_NODO_COMPUERTA =  "@SECUENCIA_NODO_COMPUERTA@|";
    
    /** La Constante SECUENCIA_NODO_ORIGEN. */
    public static final String SECUENCIA_NODO_ORIGEN = "@SECUENCIA_NODO_ORIGEN@|";
    
    /** La Constante SENTENCIA_SQL. */
    public static final String SENTENCIA_SQL = "|@SENTENCIA_SQL@|"; 
   
    /** La Constante SITUACION. */
    public static final String SITUACION = "@SITUACION@|";

    /** La Constante TIPO_GENERACION. */
    public static final String TIPO_GENERACION = "@TIPO_GENERACION@|";
    
    /** La Constante USUARIO_BLOQUEA. */
    public static final String USUARIO_BLOQUEA = "@USUARIO_BLOQUEA@|";
    
    /** La Constante USUARIO_CREADOR. */
    public static final String USUARIO_CREADOR = "@USUARIO_CREADOR@|";

    /** La Constante VERSION. */
    public static final String VERSION = "@VERSION@|";
    
    /** La Constante VERSION_DESTINO. */
    public static final String VERSION_DESTINO = "@VERSION_DESTINO@|";
    
    /** La Constante VERSION_ORIGEN. */
    public static final String VERSION_ORIGEN = "@VERSION_ORIGEN@|";
	
    /*
	 * ========================
	 * =   leeEstiloNivelServicio  =
	 * ========================
	 */
	
	/** La Constante GDCELLAMBER. */
	public static final String GDCELLAMBER = "gdcellamber";
	
	/** La Constante GDCELLRED. */
	public static final String GDCELLRED = "gdcellred";
	
	/** La Constante GDCELLGREEN. */
	public static final String GDCELLGREEN = "gdcelgreen";
	
	/** La Constante GDCELLORANGE. */
	public static final String GDCELLORANGE = "gdcellorange";


/*
	 * ========================
	 * =   leeInfAreaTrabajoVP  =
	 * ========================
	 */
	
	/** La Constante LEE_INF_AREA_TRABAJO_VP. */
	public static final String LEE_INF_AREA_TRABAJO_VP = "LEE_INF_AREA_TRABAJO_VP";
	
	/** La Constante VARIABLE_PROCESO. */
	public static final String VARIABLE_PROCESO = "VARIABLE_PROCESO";
	
	/*
	 * ========================
	 * =   leeInfAreaTrabajoVS  =
	 * ========================
	 */
	
	/** La Constante VARIABLE_SISTEMA. */
	public static final String VARIABLE_SISTEMA = "VARIABLE_SISTEMA";
	
	/** La Constante MONEDA. */
	public static final String MONEDA = "MONEDA";
	
	/** La Constante MON. */
	public static final String MON = "_$MON";
	
	/*
	 * ========================
	 * =   leeInfAreaTrabajo  =
	 * ========================
	 */
	
	/** La Constante DA_FECHA_HORA_LIMITE. */
	public static final String DA_FECHA_HORA_LIMITE = "DA.FECHA_HORA_LIMITE";
	public static final String DA_FECHA_HORA_FIN_ESPERA = "DA.FECHA_HORA_FIN_ESPERA";
	
	
	
	/** La Constante NO_APLICA. */
	public static final String NO_APLICA = "NO APLICA";
	
	/** La Constante ROL. */
	public static final String ROL = "ROL";
	
	/** La Constante ROL_USUARIO. */
	public static final String ROL_USUARIO = "ROL_USUARIO";
	
	/** La Constante SECUENCIA. */
	public static final String SECUENCIA = "SECUENCIA";
	
	/*
	 * ========================
	 * =   leeInfAreaTrabajoVE  =
	 * ========================
	 */
	
	/** La Constante VARIABLE_ENTIDAD. */
	public static final String VARIABLE_ENTIDAD = "VARIABLE_ENTIDAD";
	
	/*
	 * ========================
	 * =   leeInfAreaTrabajoVL  =
	 * ========================
	 */
	
	/** La Constante VARIABLE_LOCALIDAD. */
	public static final String VARIABLE_LOCALIDAD = "VARIABLE_LOCALIDAD";

	/*
	 * ========================
	 * =   leeInfAreaTrabajoDPN  =
	 * ========================
	 */
	
	/** La Constante DATO_PROCESO_NODO. */
	public static final String DATO_PROCESO_NODO = "DATO_PROCESO_NODO";
	
	/** La Constante LEE_INF_AREA_TRABAJO_DPN. */
	public static final String LEE_INF_AREA_TRABAJO_DPN = "LEE_INF_AREA_TRABAJO_DPN";
	/*
	 * ========================
	 * =   creaCorreoProceso  =
	 * ========================
	 */
	
	/** La Constante CREADO. */
	public static final String CREADO = "CREADO";
	
	/** La Constante PARA. */
	public static final String PARA = "PARA";
	
	/** La Constante CCP. */
	public static final String CCP = "CCP";
	
	/*
	 * ========================
	 * =   leeAtributosDato  =
	 * ========================
	 */
	
	/** La Constante LEE_ATRIBUTOS_DATO. */
	public static final String LEE_ATRIBUTOS_DATO = "LEE_ATRIBUTOS_DATO";
	
	/*
	 * ========================
	 * =   leeAtributosMoneda  =
	 * ========================
	 */
	
	/** La Constante LEE_ATRIBUTOS_MONEDA. */
	public static final String LEE_ATRIBUTOS_MONEDA = "LEE_ATRIBUTOS_MONEDA";
	
	/** La Constante ENTIDAD. */
	public static final String ENTIDAD = "ENTIDAD";
	
	/** La Constante LOCALIDAD. */
	public static final String LOCALIDAD = "LOCALIDAD";
	
	/** La Constante SISTEMA. */
	public static final String SISTEMA = "SISTEMA";
	
	/** La Constante SOLICITAR. */
	public static final String SOLICITAR = "SOLICITAR";
	
	/** La Constante SOLICITAR. */
	public static final String TEXTBOX = "TEXTBOX";
	
	/*
	 * ========================
	 * =   leeColumnaAreaTrabajo  =
	 * ========================
	 */
	
	/** La Constante LEE_ATRIBUTOS_MONEDA. */
	public static final String LEE_COLUMNAS_AREA_TRABAJO = "LEE_COLUMNAS_AREA_TRABAJO";
	
	/*
	 * ========================
	 * =   leeColumnasSeccionOCU  =
	 * ========================
	 */
	
	/** La Constante CVE_SECCION. */
	public static final String CVE_SECCION = "@CVE_SECCION@|";
	
	/*
	 * ========================
	 * =   leeColumnasSeccionOCU  =
	 * ========================
	 */
	
	/** La Constante CVE_MONEDA. */
	public static final String CVE_MONEDA = "CVE_MONEDA";
	
	/** La Constante VW_MONEDA_PROCESO. */
	public static final String VW_MONEDA_PROCESO = "VW_MONEDA_PROCESO";
	
	/** La Constante COMBOBOX. */
	public static final String COMBOBOX = "COMBOBOX";
	
	/** La Constante ENTRADA. */
	public static final String ENTRADA = "ENTRADA";
	
	/** La Constante SALIDA. */
	public static final String SALIDA = "SALIDA";
	
	/** La Constante SOLICITAR. */
	public static final String TABLA = "TABLA";
	
	
	/*
	 * ========================
	 * =   guardaVariablesSeccion  =
	 * ========================
	 */
	
	/** La Constante GUARDA_VARIABLES_SECCION. */
	public static final String GUARDA_VARIABLES_SECCION = "GUARDA_VARIABLES_SECCION";
	

	/*
	 * ========================
	 * =   guardaDocumentoBinarioNodo  =
	 * ========================
	 */
	
	/** La Constante GUARDA_DOCUMENTO_BINARIO_NODO. */
	public static final String GUARDA_DOCUMENTO_BINARIO_NODO = "GUARDA_DOCUMENTO_BINARIO_NODO";
	
	
	/*
	 * ========================
	 * =   leeVariablesSeccion  =
	 * ========================
	 */
	
	/** La Constante LEE_VARIABLES_SECCION. */
	public static final String LEE_VARIABLES_SECCION = "LEE_VARIABLES_SECCION";
	
	/*
	 * ========================
	 * =   extraeTareasCadena  =
	 * ========================
	 */
	
	/** La Constante SEC_TAREA_NO_NUMERICA. */
	public static final String SEC_TAREA_NO_NUMERICA = "SEC_TAREA_NO_NUMERICA";
	
	/** La Constante TAREA_NODO_NO_EXISTE. */
	public static final String TAREA_NODO_NO_EXISTE = "TAREA_NODO_NO_EXISTE";
	
	/** La Constante ATRIBUTOS_NO_ENCONTRADOS. */
	public static final String ATRIBUTOS_NO_ENCONTRADOS = "ATRIBUTOS_NO_ENCONTRADOS";
	
	/** La Constante SECUENCIA_TAREA. */
	public static final String SECUENCIA_TAREA = "SECUENCIA_TAREA";
	
	/** La Constante EXTRAE_TAREAS_NODO. */
	public static final String EXTRAE_TAREAS_NODO = "EXTRAE_TAREAS_NODO";
	
	/*
	 * ========================
	 * =   guardaTareasNodo  =
	 * ========================
	 */
	
	/** La Constante GUARDA_TAREAS_NODO. */
	public static final String GUARDA_TAREAS_NODO = "GUARDA_TAREAS_NODO";
	
	/** La Constante NO_EXISTEN_TAREAS. */
	public static final String NO_EXISTEN_TAREAS = "NO_EXISTEN_TAREAS";
	
	/** La Constante ERR_UPD_TAREA_NODO. */
	public static final String ERR_UPD_TAREA_NODO = "ERR_UPD_TAREA_NODO";
	
	/*
	 * ========================
	 * =   validaVariablesRequeridas  =
	 * ========================
	 */
	
	/** La Constante VALIDA_VARIABLES_REQUERIDAS. */
	public static final String VALIDA_VARIABLES_REQUERIDAS = "VALIDA_VARIABLES_REQUERIDAS";
	
	/** La Constante MENSAJE_VARIABLES. */
	public static final String MENSAJE_VARIABLES = "MENSAJE_VARIABLES";
	
	/** La Constante VARIABLES_REQUERIDAS_SIN_VALOR. */
	public static final String VARIABLES_REQUERIDAS_SIN_VALOR = "VARIABLES_REQUERIDAS_SIN_VALOR";

	/*
	 * ========================
	 * =   leeTareasNodo  =
	 * ========================
	 */
	
	/** La Constante LEE_TAREAS_NODO. */
	public static final String LEE_TAREAS_NODO = "LEE_TAREAS_NODO";
	
	/** La Constante ERR_LEE_TAREAS_SECCION. */
	public static final String ERR_LEE_TAREAS_SECCION = "ERR_LEE_TAREAS_SECCION";
	
	/** La Constante ERR_INS_TAREAS_SECCION. */
	public static final String ERR_INS_TAREAS_SECCION = "ERR_INS_TAREAS_SECCION";
	
	/*
	 * ========================
	 * =   leeDocumentoBinarioNodo  =
	 * ========================
	 */
	
	/** La Constante LEE_DOCUMENTO_BINARIO_NODO. */
	public static final String LEE_DOCUMENTO_BINARIO_NODO = "ERR_LEE_DOCUMENTO_BINARIO_NODO";
	
	/** La Constante ERR_LEE_DOCUMENTO_BINARIO_NODO. */
	public static final String ERR_LEE_DOCUMENTO_BINARIO_NODO = "ERR_LEE_DOCUMENTO_BINARIO_NODO";

	/** La Constante NO_EXISTE_DOCUMENTO_PARA_NODO. */
	public static final String NO_EXISTE_DOCUMENTO_PARA_NODO = "NO_EXISTE_DOCUMENTO_PARA_NODO";
	

	/*
	 * ========================
	 * =   borraDocumentoBinarioNodo  =
	 * ========================
	 */
	
	/** La Constante BORRA_DOCUMENTO_BINARIO_NODO. */
	public static final String BORRA_DOCUMENTO_BINARIO_NODO = "BORRA_DOCUMENTO_BINARIO_NODO";
	
	/** La Constante ERR_LEE_DOCUMENTO_BINARIO_PROCESO. */
	public static final String ERR_LEE_DOCUMENTO_BINARIO_PROCESO = "ERR_LEE_DOCUMENTO_BINARIO_PROCESO";

	/** La Constante ERROR_BORRADO_DOCUMENTO_NODO. */
	public static final String ERROR_BORRADO_DOCUMENTO_NODO = "ERROR_BORRADO_DOCUMENTO_NODO";
	
	
	/*
	 * ========================
	 * =   validaTareasRequerida  =
	 * ========================
	 */
	
	/** La Constante VALIDA_TAREAS_REQUERIDAS. */
	public static final String VALIDA_TAREAS_REQUERIDAS = "VALIDA_TAREAS_REQUERIDAS";
	
	/** La Constante REQUERIDA. */
	public static final String REQUERIDA = "REQUERIDA";

	/** La Constante ERROR_BORRADO_DOCUMENTO_NODO. */
	public static final String LISTA_TAREAS = "LISTA_TAREAS";
	
	/** La Constante TAREAS_REQUERIDAS_SIN_REALIZAR. */
	public static final String TAREAS_REQUERIDAS_SIN_REALIZAR = "TAREAS_REQUERIDAS_SIN_REALIZAR";
	
	/*
	 * ========================
	 * =   validaDocumentoRequetidos  =
	 * ========================
	 */
	
	/** La Constante VALIDA_DOCUMENTO_REQUERIDOS. */
	public static final String VALIDA_DOCUMENTO_REQUERIDOS = "VALIDA_DOCUMENTO_REQUERIDOS";
	
	/** La Constante DOCUMENTOS. */
	public static final String DOCUMENTOS = "DOCUMENTOS";

	/** La Constante LISTA_DOCUMENTOS. */
	public static final String LISTA_DOCUMENTOS = "@LISTA_DOCUMENTOS@";
	
	/** La Constante DOCUMENTOS_REQUERIDOS_SIN_ANEXAR. */
	public static final String DOCUMENTOS_REQUERIDOS_SIN_ANEXAR = "DOCUMENTOS_REQUERIDOS_SIN_ANEXAR";
	
	/*
	 * ========================
	 * =   terminaActividad  =
	 * ========================
	 */
	
	/** La Constante TERMINA_ACTIVIDAD. */
	public static final String TERMINA_ACTIVIDAD = "TERMINA_ACTIVIDAD";
	
	/** La Constante ESTADO_INVALIDO_PARA_TERMINAR. */
	public static final String ESTADO_INVALIDO_PARA_TERMINAR = "ESTADO_INVALIDO_PARA_TERMINAR";

	/** La Constante ERR-UPD-TAB-ERR-UPD-TAB-IN_NODO_PROCESO. */
	public static final String ERR_UPD_TAB_IN_NODO_PROCESO = "ERR-UPD-TAB-IN_NODO_PROCESO";
	
	/*
	 * ========================
	 * =   leeSeccionesNodo  =
	 * ========================
	 */
	
	/** La Constante NO_EXISTEN_SECCIONES_NODO. */
	public static final String NO_EXISTEN_SECCIONES_NODO = "NO_EXISTEN_SECCIONES_NODO";
	
	/*
	 * ========================
	 * =   valDatosIn  =
	 * ========================
	 */
	
	/** La Constante ACTIVIDAD_NO_EXISTE. */
	public static final String ACTIVIDAD_NO_EXISTE = "ACTIVIDAD_NO_EXISTE";
	
	/*
	 * ========================
	 * =   leeDocumentosNodo  =
	 * ========================
	 */
	
	/** La Constante LEE_COLUMNAS_SECCION_OCU. */
	public static final String LEE_COLUMNAS_SECCION_OCU = "LEE_COLUMNAS_SECCION_OCU";
	
	/** La Constante ERR_LEE_DOCUMENTOS_NODO. */
	public static final String ERR_LEE_DOCUMENTOS_NODO = "ERR_LEE_DOCUMENTOS_NODO";

	/*
	 * ========================
	 * =   correEjecutablePython  =
	 * ========================
	 */
	
	/** La Constante NOMBRE_EJECUTABLE_REPSE. */
	public static final String NOMBRE_EJECUABLE_REPSE = "ValidacionREPSE.exe";
	
	/** La Constante PATH_EJECUTABLE_REPSE. */
	public static final String PATH_EJECUTABLE_REPSE = "C:\\briomax\\REPSE\\";

	
	/** La Constante PATH_EJECUTABLE_PDF. */
	public static final String PATH_RUTA_PDF = "C:\\briomax\\Documentacion_PDF\\";
	
	/** La Constante backup. */
	public static final String BACKUP = "Backup";
	
	/*
	 * ============================================================================
	 * =   PROCESA MENSAJES DE ENVÍO Y RECEPCIÓN EN LA INSTANCIA ACTUAL DE BD  =
	 * ============================================================================
	 */
	public static final String USUARIO_MENSAJE ="Brio.Workflow";
	
	public static final String AMBITO_WFBD_ACTUAL = "WF-BD-ACTUAL";
	
	public static final String SITUACION_PENDIENTE = "PENDIENTE";
	
	public static final String ACTIVACION_POR_MENSAJE = "ACTIVACION POR MENSAJE";
	
	public static final String MENSAJE = "MENSAJE";
	
	public static final String ROL_WORKFLOW = "ROL-WORKFLOW";
	
	public static final String 	ENVIADO = "ENVIADO";
	
	public static final String 	PROCESA_MENSAJES = "PROCESA_MENSAJES";
	
	
	/*
	 * ============================================================================
	 * =   PROCESA SP_LEE_REGLAS_ACTIVIDAD =
	 * ============================================================================
	 */
	public static final String INFORMATIVO ="INFORMATIVO";
	
	public static final String AVISO = "AVISO";
	
	public static final String INF = "inf";
	
	public static final String WAR = "war";
	
	public static final String ERR = "err";
	
	public static final String PAR_ABRE = "(";
	
	public static final String PAR_CIERRE = ")";
	
	public static final String COR_ABRE = "[";
	
	public static final String COR_CIERRE = "]";
	
	public static final String DOS_PUNTOS = ":";
	
	public static final String COMILLA = "'";
	
	public static final String SET = "set";
	
	
	/*
	 * ============================================================================
	 * =   SP_TEMPORIZADOR_ACTIVIDADES  *
	 *  ============================================================================
	 */
	
	public static final String CVE_USUARIO_BRIOWF = "Brio.Workflow";
	
	public static final String TEMPORIZADOR_ACTIVIDADES = "TEMPORIZADOR_ACTIVIDADES";
    
	public static final String ACTIVIDAD_USUARIO_TEM = "ACTIVIDAD-USUARIO-TEMPORIZACION";
    
	public static final String TIPO_TEM_CON_INT = "CON_INTERRUPCION";
    
	public static final String TIPO_NODO_SIG_CON_INT = "TEMPORIZACION-CON-INTERRUPCION";
    
	public static final String TIPO_NODO_SIG_SIN_INT = "TEMPORIZACION-SIN-INTERRUPCION";
    
	public static final String INTERRUMPIDA = "INTERRUMPIDA";
    
	public static final String VENCIDA_POR_TIEMPO = "VENCIDA POR TIEMPO";
	
	public static final String ACTIVIDAD_PENDIENTE = "ACTIVIDAD_PENDIENTE";
	
	
	
	/*
	 * ============================================================================
	 * = SP_TEMPORIZADOR_ACTIVIDADES *
	 * ============================================================================
	 */
	
	public static final String NOTIFICADO = "NOTIFICADO";
	
    public static final String FECHA_PROGRAMACION = "@FECHA_PROGRAMACION@";
    
    public static final String NOM_DOCUMENTO = "@NOM_PROCESO_PERIODICO@";
    
    public static final String CONTRATO = "@CONTRATO@";
    
    public static final String MES_PROCESO = "@MES_PROCESO@";
    
    public static final String PROGRAMADO =  "PROGRAMADO";
    
    public static final String FECHA_LIMITE_DIAS = "FECHA_LIMITE_DIAS";
    
    public static final String UTC = "UTC";
    
    /*
	 * ============================================================================
	 * = TEMPORIZADOR_DECLARACION_COMPLEMENTARIA *
	 * ============================================================================
	 */
    public static final String NUM_CONTRATO = "VPRO_01_NUM_CONTRATO";
    
    public static final String PERIODO_EJECUCION_DEL_CONTRATISTA = "VPRO_01_PERIODO_EJECUCION_DEL_CONTRATISTA";
    
    public static final String PERIODO_EJECUCION_AL_CONTRATISTA = "VPRO_01_PERIODO_EJECUCION_AL_CONTRATISTA";
    
    public static final String RFC_CONTRATISTA = "VPRO_01_RFC_CONTRATISTA";
    
    public static final String DEC_IVA_CONTRATISTA = "DEC_IVA_CONTRATISTA";
    
    public static final String DEC_IVA_CONTRATISTA_COMPLEMENTARIO = "DEC_IVA_CONTRATISTA_COMPLEMENTARIO";
    
    public static final String PROGRAMADO_DC = "PROGRAMADO";
    
    public static final String PENDIENTE_DC = "PENDIENTE";
    
    public static final String REPSE_ELIMINAR = "REPSE_ELIMINAR_%";
    
    
    
    
    /*
	 * ============================================================================
	 * = SP_GENERA_FECHAS_INTERVAL *
	 * ============================================================================
	 */
    
    public static final String DOMINGO = "D";
    
    public static final String LUNES = "L";
    
    public static final String MARTES = "M";
    
    public static final String MIERCOLES = "W";
    
    public static final String JUEVES = "J";
    
    public static final String VIERNES = "V";
    
    public static final String SABADO = "S";
    
    public static final String CH_1 = "1";
    
    public static final String CH_2 = "2";
    
    public static final String CH_3 = "3";
    
    public static final String CH_4 = "4";
    
    public static final String CH_5 = "5";
    
    public static final String CH_6 = "6";
    
    public static final String CH_7 = "7";
    
    public static final String TIPO_LISTA_DIAS_MES = "DIAS_DEL_MES";
    
    public static final String TIPO_LISTA_DIAS_SEMANA = "DIAS_DE_LA_SEMANA";
    
    public static final String TIPO_LISTA_NINGUNA = "NINGUNA";
    
    public static final String  DETALLE_PERIODO_PENULTIMO = "P";
    
    public static final String DETALLE_PERIODO_ULTIMO = "U";
    

}