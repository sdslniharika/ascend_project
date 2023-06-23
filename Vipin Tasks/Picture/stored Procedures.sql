// Insert   
  ----------------------------------------------
  CREATE PROCEDURE InsertPicture(varchar(6),  varchar(15),  varchar(10), int, int)
  LANGUAGE 'plpgsql'
  AS $$
  
  BEGIN
  
  INSERT INTO picture(movieId,movieName,language,releasedIn,revenueInDollars) VALUES($1, $2, $3, $4, $5);
  
  END;
  $$;
  
  CALL InsertPicture('M2001', 'bHEEM', 'ASD',2300, 38000);
  -----------------------------------------------
  
  ----------------------------------------------------------
 // Update 
 ----------------------
 CREATE PROCEDURE UpdatePicture(integer, varchar(6))
  LANGUAGE 'plpgsql'
  AS $$
  
  BEGIN
  
   update picture set revenueInDollars=$1 where movieId=$2;
  
  END;
  $$;
  
  CALL UpdatePicture(5600, 'M2001');
  
 -----------------------------------------------------------
 // Delete
 ----------------
  CREATE PROCEDURE DeletePicture(varchar(6))
  LANGUAGE 'plpgsql'
  AS $$
  
  BEGIN
  
   delete from Picture where movieId=$1;
  
  END;
  $$;
  
  CALL DeletePicture('M2001');
  
  --------------------------------------------------------------------------
  // find All
  ----------------------
  	CREATE OR REPLACE FUNCTION FindAllPictures()
  	RETURNS  TABLE
  	(
  		movieId varchar(6), movieName varchar(15), language varchar(10), releasedIn int , revenueInDollars int
  	)
  	
  	LANGUAGE 'plpgsql'
  	
  	AS
  	
  	$BODY$
  	#variable_conflict use_column
  	BEGIN
  	RETURN QUERY
  		SELECT movieId, movieName, language, releasedIn, revenueInDollars from movie;
  	END;
  	$BODY$;
  	
  	select FindAllPictures();
  
 // Find By Id
 -------------------------
	CREATE OR REPLACE FUNCTION FindPictureById(varchar(6))
  	RETURNS  TABLE
  	(
  		movieId varchar(6), movieName varchar(15), language varchar(10), releasedIn int , revenueInDollars int
  	)
  	
  	LANGUAGE 'plpgsql'
  	
  	AS
  	
  	$BODY$
  	#variable_conflict use_column
  	BEGIN
  	RETURN QUERY
  		SELECT movieId, movieName, language, releasedIn, revenueInDollars from movie where movieId = $1;
  	END;
  	$BODY$;
  	
  	select FindPictureById('M1001');
  
  ---------------------------------------------------------------------

