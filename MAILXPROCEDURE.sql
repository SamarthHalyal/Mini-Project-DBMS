CREATE DEFINER=`root`@`localhost` PROCEDURE `MAILXPROCEDURE`(inout name_list varchar(100))
BEGIN

-- declareing variables
declare is_done integer default 0;
declare user_name varchar(20) default "";

-- creating a cursor
declare C_phones cursor for
select username from register;

-- creating a handler to get out of loop
declare CONTINUE handler for Not found set is_done = 1;

-- opening the cursor for use
open C_phones;

-- loop where action happens
get_list: loop
fetch C_phones into user_name;
if is_done = 1 then
leave get_list;
end if;
set name_list = CONCAT(user_name,";",name_list);
end loop;

close C_phones;

END
