--
--				PRUEBA DE SP_GUARDA_DOCUMENTO_BINARIO_NODO
--
DECLARE				@CH_TIPO_EXCEPCION			VARCHAR		(20)			-- TIPO DE EXCEPCIÓN 'OK', 'ERROR', 'AVISO'
DECLARE				@CH_MENSAJE					VARCHAR		(2000)			-- MENSAJE
DECLARE				@CH_NOMBRE_ARCHIVO			VARCHAR		(100)			-- NOMBRE DEL ARCHIVO
DECLARE				@VB_ARCHIVO_BINARIO			VARBINARY	(MAX)			-- ARCHIVO BINARIO

--
--			REUPERA UN DCUMENTO BINARIO RESPALDADO
--
SELECT	@CH_NOMBRE_ARCHIVO = NOMBRE_ARCHIVO,
		@VB_ARCHIVO_BINARIO = ARCHIVO_BINARIO
FROM	RES_IN_DOCUMENTO_NOD
WHERE	CVE_PROCESO = 'INCIDENCIA-SMI'		AND
		VERSION = 1.00						AND
		CVE_INSTANCIA = '201807-000001'		AND
		CVE_NODO = 'ACTIVIDAD-USUARIO'		AND
		ID_NODO = 1							AND
		SECUENCIA_NODO = 2					AND
		SECUENCIA_DOCUMENTO = 1

--
--			GUARDA EL DOCUMENTO
--
EXEC		SP_GUARDA_DOCUMENTO_BINARIO_NODO
				'Gabriela.Arias',                                   -- USUARIO
				'BRIOMAX',                                          -- ENTIDAD
				'BRIOMAX-CENTRAL',                                  -- LOCALIDAD
				'ES-MX',                                            -- IDIOMA
				'INCIDENCIA-SMI',                                   -- CLAVE DEL PROCESO
				1.00,                                               -- VERSIÓN
				'201807-000001',                                    -- CLAVE DE LA INSTANCIA
				'ACTIVIDAD-USUARIO',                                -- CLAVE DEL NODO
				1,                                                  -- ID DEL NODO
				2,                                                  -- SECUENCIA DEL NODO
				1,                                                  -- SECUENCIA DEL DOCUMENTO
				@CH_NOMBRE_ARCHIVO,                                 -- NOMBRE DEL ARCHIVO
				@VB_ARCHIVO_BINARIO,                                -- DOCUMENTO BINARIO
				'MS WORD',                                          -- TIPO DE DOCUMENTO
				@CH_TIPO_EXCEPCION                  OUT,            -- TIPO DE EXCEPCIÓN 'OK', 'ERROR', 'AVISO'
				@CH_MENSAJE                         OUT             -- MENSAJE

--
--			VERIFICA RESUTADOS
--
SELECT '@CH_TIPO_EXCEPCION=', @CH_TIPO_EXCEPCION
SELECT '@CH_MENSAJE=', @CH_MENSAJE
SELECT * FROM IN_DOCUMENTO_NODO

--
--			BOORRANDO EL DOCUMENTO DEL NODO.
--			ESTE SCRIPT SE EJECUTA DE FORMA INDEPENDIENTE PARA ELIMINAR UN DOCUMENTO PREVIAMENTE CARGADO
--
UPDATE		IN_DOCUMENTO_NODO
	SET		NOMBRE_ARCHIVO = NULL,
			ARCHIVO_BINARIO = NULL
WHERE		CVE_PROCESO = 'INCIDENCIA-SMI'                     AND
			VERSION = 1.00                                     AND
			CVE_INSTANCIA = '201807-000001'                    AND
			CVE_NODO = 'ACTIVIDAD-USUARIO'                     AND
			ID_NODO = 1                                        AND
			SECUENCIA_NODO = 2                                 AND
			SECUENCIA_DOCUMENTO = 1


