--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package ANIO_ESCOLARR
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "ANIO_ESCOLARR" AS 
    PROCEDURE INSERTAR_ANIO_ESCOLAR (
        V_ANIO IN DATE,
        V_FECHA_INICIO IN DATE,
        V_FECHA_FIN IN DATE
    );
    
    FUNCTION INCREMENT_ID_ANIO_ESCOLAR RETURN NUMBER;
    
    FUNCTION EXISTE_ANIO_ESCOLAR(V_ANIO IN DATE) RETURN BOOLEAN;

    PROCEDURE SP_LISTAR_ANIO_ESCOLAR (
        C_LISTA OUT SYS_REFCURSOR
    );
    
    PROCEDURE SP_ACTUALIZAR_ANIO_ESCOLAR (
        V_ID_ANIO_ESCOLAR IN NUMBER,
        V_ANIO IN DATE,
        V_FEHCA_INICIO IN DATE,
        V_FECHA_FIN IN DATE,
        V_ERROR IN OUT NUMBER,
        V_DESCRIPCION_ERR IN OUT VARCHAR2
    );

END ANIO_ESCOLARR;
