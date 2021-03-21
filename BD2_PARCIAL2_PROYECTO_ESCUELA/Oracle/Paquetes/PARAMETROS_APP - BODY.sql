--------------------------------------------------------
--  File created - Saturday-March-20-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package Body PARAMETROS_APP
--------------------------------------------------------

  CREATE OR REPLACE PACKAGE BODY "PARAMETROS_APP" AS
----------------------
    PROCEDURE INSERTAR_ASIGNACIONES (
        V_ID_PROFESOR NUMBER,
        V_ID_ASIGNATURA NUMBER,
        V_ID_NIVEL_EDUCATIVO NUMBER,
        V_ERROR OUT NUMBER,
        V_DESCRIPCION_ERR OUT VARCHAR2 
    ) AS BEGIN
    V_ERROR := 0;
    CASE
        WHEN profesores_asig_curso_db.VALIDAR_CLASE_GRADO(V_ID_ASIGNATURA, V_ID_NIVEL_EDUCATIVO) THEN
            V_DESCRIPCION_ERR := 'Error: La clase que intenta registrar ya se esta impartiendo en el grado';
            V_ERROR := 1;
        WHEN profesores_asig_curso_db.LIMITE_ASIGN_PROFESOR(V_ID_PROFESOR) >= 10 THEN
            V_DESCRIPCION_ERR := 'Error: El profesor solo puede impartir 10 clases';
            V_ERROR := 1;    
        ELSE    
            PROFESORES_ASIG_CURSO_DB.SP_INSERTAR_ASIG_PROFESORES(V_ID_PROFESOR,V_ID_ASIGNATURA,V_ID_NIVEL_EDUCATIVO);
        END CASE;            
      
END INSERTAR_ASIGNACIONES; 
    
----------------------    
    PROCEDURE SP_INSERTAR_MATRICULA_ALUMNO (
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
    ) AS BEGIN 
    V_ERROR := 0;
    IF MATRICULA_ALUMNO.ALUMNO_YA_MATRICULADO(V_RTN) THEN
        V_DESCRIPCION := 'El alumno ya esta matriculado, actualizar la matricula si eso se requiere';
        V_ERROR := 1;
    ELSE
        MATRICULA_ALUMNO.SP_INSERTAR_MATRICULA(V_NOMBRES,V_APELLIDOS,V_RTN, V_DIRECCION, V_TELEFONO, V_FECHA, V_ID_ANIO_ESCOLAR, V_ID_NIVEL_EDUCATIVO);
        V_DESCRIPCION := 'Matricula registrada correctamente';
    END IF;
    END SP_INSERTAR_MATRICULA_ALUMNO; 
    
    PROCEDURE SP_INSERTAR_ANIO_ESCOLAR (
        V_ANIO IN DATE,
        V_FECHA_INICIO IN DATE,
        V_FECHA_FIN IN DATE,
        V_ERROR IN OUT NUMBER,
        V_DESCRIPCION IN OUT VARCHAR2
    )
    AS BEGIN
        V_ERROR := 0;
        
        CASE
        WHEN ANIO_ESCOLARR.EXISTE_ANIO_ESCOLAR(V_ANIO) THEN
            V_DESCRIPCION := 'El año escolar ya fue registrado no puede registrar nuevamente el mismo';
            V_ERROR := 1;
        WHEN EXTRACT(YEAR FROM V_ANIO) <> EXTRACT(YEAR FROM V_FECHA_INICIO) OR EXTRACT(YEAR FROM V_ANIO) <> EXTRACT(YEAR FROM V_FECHA_FIN) THEN
            V_DESCRIPCION := 'El año de las fechas de inicio y fin deben coincidir con el año de la fecha del año escolar';
            V_ERROR := 1;
        ELSE    
            anio_escolarr.insertar_anio_escolar(V_ANIO,V_FECHA_INICIO,V_FECHA_FIN);
            V_DESCRIPCION := 'Año Escolar registrado correctamente';
        END CASE; 

    EXCEPTION 
        WHEN OTHERS THEN
            V_ERROR := 1;
            V_DESCRIPCION := SQLERRM;
    END SP_INSERTAR_ANIO_ESCOLAR;
    
-----------------------------
    PROCEDURE SP_INSERTAR_CLASES_GRADO (
        V_ID_NIVEL_EDUCATIVO IN NUMBER,
        V_ID_ASIGNATURA IN NUMBER, 
        V_DESCRIPCION IN OUT VARCHAR2
    )AS BEGIN
        CASE
        WHEN GRADO_ASIGNATURAS_DB.EXISTE_CLASE_ASIGNADA_CURSO(V_ID_ASIGNATURA, V_ID_NIVEL_EDUCATIVO) THEN
             V_DESCRIPCION := 'Advertencia: La clase ya esta asignada para este grado';
        WHEN GRADO_ASIGNATURAS_DB.LIMITE_CLASES_GRADO(V_ID_NIVEL_EDUCATIVO) >= 10 THEN
             V_DESCRIPCION := 'Advertencia: El grado solo puede tener asignadas un maximo de 10 clases';
        ELSE
            GRADO_ASIGNATURAS_DB.SP_INSERTAR_ASIG_GRADO(V_ID_NIVEL_EDUCATIVO, V_ID_ASIGNATURA);
        END CASE;
        EXCEPTION 
            WHEN OTHERS THEN
            V_DESCRIPCION := SQLERRM;
    END SP_INSERTAR_CLASES_GRADO;
    
    PROCEDURE SP_INSERTAR_PROFESOR (
         P_NOMBRES IN VARCHAR2,
         P_APELLIDOS IN VARCHAR2,
         P_RTN IN VARCHAR2,
         P_DIRECCION IN VARCHAR2,
         P_TELEFONO IN VARCHAR2,
         P_ESTADO IN NUMBER,
         V_DESCRIPCION IN OUT VARCHAR2
    )AS BEGIN
        CASE
        WHEN PROFESOR_DB.PROFESOR_YA_REGISTRADO(P_RTN) THEN
             V_DESCRIPCION := 'Advertencia: El profesor ya esta registrado en el sistema';
        ELSE
            profesor_db.insertar_profesor(P_NOMBRES,P_APELLIDOS,P_RTN,P_DIRECCION,P_TELEFONO,P_ESTADO);
        END CASE;
        EXCEPTION 
            WHEN OTHERS THEN
            V_DESCRIPCION := SQLERRM;
    END SP_INSERTAR_PROFESOR;
    
    PROCEDURE SP_INSERTAR_ASIGNATURA(
         A_NOMBRE IN VARCHAR2,
         A_DESCRIPCION IN VARCHAR2,
         A_CREDITOS IN VARCHAR2,
         A_HORA_INICIO IN VARCHAR2,
         A_HORA_FIN IN VARCHAR2,
         V_DESCRIPCION IN OUT VARCHAR2
    )AS BEGIN
        CASE
        WHEN ASIGNATURA_DB.ASIGNATURA_YA_REGISTRADO(A_NOMBRE) THEN
             V_DESCRIPCION := 'Advertencia: La asignatura ya esta registrada en el sistema';
        ELSE
            asignatura_db.insertar_asignatura(A_NOMBRE,A_DESCRIPCION,A_CREDITOS,A_HORA_INICIO,A_HORA_FIN);
        END CASE;
        EXCEPTION 
            WHEN OTHERS THEN
            V_DESCRIPCION := SQLERRM;
    END SP_INSERTAR_ASIGNATURA;
    
END PARAMETROS_APP;
