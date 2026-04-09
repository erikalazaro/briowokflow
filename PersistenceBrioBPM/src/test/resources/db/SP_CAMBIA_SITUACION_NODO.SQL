
--
--             PRUEBA DE SP_CAMBIA_SITUACION_NODO
--
DECLARE @CH_TIPO_EXCEPCION				VARCHAR		(20)			-- TIPO DE EXCEPCION 'OK', 'ERROR', 'AVISO'
DECLARE @CH_MENSAJE						VARCHAR		(2000)			-- MENSAJE

EXEC       SP_CAMBIA_SITUACION_NODO
                'Gabriela.Arias',						-- USUARIO CREADOR
                'BRIOMAX',								-- ENTIDAD
                'BRIOMAX-CENTRAL',						-- LOCALIDAD
                'ES-MX',								-- IDIOMA
                'INCIDENCIA-SMI',						-- CLAVE DEL PROCESO
                1.00,									-- VERSION
                '202003-000002',						-- CLAVE DE LA INSTANCIA
                'ACTIVIDAD-USUARIO',					-- CLAVE DEL NODO
                1,										-- ID DEL NODO
                2,										-- SECUENCIA DEL NODO
                'OBTENER',								-- "OBTENER", "CEDER", "CANCELAR"
                @CH_TIPO_EXCEPCION		OUT,			-- TIPO DE EXCEPCION 'OK', 'ERROR', 'AVISO'
                @CH_MENSAJE				OUT				-- MENSAJE


SELECT '@CH_TIPO_EXCEPCION=', @CH_TIPO_EXCEPCION
SELECT '@CH_MENSAJE=', @CH_MENSAJE
