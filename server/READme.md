```
create database chope;
use chope;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `userRoles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `userroles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `userroles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

CREATE TABLE `restaurant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `about` varchar(512) DEFAULT NULL,
  `contact` varchar(128) DEFAULT NULL,
  `restaurantlink` text,
  `menu` text,
  `user_id` int NOT NULL,
  `address` varchar(128) DEFAULT NULL,
  `cuisine_id` int DEFAULT NULL,
  `latitude` decimal(8,6) DEFAULT NULL,
  `longtitude` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`user_id`),
  KEY `ibfk_1_idx` (`cuisine_id`),
  CONSTRAINT `id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `days` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mondayOpening` time DEFAULT NULL,
  `mondayClosing` time DEFAULT NULL,
  `tuesdayOpening` time DEFAULT NULL,
  `tuesdayClosing` time DEFAULT NULL,
  `wednesdayOpening` time DEFAULT NULL,
  `wednesdayClosing` time DEFAULT NULL,
  `thursdayOpening` time DEFAULT NULL,
  `thursdayClosing` time DEFAULT NULL,
  `fridayOpening` time DEFAULT NULL,
  `fridayClosing` time DEFAULT NULL,
  `saturdayOpening` time DEFAULT NULL,
  `saturdayClosing` time DEFAULT NULL,
  `sundayOpening` time DEFAULT NULL,
  `sundayClosing` time DEFAULT NULL,
  `restaurant_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `days_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);

CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `types` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(128) NOT NULL,
  `ratings` int NOT NULL,
  `restaurant_id` int NOT NULL,
  `user_id` int NOT NULL,
  `timestamp` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurant_id` (`restaurant_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `cuisine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `time_id` int NOT NULL,
  `restaurant_id` int NOT NULL,
  `user_id` int NOT NULL,
  `date_reserve` varchar(48) DEFAULT NULL,
  `pax` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurant_id` (`restaurant_id`),
  KEY `user_id` (`user_id`),
  KEY `reservations_ibfk_3_idx` (`time_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` mediumblob NOT NULL,
  `image_filetype` varchar(45) NOT NULL,
  `restaurant_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `images_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);

CREATE TABLE `mealNames` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `mealCategories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
	
CREATE TABLE `meals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name_id` int NOT NULL,
  `category_id` int NOT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `restaurant_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name_id` (`name_id`),
  KEY `category_id` (`category_id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `meals_ibfk_1` FOREIGN KEY (`name_id`) REFERENCES `mealNames` (`id`),
  CONSTRAINT `meals_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `mealCategories` (`id`),
  CONSTRAINT `meals_ibfk_3` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);

CREATE TABLE `distance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `distance` int NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `capacity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `capacity` int DEFAULT NULL,
  `starttiming` time DEFAULT NULL,
  `restaurant_id` int NOT NULL,
  `endtiming` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `restaurant_id` (`restaurant_id`),
  CONSTRAINT `capacity_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
);

```