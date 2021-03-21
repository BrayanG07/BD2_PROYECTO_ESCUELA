--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package ALUMNOS_DB
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "ALUMNOS_DB" AS 

   PROCEDURE SP_ACTUALIZAR_ALUMNOS (
        V_ID_ALUMNO IN NUMBER,
        V_NOMBRES IN VARCHAR,
        V_APELLIDOS IN VARCHAR,
        V_RTN IN VARCHAR2,
        V_DIRECCION IN VARCHAR2,
        V_TELEFONO IN VARCHAR2,
        V_DESCRIPCION_ERR OUT VARCHAR2
    );
    
    PROCEDURE SP_LISTAR_ALUMNOS (
        V_BUSQUEDA IN VARCHAR2,
        C_LISTA_ALUMNO OUT SYS_REFCURSOR
    );

END ALUMNOS_DB;
