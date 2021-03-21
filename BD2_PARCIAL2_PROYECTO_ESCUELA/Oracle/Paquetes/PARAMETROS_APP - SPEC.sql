--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package PARAMETROS_APP
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "PARAMETROS_APP" AS
    PROCEDURE INSERTAR_ASIGNACIONES (
        V_ID_PROFESOR NUMBER,
        V_ID_ASIGNATURA NUMBER,
        V_ID_NIVEL_EDUCATIVO NUMBER,
        V_ERROR OUT NUMBER,
        V_DESCRIPCION_ERR OUT VARCHAR2 
    );
    PROCEDURE SP_INSERTAR_MATRICULA_ALUMNO(
        V_NOMBRES IN VARCHAR2,
        V_APELLIDOS IN VARCHAR2,
        V_RTN IN VARCHAR2,
        V_DIRECCION IN VARCHAR2,
        V_TELEFONO IN VARCHAR2,
        V_FECHA DATE,
        V_ID_ANIO_ESCOLAR NUMBER,
        V_ID_NIVEL_EDUCATIVO NUMBER,
        V_ERROR IN OUT NUMBER,
        V_DESCRIPCION IN OUT VARCHAR2
    );
    
    PROCEDURE SP_INSERTAR_ANIO_ESCOLAR (
        V_ANIO IN DATE,
        V_FECHA_INICIO IN DATE,
        V_FECHA_FIN IN DATE,
        V_ERROR IN OUT NUMBER,
        V_DESCRIPCION IN OUT VARCHAR2
    );
    
    PROCEDURE SP_INSERTAR_CLASES_GRADO (
        V_ID_NIVEL_EDUCATIVO IN NUMBER,
        V_ID_ASIGNATURA IN NUMBER, 
        V_DESCRIPCION IN OUT VARCHAR2
    );
    
    PROCEDURE SP_INSERTAR_PROFESOR (
         P_NOMBRES IN VARCHAR2,
         P_APELLIDOS IN VARCHAR2,
         P_RTN IN VARCHAR2,
         P_DIRECCION IN VARCHAR2,
         P_TELEFONO IN VARCHAR2,
         P_ESTADO IN NUMBER,
         V_DESCRIPCION IN OUT VARCHAR2
    );
    
    PROCEDURE SP_INSERTAR_ASIGNATURA(
         A_NOMBRE IN VARCHAR2,
         A_DESCRIPCION IN VARCHAR2,
         A_CREDITOS IN VARCHAR2,
         A_HORA_INICIO IN VARCHAR2,
         A_HORA_FIN IN VARCHAR2,
         V_DESCRIPCION IN OUT VARCHAR2
    );
        
END PARAMETROS_APP;
