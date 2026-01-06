-- Leasing Schema

-- Units

CREATE TABLE units (
    unit_id SERIAL PRIMARY KEY,
    property_id INT REFERENCES properties(property_listing) ON DELETE CASCADE,
    unit_number VARCHAR(20) NOT NULL,
    monthly_rent DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'Available' CHECK (status IN ('Available', 'Occupied', 'Maintenance')),
    days_on_market INT DEFAULT 0,
    pm_id INT REFERENCES agents(agent_id) --reference to property managers id
);

-- Tenants

CREATE TABLE tenants (
    tenant_id SERIAL PRIMARY KEY,
    unit_id INT UNIQUE REFERENCES units(unit_id),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) UNIQUE,
    lease_start DATE NOT NULL,
    lease_END DATE NOT NULL,
    rent_status VARCHAR(20) DEFAULT 'Good Standing' check (rent_status IN ('Good Standing', 'Late', 'Delinquent')),
    is_active BOOLEAN DEFAULT TRUE -- When false, tenant will be moved to Junk
);

-- Prospects 

CREATE TABLE prospects (
    prospect_id SERIAL PRIMARY KEY,
    unit_applying_for INT REFERENCES units(unit_id),
    name VARCHAR(100) NOT NULL,
    annual_income DECIMAL(12,2),
    credit_score INT CHECK (credit_score BETWEEN 300 AND 850),
    rental_history_ok BOOLEAN DEFAULT TRUE,
    application_status VARCHAR(20) DEFAULT 'pending'
);