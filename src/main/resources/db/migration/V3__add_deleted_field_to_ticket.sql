ALTER TABLE ticket ADD COLUMN deleted INTEGER;

UPDATE ticket SET deleted = 0;