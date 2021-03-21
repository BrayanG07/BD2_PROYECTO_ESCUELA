--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package USUARIOS_DB
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE "USUARIOS_DB" AS
    PROCEDURE SP_INSERTAR_USUARIO (
        V_USUARIO VARCHAR2,
        V_PASWORD VARCHAR2,
        V_ID_ROL NUMBER,
        V_ERROR OUT NUMBER,
        V_DESCRIPCION OUT VARCHAR2
    );
    --
    FUNCTION INCREMENT_ID_USUARIOS RETURN NUMBER;
    
    --
    PROCEDURE ACTUALIZAR_USUARIO (
        V_ID_USUARIO NUMBER,
        V_USUARIO VARCHAR,
        V_PASSWORD VARCHAR,
        V_ID_ROL NUMBER,
        V_ERROR OUT NUMBER,
        V_DESCRIPCION_ERR OUT VARCHAR2
    );

    --
     PROCEDURE SP_LISTAR_ROLES (
        C_CURSOR OUT SYS_REFCURSOR
     );
     
     --
      PROCEDURE SP_USUARIO_LISTA (
        V_BUSQUEDA IN VARCHAR2,
        C_LISTA_USUARIO OUT SYS_REFCURSOR
      );
      
    --
    PROCEDURE SP_LOGIN (
        V_USUARIO VARCHAR2,
        V_PASSWORD VARCHAR2,
        C_CURSOR_LOGIN OUT SYS_REFCURSOR
    );

END USUARIOS_DB;
