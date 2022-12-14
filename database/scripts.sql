-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clinica_medica_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clinica_medica_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clinica_medica_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clinica_medica_db` ;

-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_especialidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_especialidade` (
  `id_especialidade` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_especialidade`),
  UNIQUE INDEX `unique_nome` (`nome` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_contato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_contato` (
  `id_contato` BIGINT NOT NULL AUTO_INCREMENT,
  `celular` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `telefone` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_contato`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_pessoa` (
  `id_pessoa` BIGINT NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(255) NULL DEFAULT NULL,
  `cep` VARCHAR(255) NULL DEFAULT NULL,
  `cidade` VARCHAR(255) NULL DEFAULT NULL,
  `estado` VARCHAR(255) NULL DEFAULT NULL,
  `logradouro` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `numeroCasa` INT NULL DEFAULT NULL,
  `id_contato` BIGINT NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  INDEX `pk_contato` (`id_contato` ASC) VISIBLE,
  CONSTRAINT `pk_contato`
    FOREIGN KEY (`id_contato`)
    REFERENCES `clinica_medica_db`.`tb_contato` (`id_contato`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_funcionario` (
  `dataContrato` DATETIME NULL DEFAULT NULL,
  `salario` DOUBLE NULL DEFAULT NULL,
  `senhaHash` VARCHAR(255) NULL DEFAULT NULL,
  `id_funcionario` BIGINT NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  CONSTRAINT `pk_pessoa_funcionario`
    FOREIGN KEY (`id_funcionario`)
    REFERENCES `clinica_medica_db`.`tb_pessoa` (`id_pessoa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_medico` (
  `crm` BIGINT NULL DEFAULT NULL,
  `id_medico` BIGINT NOT NULL,
  `id_especialidade` BIGINT NOT NULL,
  PRIMARY KEY (`id_medico`),
  UNIQUE INDEX `unique_crm` (`crm` ASC) VISIBLE,
  INDEX `pk_especialidade` (`id_especialidade` ASC) VISIBLE,
  CONSTRAINT `pk_especialidade`
    FOREIGN KEY (`id_especialidade`)
    REFERENCES `clinica_medica_db`.`tb_especialidade` (`id_especialidade`),
  CONSTRAINT `pk_funcionario_medico`
    FOREIGN KEY (`id_medico`)
    REFERENCES `clinica_medica_db`.`tb_funcionario` (`id_funcionario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_agenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_agenda` (
  `id_agenda` BIGINT NOT NULL AUTO_INCREMENT,
  `id_medico` BIGINT NULL DEFAULT NULL,
  `data` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `horario` INT NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `telefone` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_agenda`),
  INDEX `pk_agenda_medico` (`id_medico` ASC) VISIBLE,
  CONSTRAINT `pk_agenda_medico`
    FOREIGN KEY (`id_medico`)
    REFERENCES `clinica_medica_db`.`tb_medico` (`id_medico`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_endereco` (
  `id_endereco` BIGINT NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(255) NULL DEFAULT NULL,
  `cep` VARCHAR(255) NULL DEFAULT NULL,
  `cidade` VARCHAR(255) NULL DEFAULT NULL,
  `estado` VARCHAR(255) NULL DEFAULT NULL,
  `logradouro` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_endereco`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_medica_db`.`tb_paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_medica_db`.`tb_paciente` (
  `altura` DOUBLE NULL DEFAULT NULL,
  `peso` DOUBLE NULL DEFAULT NULL,
  `tipoSanguineo` VARCHAR(3) NULL DEFAULT NULL,
  `id_paciente` BIGINT NOT NULL,
  PRIMARY KEY (`id_paciente`),
  CONSTRAINT `pk_pessoa_paciente`
    FOREIGN KEY (`id_paciente`)
    REFERENCES `clinica_medica_db`.`tb_pessoa` (`id_pessoa`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `clinica_medica_db`;

DELIMITER $$
USE `clinica_medica_db`$$
CREATE
DEFINER=`root`@`%`
TRIGGER `clinica_medica_db`.`insert_endereco`
AFTER INSERT ON `clinica_medica_db`.`tb_pessoa`
FOR EACH ROW
if NEW.cep not in (select cep from tb_endereco) then
		insert into tb_endereco(bairro, cep, cidade, estado, logradouro) values(new.bairro, new.cep, new.cidade, new.estado, new.logradouro);
	end if$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
