

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Airline
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Airline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Airline` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Airline` ;

-- -----------------------------------------------------
-- Table `Airline`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `Name` VARCHAR(45) NULL,
  `Surname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `DOB` DATE NULL,
  `Balance` DOUBLE NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `password_UNIQUE` (`password` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Airline`.`Flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`Flight` (
  `idFlight` INT NOT NULL AUTO_INCREMENT,
  `destination` VARCHAR(45) NULL,
  `price` DOUBLE NULL,
  `departTime` DATETIME NULL,
  `duration` TIME NULL,
  `availSeats` INT NULL DEFAULT 40,
  `departLoc` VARCHAR(45) NULL,
  PRIMARY KEY (`idFlight`),
  UNIQUE INDEX `idFlight_UNIQUE` (`idFlight` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Airline`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`Payment` (
  `idPayment` INT NOT NULL AUTO_INCREMENT,
  `amt` DOUBLE NOT NULL,
  `paid` SET('y', 'n') NOT NULL DEFAULT 'n',
  PRIMARY KEY (`idPayment`),
  UNIQUE INDEX `idPayment_UNIQUE` (`idPayment` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Airline`.`Booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Airline`.`Booking` (
  `User_idUser` INT NOT NULL,
  `Flight_idFlight` INT NOT NULL,
  `Payment_idPayment` INT NOT NULL,
  `bookTime` DATETIME NOT NULL,
  `seats` INT NULL,
  PRIMARY KEY (`User_idUser`, `Flight_idFlight`, `Payment_idPayment`),
  INDEX `fk_Booking_Flight1_idx` (`Flight_idFlight` ASC),
  INDEX `fk_Booking_Payment1_idx` (`Payment_idPayment` ASC),
  CONSTRAINT `fk_Booking_User`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Airline`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Flight1`
    FOREIGN KEY (`Flight_idFlight`)
    REFERENCES `Airline`.`Flight` (`idFlight`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Booking_Payment1`
    FOREIGN KEY (`Payment_idPayment`)
    REFERENCES `Airline`.`Payment` (`idPayment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
