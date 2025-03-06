-- wego.car_parks definition

CREATE TABLE `car_parks` (
  `car_park_no` varchar(10) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `x_coord` decimal(10,4) DEFAULT NULL,
  `y_coord` decimal(10,4) DEFAULT NULL,
  `car_park_type` varchar(50) DEFAULT NULL,
  `type_of_parking_system` varchar(50) DEFAULT NULL,
  `short_term_parking` varchar(50) DEFAULT NULL,
  `free_parking` varchar(50) DEFAULT NULL,
  `night_parking` varchar(3) DEFAULT NULL,
  `car_park_decks` int(11) DEFAULT NULL,
  `gantry_height` decimal(3,1) DEFAULT NULL,
  `car_park_basement` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`car_park_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- wego.park_info definition

CREATE TABLE `park_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carpark_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `total_lots` int(11) DEFAULT NULL,
  `lots_available` int(11) DEFAULT NULL,
  `lot_type` varchar(10) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8mb4;


-- wego.users definition

CREATE TABLE `users` (
  `id` varchar(25) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `user_name` varchar(10) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_unique` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;