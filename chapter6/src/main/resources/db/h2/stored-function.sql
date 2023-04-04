CREATE OR REPLACE FUNCTION get_first_name_by_id (id bigint)
    RETURNS varchar AS '
declare
    res varchar;
BEGIN
    SELECT e.name into res FROM employee e where e.id = $1;
    RETURN res;
END;
' LANGUAGE plpgsql;