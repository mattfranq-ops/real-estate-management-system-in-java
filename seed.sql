-- =============================================
-- Seed Data available for re_schema
-- =============================================

-- == AGENTS == 

INSERT INTO agents (agent_name, agent_email, agent_phone, agent_firm, agent_commission) -- Inserting values into the re_schema agents template
VALUES
('Sarah Johnson', 'sarah.johnson@elitehomes.com', '555-111-2222', 'Elite Homes Realty', 2.50),
('Marcus Lee', 'the.marcus@eliteshomes.com', '555-333-1234', 'Elite Homes Realty', 3.0),
('John Doe', 'themysteryman@enigmarealty.com', '555-444-4321', 'Enigma Realty', NULL); -- Test for null case

-- == CLIENTS ==

INSERT INTO clients (client_name, client_email, client_phone, client_type) -- Inserting values into the re_schema clients template
VALUES
('Johnathon Greg', 'jgreg@gmail.com', '555-777-8888', 'Buyer'),
('Johnathon Greg', 'johnathongreg@gmail.com', '555-876-1111', 'Buyer'),
('Hamala Ann', 'ham@gmail.com', '555-123-5432', 'Seller'),
('Natalie Tom', 'nattyom@gmail.com', '555-222-1111', 'Investor');

-- == Properties ==

INSERT INTO properties (
    property_mls_listing, -- Listing
    property_address, property_city, property_state, property_zipcode, -- Property location
    property_type, property_bedrooms, property_bathrooms, property_square_feet, property_year_built, -- Property details
    property_listing_price, -- Listing price
    agent_id -- agent assigned
)
VALUES -- Inserting values into the re_schema properties template
('MLS-10001', '123 Maple Street', 'Springfield', 'IL', '62704',
 'Single-Family', 3, 2.0, 1850, 1998, 325000.00, 1),

('MLS-10002', '88 Riverside Ave', 'Denver', 'CO', '80205',
 'Condo', 2, 1.5, 1100, 2010, 250000.00, 2),

('MLS-10003', '450 Industrial Way', 'Dallas', 'TX', '75201',
 'Commercial', NULL, 4.0, 5000, 1985, 950000.00, 3),

('MLS-10004', '789 Hillcrest Lane', 'Orlando', 'FL', '32801',
 'Single-Family', 4, 3.5, 2400, 2005, 470000.00, 1);

 -- == Offers ==
 
 INSERT INTO offers (property_id, client_id, offer_amount, offer_status) -- Inserting values into the re_schema offers template
 VALUES
(1, 1, 315000.00, 'Submitted'),
(1, 4, 320000.00, 'Countered'),
(2, 1, 245000.00, 'Accepted'),
(3, 3, 900000.00, 'Submitted'),
(4, 1, 450000.00, 'Rejected');

-- == Closings ==

INSERT INTO closings (offer_id, closing_date, closing_sale_price, mortgage_amount, agent_commission) -- Inserting values into the re_schema closings template
VALUES
(3, '2025-01-15', 245000.00, 200000.00, 7350.00);