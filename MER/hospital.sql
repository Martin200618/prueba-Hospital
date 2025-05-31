-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-05-2025 a las 06:13:02
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospital`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `id` bigint(20) NOT NULL,
  `confirmado` bit(1) NOT NULL,
  `fecha_envio` datetime(6) DEFAULT NULL,
  `medicamento_id` bigint(20) DEFAULT NULL,
  `paciente_id` bigint(20) DEFAULT NULL,
  `paciente_medicamento_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamento`
--

CREATE TABLE `medicamento` (
  `id` bigint(20) NOT NULL,
  `dosis` varchar(255) DEFAULT NULL,
  `horario` time(6) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `recibir_notificaciones` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente_medicamento`
--

CREATE TABLE `paciente_medicamento` (
  `id` bigint(20) NOT NULL,
  `proximo_recordatorio` datetime(6) DEFAULT NULL,
  `suspendido` bit(1) NOT NULL,
  `medicamento_id` bigint(20) DEFAULT NULL,
  `paciente_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsud7jnwwxqk3g1f682hmc8j0f` (`medicamento_id`),
  ADD KEY `FKd2h8ntjxag3h26th5yy4is9h9` (`paciente_id`),
  ADD KEY `FK8g6ntctbrn0dqp4cb8d3limtm` (`paciente_medicamento_id`);

--
-- Indices de la tabla `medicamento`
--
ALTER TABLE `medicamento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `paciente_medicamento`
--
ALTER TABLE `paciente_medicamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9q8fv208sshrlbrit54bky0eh` (`medicamento_id`),
  ADD KEY `FKqonlqmo4b64ecoonb23jp779v` (`paciente_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `medicamento`
--
ALTER TABLE `medicamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paciente_medicamento`
--
ALTER TABLE `paciente_medicamento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historial`
--
ALTER TABLE `historial`
  ADD CONSTRAINT `FK8g6ntctbrn0dqp4cb8d3limtm` FOREIGN KEY (`paciente_medicamento_id`) REFERENCES `paciente_medicamento` (`id`),
  ADD CONSTRAINT `FKd2h8ntjxag3h26th5yy4is9h9` FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`id`),
  ADD CONSTRAINT `FKsud7jnwwxqk3g1f682hmc8j0f` FOREIGN KEY (`medicamento_id`) REFERENCES `medicamento` (`id`);

--
-- Filtros para la tabla `paciente_medicamento`
--
ALTER TABLE `paciente_medicamento`
  ADD CONSTRAINT `FK9q8fv208sshrlbrit54bky0eh` FOREIGN KEY (`medicamento_id`) REFERENCES `medicamento` (`id`),
  ADD CONSTRAINT `FKqonlqmo4b64ecoonb23jp779v` FOREIGN KEY (`paciente_id`) REFERENCES `paciente` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
