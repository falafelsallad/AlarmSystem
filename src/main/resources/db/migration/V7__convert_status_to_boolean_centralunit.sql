ALTER TABLE central_unit ADD status_boolean BOOLEAN;
-- temporary column

UPDATE central_unit
SET status_boolean = CASE
    WHEN LOWER(status) IN ('true', 'on', 'ready', '1') THEN TRUE
    ELSE FALSE
END;
-- convert values to boolean, in a case format

ALTER TABLE central_unit DROP COLUMN status;
--dropping original column

ALTER TABLE central_unit RENAME COLUMN status_boolean TO status;
--renaming new column to original name