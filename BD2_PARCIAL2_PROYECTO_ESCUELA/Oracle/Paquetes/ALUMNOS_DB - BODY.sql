--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package Body ALUMNOS_DB
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "ALUMNOS_DB" AS
    PROCEDURE SP_ACTUALIZAR_ALUMNOS (
        V_ID_ALUMNO IN NUMBER,
        V_NOMBRES IN VARCHAR,
        V_APELLIDOS IN VARCHAR,
        V_RTN IN VARCHAR2,
        V_DIRECCION IN VARCHAR2,
        V_TELEFONO IN VARCHAR2,
        V_DESCRIPCION_ERR OUT VARCHAR2
    )AS
BEGIN
    UPDATE ALUMNOS
        SET NOMBRES = V_NOMBRES, APELLIDOS = V_APELLIDOS, RTN = V_RTN, DIRECCION = V_DIRECCION, TELEFONO = V_TELEFONO
        WHERE ID_ALUMNO = V_ID_ALUMNO;
EXCEPTION
    WHEN OTHERS THEN
    V_DESCRIPCION_ERR := SQLERRM;
END SP_ACTUALIZAR_ALUMNOS;

PROCEDURE SP_LISTAR_ALUMNOS (
        V_BUSQUEDA IN VARCHAR2,
        C_LISTA_ALUMNO OUT SYS_REFCURSOR
    )
    IS BEGIN
    OPEN C_LISTA_ALUMNO FOR
        SELECT ID_ALUMNO, NOMBRES, APELLIDOS, RTN, DIRECCION, TELEFONO
        FROM ALUMNOS
        WHERE NOMBRES LIKE '%'||V_BUSQUEDA||'%';    
END SP_LISTAR_ALUMNOS;

END ALUMNOS_DB;
