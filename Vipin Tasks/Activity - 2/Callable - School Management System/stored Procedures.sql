// Insert   
  ----------------------------------------------
  CREATE PROCEDURE InsertStudent(integer, varchar(15), varchar(50), varchar(50))
  LANGUAGE 'plpgsql'
  AS $$
  
  BEGIN
  
  INSERT INTO student(studentid, studentname, studentAddress, studentEmail) VALUES($1, $2, $3, $4);
  
  END;
  $$;
  
  CALL InsertStudent(2, 'bHEEM', 'ASD','FDA');
  -----------------------------------------------
  
  ----------------------------------------------------------
 // Update 
 ----------------------
 CREATE PROCEDURE UpdateStudent(varchar(15), integer)
  LANGUAGE 'plpgsql'
  AS $$
  
  BEGIN
  
   update student set studentName=$1 where studentId=$2;
  
  END;
  $$;
  
  CALL UpdateStudent('Chota Bheem', 2);
  
 -----------------------------------------------------------
 // Delete
 ----------------
  CREATE PROCEDURE DeleteStudent(integer)
  LANGUAGE 'plpgsql'
  AS $$
  
  BEGIN
  
   delete from student where studentId=$1;
  
  END;
  $$;
  
  CALL DeleteStudent(2);
  
  --------------------------------------------------------------------------
  // find All
  ----------------------
  	CREATE OR REPLACE FUNCTION FindAllStudents()
  	RETURNS  TABLE
  	(
  		studentId integer, studentName varchar(15), studentAddress varchar(50), studentEmail varchar(50)
  	)
  	
  	LANGUAGE 'plpgsql'
  	
  	AS
  	
  	$BODY$
  	#variable_conflict use_column
  	BEGIN
  	RETURN QUERY
  		SELECT studentId, studentName, studentAddress, studentEmail from student;
  	END;
  	$BODY$;
  	
  	select FindAllStudents();
  
 // Find By Id
 -------------------------
	CREATE OR REPLACE FUNCTION FindStudentById(integer)
  	RETURNS  TABLE
  	(
  		studentId integer, studentName varchar(15), studentAddress varchar(50), studentEmail varchar(50)
  	)
  	
  	LANGUAGE 'plpgsql'
  	
  	AS
  	
  	$BODY$
  	#variable_conflict use_column
  	BEGIN
  	RETURN QUERY
  		SELECT studentId, studentName, studentAddress, studentEmail from student where studentId=$1;
  	END;
  	$BODY$;
  	
  	select FindStudentById(1002);
  
  ---------------------------------------------------------------------

