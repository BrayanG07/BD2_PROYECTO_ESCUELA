--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package Body GRADO_ASIGNATURAS_DB
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "GRADO_ASIGNATURAS_DB" AS 

  PROCEDURE SP_INSERTAR_ASIG_GRADO (
    V_ID_NIVEL_EDUCATIVO IN NUMBER,
    V_ID_ASIGNATURA IN NUMBER    
)
AS BEGIN
     INSERT INTO NVL_EDUCATIVO_ASIGNATURAS VALUES(V_ID_NIVEL_EDUCATIVO,V_ID_ASIGNATURA);
END SP_INSERTAR_ASIG_GRADO;

FUNCTION EXISTE_CLASE_ASIGNADA_CURSO (V_ID_ASIGNATURA IN NUMBER, V_ID_GRADO NUMBER) RETURN BOOLEAN AS 
        v_contador NUMBER;
    BEGIN
        SELECT count(1)
            INTO v_contador
            FROM NVL_EDUCATIVO_ASIGNATURAS
        WHERE ID_ASIGNATURA = V_ID_ASIGNATURA
        AND ID_NIVEL_EDUCATIVO = V_ID_GRADO;                
            RETURN (v_contador > 0);
    END EXISTE_CLASE_ASIGNADA_CURSO; 
    
PROCEDURE SP_GRADO_ASIGN_LISTA (
        V_BUSQUEDA IN VARCHAR2,
        C_LISTA_GRADO_ASIGN OUT SYS_REFCURSOR
    )
    IS BEGIN
    OPEN C_LISTA_GRADO_ASIGN FOR
        SELECT na.id_nivel_educativo, na.id_asignatura, ne.nombre, ne.seccion, a.nombre
            FROM NVL_EDUCATIVO_ASIGNATURAS NA INNER JOIN nivel_educativo NE ON NA.ID_NIVEL_EDUCATIVO = NE.ID_NIVEL_EDUCATIVO
            INNER JOIN ASIGNATURAS A ON NA.ID_ASIGNATURA = A.ID_ASIGNATURA
        WHERE A.nombre LIKE '%'||V_BUSQUEDA||'%';    
END SP_GRADO_ASIGN_LISTA;    


PROCEDURE SP_LISTAR_ASIGN_GRADO_TBL (
            V_ID_NIVEL_EDUCATIVO IN NUMBER,
            C_LISTA_RESULTANTE OUT SYS_REFCURSOR
          )
    IS BEGIN
        OPEN C_LISTA_RESULTANTE FOR
            SELECT NEA.id_asignatura, a.nombre
                FROM NVL_EDUCATIVO_ASIGNATURAS NEA INNER JOIN ASIGNATURAS A ON NEA.id_asignatura = a.id_asignatura        
        WHERE NEA.id_nivel_educativo = V_ID_NIVEL_EDUCATIVO;
END SP_LISTAR_ASIGN_GRADO_TBL;


PROCEDURE ELIMINAR_GRADO_ASIGN (
        V_ID_NIVEL_EDUCATIVO IN NUMBER 
    )
    AS BEGIN
        DELETE FROM nvl_educativo_asignaturas WHERE id_nivel_educativo = V_ID_NIVEL_EDUCATIVO;
END ELIMINAR_GRADO_ASIGN ; 

FUNCTION LIMITE_CLASES_GRADO(V_ID_GRADO IN NUMBER) RETURN NUMBER AS
    CURSOR C_CURSOR_VALIDAR IS
        SELECT ID_ASIGNATURA
            FROM nvl_educativo_asignaturas
        WHERE ID_NIVEL_EDUCATIVO = V_ID_GRADO;
      total_resultados NUMBER;
    BEGIN

  FOR ITEM IN C_CURSOR_VALIDAR LOOP
     total_resultados := C_CURSOR_VALIDAR%ROWCOUNT;
 END LOOP;
 RETURN total_resultados;
END LIMITE_CLASES_GRADO;

END GRADO_ASIGNATURAS_DB;
