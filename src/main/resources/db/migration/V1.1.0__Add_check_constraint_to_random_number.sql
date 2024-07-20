ALTER TABLE toy
ADD (
    CONSTRAINT chk_random_number_range
    CHECK (random_number >= 0 AND random_number <= 999)
);