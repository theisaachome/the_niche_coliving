-- Enable UUID generation (PostgreSQL)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE flyway_schema_history (
  installed_rank INT NOT NULL,
  version VARCHAR(50),
  description VARCHAR(200),
  type VARCHAR(20),
  script VARCHAR(1000),
  checksum INTEGER,
  installed_by VARCHAR(100),
  installed_on TIMESTAMP DEFAULT now(),
  execution_time INTEGER,
  success BOOLEAN,
  PRIMARY KEY (installed_rank)
);
