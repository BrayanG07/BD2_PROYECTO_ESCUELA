--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package GRADO_ASIGNATURAS_DB
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "GRADO_ASIGNATURAS_DB" AS 

  PROCEDURE SP_INSERTAR_ASIG_GRADO (
    V_ID_NIVEL_EDUCATIVO IN NUMBER,
    V_ID_ASIGNATURA IN NUMBER    
);

FUNCTION EXISTE_CLASE_ASIGNADA_CURSO (V_ID_ASIGNATURA IN NUMBER, V_ID_GRADO NUMBER) RETURN BOOLEAN; 

    PROCEDURE SP_GRADO_ASIGN_LISTA (
        V_BUSQUEDA IN VARCHAR2,
        C_LISTA_GRADO_ASIGN OUT SYS_REFCURSOR
      );
      
    PROCEDURE SP_LISTAR_ASIGN_GRADO_TBL (
        V_ID_NIVEL_EDUCATIVO IN NUMBER,
        C_LISTA_RESULTANTE OUT SYS_REFCURSOR
    );
    
    PROCEDURE ELIMINAR_GRADO_ASIGN (
        V_ID_NIVEL_EDUCATIVO IN NUMBER
    );
    
    FUNCTION LIMITE_CLASES_GRADO (V_ID_GRADO IN NUMBER) RETURN NUMBER;

END GRADO_ASIGNATURAS_DB;
