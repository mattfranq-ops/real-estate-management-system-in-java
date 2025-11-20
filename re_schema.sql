-- ========================================
-- Real Estate Schema for System Management
-- ========================================

-- ==Agents Overview==
CREATE TABLE agents (
    agent_id SERIAL PRIMARY KEY, --Every agent will have a unique id
    agent_name VARCHAR(100) NOT NULL, --Every agent will have a name
    agent_email VARCHAR(120) UNIQUE NOT NULL, --Every agent must have an emails
    agent_phone VARCHAR(20) UNIQUE, --Every agent will have a unique number
    agent_firm VARCHAR(100), --Every can work with a firm, self employed may also be an option
    agent_commission DECIMAL(5,2) --An agents commission
);

-- ==Clients on==
CREATE TABLE clients(
    client_id SERIAL PRIMARY KEY,
    client_name VARCHAR(100) NOT NULL,
    client_email VARCHAR(120) UNIQUE NOT NULL,
    client_phone VARCHAR(20) UNIQUE,
    client_type VARCHAR(10) NOT NULL CHECK (client_type IN ('Buyer', 'Seller', 'Investor')) --Unique to clients, way to reference what their business with their agent is
);

-- ==Properties Template==
CREATE TABLE properties(
    property_listing SERIAL PRIMARY KEY,
    property_mls_listing VARCHAR(30) UNIQUE NOT NULL, --Properties need to have a mls listing in order for an agent to market it
    property_address VARCHAR(200) UNIQUE NOT NULL,
    property_city VARCHAR(100),
    property_state VARCHAR(50),
    property_zipcode VARCHAR(20), --Lines above are general information to "locate" a property if used in a real world setting
    property_type VARCHAR(20) CHECK (property_type in ('Single-Family','Multi-Family','Condo','Lot-And-Plot', 'Commercial')), --properties are listed as the following in order for it to be known what type of property is being marketed
    property_bedrooms INT,
    property_bathrooms DECIMAL(4,1),
    property_square_feet INT,
    property_year_built INT, --Property details
    property_listing_price DECIMAL(12,2) NOT NULL, --Property price for buyers
    list_date DATE DEFAULT CURRENT_DATE, --Property will by default be listed the same day that its posting is made in this system
    agent_id INT REFERENCES agents(agent_id) --References the agent that working on this property by assigning it their id
);

-- ==Offers==
CREATE TABLE offers(
    offer_id SERIAL PRIMARY KEY,
    property_id INT NOT NULL REFERENCES properties(property_listing) ON DELETE CASCADE, -- Offer will be deleted if property is deleted
    client_id INT NOT NULL REFERENCES clients(client_id) ON DELETE CASCADE, -- Offer will be deleted in client is delted
    offer_amount DECIMAL(12,2) NOT NULL CHECK (offer_amount > 0), -- Offer amount
    offer_date DATE NOT NULL DEFAULT CURRENT_DATE, -- Date of offer is listed as current date if not specified
    offer_status VARCHAR(20) NOT NULL CHECK (offer_status in ('Submitted','Accepted','Rejected','Withdrawn','Countered')) -- Offer status
);

-- ==Previous Closings==
CREATE TABLE closings(
    closing_id SERIAL PRIMARY KEY,
    offer_id INT UNIQUE NOT NULL REFERENCES offers(offer_id) ON DELETE CASCADE, -- If offer is deleted, closing is deleted along with it
    closing_date DATE NOT NULL, -- Date closing is listed for
    closing_sale_price DECIMAL(12,2) NOT NULL CHECK (closing_sale_price > 0), -- Final price agreed upon for sale
    mortgage_amount DECIMAL(12,2) NOT NULL CHECK (mortgage_amount > 0), -- Mortgage listed for closing
    agent_commission DECIMAL(12,2) CHECK (agent_commission >= 0) -- Commission for agent
);

