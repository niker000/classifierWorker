CREATE TABLE procurement_vocabulary (
	id SERIAL NOT NULL PRIMARY KEY,
	title VARCHAR NOT NULL,
	code VARCHAR UNIQUE NOT NULL,
	tree_path ltree NOT NULL
);