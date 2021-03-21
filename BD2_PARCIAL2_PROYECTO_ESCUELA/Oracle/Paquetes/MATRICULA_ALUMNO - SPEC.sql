--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package MATRICULA_ALUMNO
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "MATRICULA_ALUMNO" AS
    FUNCTION ALUMNO_YA_MATRICULADO(V_RTN IN VARCHAR2) RETURN BOOLEAN;
    
    PROCEDURE SP_INSERTAR_MATRICULA(
        V_NOMBRES IN VARCHAR2,
        V_APELLIDOS IN VARCHAR2,
        V_RTN IN VARCHAR2,
        V_DIRECCION IN VARCHAR2,
        V_TELEFONO IN VARCHAR2,
        V_FECHA DATE,
        V_ID_ANIO_ESCOLAR NUMBER,
        V_ID_NIVEL_EDUCATIVO NUMBER
    );
    
    FUNCTION OBTENER_SIGUIENTE_COD_ALUMNO RETURN NUMBER;
    
    FUNCTION OBTENER_SIGUIENTE_MATRICULA RETURN NUMBER;
    
    FUNCTION ULTIMO_ID_ALUMNO RETURN NUMBER;
    
    PROCEDURE SP_ACTUALIZAR_MATRICULA (
        V_ID_MATRICULA IN NUMBER,
        V_ID_ALUMNO IN NUMBER,
        V_NOMBRE IN VARCHAR,
        V_APELLIDO IN VARCHAR,
        V_DIRECCION IN VARCHAR,
        V_RTN IN VARCHAR,
        V_TELEFONO IN VARCHAR,
        V_ID_ANIO_ESCOLAR NUMBER,
        V_ID_NIVEL_EDUCATIVO IN NUMBER,
        V_FECHA DATE,
        V_ERROR OUT NUMBER,
        V_DESCRIPCION_ERR OUT VARCHAR2
    );
    
    PROCEDURE SP_COMBO_LISTAR_ANIO (
        C_CURSOR_ANIO OUT SYS_REFCURSOR
    );
    
    PROCEDURE SP_LISTAR_MATRICULA(
        V_BUSQUEDA IN NUMBER,
        C_CURSOR OUT SYS_REFCURSOR
    );
    
END MATRICULA_ALUMNO;
