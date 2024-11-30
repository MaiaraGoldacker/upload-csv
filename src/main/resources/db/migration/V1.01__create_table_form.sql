CREATE TABLE type_form (
    code TEXT PRIMARY KEY,
    description TEXT
);

CREATE TABLE form (
    id SERIAL PRIMARY KEY,
    source TEXT,
    code TEXT UNIQUE,
    display_value TEXT, 
    long_description TEXT,
    from_date DATE,
    to_date DATE,
    sorting_priority INTEGER, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type_form_code TEXT NOT NULL, 
    CONSTRAINT fk_type_form_code FOREIGN KEY (type_form_code) REFERENCES type_form (code) ON DELETE CASCADE
);

