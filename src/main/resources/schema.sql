CREATE TABLE hotels (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    country VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20),
    phone_number VARCHAR(20),
    email VARCHAR(100),
    description TEXT
);

CREATE TABLE rooms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    room_number VARCHAR(50) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    capacity INT NOT NULL,
    available_from DATE NOT NULL,
    available_to DATE NOT NULL,
    amenities TEXT,
    floor INT,
    bed_type VARCHAR(50),
    FOREIGN KEY (hotel_id) REFERENCES hotels(id) -- Assumes a hotels table exists
);

INSERT INTO rooms (hotel_id, room_number, room_type, description, price, capacity, available_from, available_to, amenities, floor, bed_type)
VALUES
(1, '101', 'Single', 'Cozy single room with a view of the city.', 75.00, 1, '2024-08-01', '2024-08-31', 'Wi-Fi, TV, Air Conditioning', 1, 'Single'),
(1, '102', 'Double', 'Spacious double room with a king-sized bed.', 120.00, 2, '2024-08-01', '2024-08-31', 'Wi-Fi, TV, Mini-Bar', 1, 'King'),
(2, '201', 'Suite', 'Luxurious suite with a living area and balcony.', 250.00, 4, '2024-08-01', '2024-08-31', 'Wi-Fi, TV, Jacuzzi, Mini-Bar', 2, 'King'),
(2, '202', 'Double', 'Comfortable double room with twin beds.', 100.00, 2, '2024-08-01', '2024-08-31', 'Wi-Fi, TV', 2, 'Twin'),
(3, '301', 'Single', 'Economy single room ideal for solo travelers.', 50.00, 1, '2024-08-01', '2024-08-31', 'Wi-Fi', 3, 'Single');

INSERT INTO hotels (name, address, city, state, country, postal_code, phone_number, email, description)
VALUES
('Grand Plaza Hotel', '123 Main Street', 'Metropolis', 'NY', 'USA', '10001', '(555) 123-4567', 'contact@grandplazahotel.com', 'Luxurious hotel in the heart of the city with top-notch amenities and service.'),
('Seaside Resort', '456 Ocean Drive', 'Beachtown', 'CA', 'USA', '90210', '(555) 234-5678', 'info@seasideresort.com', 'Relaxing seaside resort with beautiful ocean views and a private beach.'),
('Mountain Retreat', '789 Mountain Road', 'Highland', 'CO', 'USA', '80401', '(555) 345-6789', 'hello@mountainretreat.com', 'Cozy retreat nestled in the mountains with stunning views and outdoor activities.');
