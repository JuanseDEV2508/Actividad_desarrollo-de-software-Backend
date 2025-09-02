-- Initial data for Smart Devices application
-- This file will be executed automatically when the application starts

-- Insert sample devices
INSERT INTO devices (name, brand, type, description, price, release_date, image_url, specifications) VALUES
-- Smartphones
('iPhone 15 Pro', 'Apple', 'SMARTPHONE', 'El iPhone más avanzado con chip A17 Pro, cámara de 48MP y diseño en titanio.', 999.99, '2023-09-22', 'https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400', 
'Pantalla: 6.1" Super Retina XDR OLED
Procesador: A17 Pro
RAM: 8GB
Almacenamiento: 128GB/256GB/512GB/1TB
Cámara: Triple 48MP + 12MP + 12MP
Batería: 3274mAh
Sistema: iOS 17'),

('Samsung Galaxy S24 Ultra', 'Samsung', 'SMARTPHONE', 'El flagship de Samsung con S Pen integrado y cámara de 200MP.', 1199.99, '2024-01-17', 'https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=400',
'Pantalla: 6.8" Dynamic AMOLED 2X
Procesador: Snapdragon 8 Gen 3
RAM: 12GB
Almacenamiento: 256GB/512GB/1TB
Cámara: Cuádruple 200MP + 12MP + 50MP + 10MP
Batería: 5000mAh
Sistema: Android 14'),

('Google Pixel 8 Pro', 'Google', 'SMARTPHONE', 'El smartphone de Google con la mejor cámara y IA integrada.', 899.99, '2023-10-04', 'https://images.unsplash.com/photo-1592899677977-9c10ca588bbd?w=400',
'Pantalla: 6.7" LTPO OLED
Procesador: Google Tensor G3
RAM: 12GB
Almacenamiento: 128GB/256GB/512GB
Cámara: Triple 50MP + 48MP + 48MP
Batería: 4950mAh
Sistema: Android 14'),

-- Laptops
('MacBook Pro 16" M3 Pro', 'Apple', 'LAPTOP', 'La laptop más potente de Apple con chip M3 Pro y pantalla Liquid Retina XDR.', 2499.99, '2023-10-30', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=400',
'Pantalla: 16" Liquid Retina XDR
Procesador: Apple M3 Pro
RAM: 18GB/36GB
Almacenamiento: 512GB/1TB/2TB/4TB
GPU: Integrada 18-core
Batería: Hasta 22 horas
Sistema: macOS Sonoma'),

('Dell XPS 15', 'Dell', 'LAPTOP', 'Laptop premium con pantalla OLED y procesador Intel de última generación.', 1899.99, '2023-08-15', 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=400',
'Pantalla: 15.6" 4K OLED
Procesador: Intel Core i7-13700H
RAM: 16GB DDR5
Almacenamiento: 512GB SSD
GPU: NVIDIA RTX 4050
Batería: 86Whr
Sistema: Windows 11'),

('Lenovo ThinkPad X1 Carbon', 'Lenovo', 'LAPTOP', 'La laptop empresarial más confiable con diseño ultraligero.', 1599.99, '2023-06-20', 'https://images.unsplash.com/photo-1588872657578-7efd1f1555ed?w=400',
'Pantalla: 14" 2.8K OLED
Procesador: Intel Core i7-1355U
RAM: 16GB LPDDR5
Almacenamiento: 512GB SSD
GPU: Intel Iris Xe
Batería: 57Whr
Sistema: Windows 11 Pro'),

-- Tablets
('iPad Pro 12.9" M2', 'Apple', 'TABLET', 'La tablet más potente con chip M2 y pantalla Liquid Retina XDR.', 1099.99, '2022-10-18', 'https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=400',
'Pantalla: 12.9" Liquid Retina XDR
Procesador: Apple M2
RAM: 8GB/16GB
Almacenamiento: 128GB/256GB/512GB/1TB/2TB
Cámara: Dual 12MP + 10MP
Batería: Hasta 10 horas
Sistema: iPadOS 17'),

('Samsung Galaxy Tab S9 Ultra', 'Samsung', 'TABLET', 'La tablet Android más grande con S Pen y pantalla AMOLED.', 999.99, '2023-07-26', 'https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=400',
'Pantalla: 14.6" Super AMOLED
Procesador: Snapdragon 8 Gen 2
RAM: 12GB/16GB
Almacenamiento: 256GB/512GB/1TB
Cámara: Dual 13MP + 8MP
Batería: 11200mAh
Sistema: Android 13'),

-- Smartwatches
('Apple Watch Series 9', 'Apple', 'SMARTWATCH', 'El smartwatch más avanzado con detección de caídas y ECG.', 399.99, '2023-09-22', 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400',
'Pantalla: 41mm/45mm Always-On Retina
Procesador: S9 SiP
RAM: 1GB
Almacenamiento: 32GB
Sensores: ECG, SpO2, Temperatura
Batería: Hasta 18 horas
Sistema: watchOS 10'),

('Samsung Galaxy Watch 6 Classic', 'Samsung', 'SMARTWATCH', 'Smartwatch premium con bisel rotativo y Wear OS.', 369.99, '2023-07-26', 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400',
'Pantalla: 43mm/47mm Super AMOLED
Procesador: Exynos W930
RAM: 2GB
Almacenamiento: 16GB
Sensores: ECG, SpO2, Temperatura
Batería: Hasta 40 horas
Sistema: Wear OS 4'),

-- Headphones
('Sony WH-1000XM5', 'Sony', 'HEADPHONES', 'Los auriculares con cancelación de ruido más avanzada del mercado.', 349.99, '2022-05-12', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400',
'Driver: 30mm
Frecuencia: 4Hz-40kHz
Cancelación de ruido: Sí
Conectividad: Bluetooth 5.2, NFC
Batería: Hasta 30 horas
Peso: 250g'),

('Apple AirPods Pro 2', 'Apple', 'HEADPHONES', 'Auriculares in-ear con cancelación de ruido activa y audio espacial.', 249.99, '2022-09-07', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400',
'Driver: Personalizado
Frecuencia: 20Hz-20kHz
Cancelación de ruido: Sí
Conectividad: Bluetooth 5.3
Batería: Hasta 6 horas (30 con estuche)
Peso: 5.3g por auricular'),

('Bose QuietComfort 45', 'Bose', 'HEADPHONES', 'Auriculares con la mejor cancelación de ruido y confort excepcional.', 329.99, '2021-09-23', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400',
'Driver: TriPort
Frecuencia: 20Hz-20kHz
Cancelación de ruido: Sí
Conectividad: Bluetooth 5.1
Batería: Hasta 24 horas
Peso: 238g');

-- Insert sample comments
INSERT INTO comments (user_name, comment_text, rating, created_at, device_id) VALUES
('Carlos M.', 'Excelente smartphone, la cámara es increíble y el rendimiento es sobresaliente. Muy recomendado.', 5, '2024-01-15 10:30:00', 1),
('Ana L.', 'Buen dispositivo, pero el precio es muy alto para lo que ofrece. La batería podría durar más.', 4, '2024-01-14 15:45:00', 1),
('Miguel R.', 'La mejor laptop que he tenido. El chip M3 Pro es una bestia y la pantalla es espectacular.', 5, '2024-01-13 09:20:00', 4),
('Laura S.', 'Muy buena tablet para trabajo y entretenimiento. El S Pen funciona perfectamente.', 5, '2024-01-12 14:15:00', 8),
('Roberto P.', 'Los auriculares tienen una cancelación de ruido increíble. Perfectos para viajar.', 5, '2024-01-11 11:30:00', 11),
('Carmen V.', 'El smartwatch es muy útil para el fitness, pero la batería podría durar más tiempo.', 4, '2024-01-10 16:20:00', 9),
('Diego F.', 'Buena laptop para trabajo, pero se calienta bastante cuando ejecutas aplicaciones pesadas.', 3, '2024-01-09 13:45:00', 5),
('Patricia G.', 'Los AirPods Pro son perfectos para el iPhone. La calidad de sonido es excelente.', 5, '2024-01-08 10:10:00', 12),
('Jorge H.', 'El Galaxy S24 Ultra es impresionante. La cámara de 200MP toma fotos increíbles.', 5, '2024-01-07 17:30:00', 2),
('Sofia M.', 'La tablet es muy grande y pesada. No es muy práctica para llevar en la mochila.', 3, '2024-01-06 12:25:00', 8);
