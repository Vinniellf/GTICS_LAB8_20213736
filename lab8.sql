-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------


CREATE SCHEMA IF NOT EXISTS `lab8` ;
USE `lab8` ;

-- -----------------------------------------------------
-- Table `new_schema1`.`Students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab8`.`Students` (
  `idStudents` INT NOT NULL auto_increment,
  `nombre` VARCHAR(100) default NULL,
  `gpa` float default NULL,
  `facultad` VARCHAR(100) default NULL,
  `creditosCompletados` INT default NULL,
  PRIMARY KEY (`idStudents`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
