CREATE TABLE IF NOT EXISTS urls (
  id  SERIAL PRIMARY KEY NOT NULL,
  original_url VARCHAR(255) NOT NULL,
  short_url VARCHAR(255) NOT NULL UNIQUE
);
