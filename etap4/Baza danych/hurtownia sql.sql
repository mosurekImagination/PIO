-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 17 Sty 2018, 15:59
-- Wersja serwera: 10.1.28-MariaDB
-- Wersja PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `hurtownia`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `dowodysprzedazy`
--

CREATE TABLE `dowodysprzedazy` (
  `IdDS` int(10) NOT NULL,
  `DataWystawienia` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kategorie`
--

CREATE TABLE `kategorie` (
  `Nazwa` varchar(255) DEFAULT NULL,
  `IdKat` int(10) NOT NULL,
  `IdNadKat` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `kategorie`
--

INSERT INTO `kategorie` (`Nazwa`, `IdKat`, `IdNadKat`) VALUES
('śruby', 1, NULL),
('taśmy', 2, NULL),
('farby', 4, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klienci`
--

CREATE TABLE `klienci` (
  `IdK` int(10) NOT NULL,
  `ImieK` varchar(255) NOT NULL,
  `NazwiskoK` varchar(255) NOT NULL,
  `Nip` int(10) NOT NULL,
  `NazwaFirmy` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `klienci`
--

INSERT INTO `klienci` (`IdK`, `ImieK`, `NazwiskoK`, `Nip`, `NazwaFirmy`) VALUES
(1, 'Jan', 'Kowalski', 784398723, 'JanKowalskiCompany'),
(2, 'Piotr', 'Kwiatkowski', 837489328, 'KwiatkiBratki'),
(3, 'Adam', 'Bombała', 823678921, 'AGS'),
(4, 'Maria', 'Kłos', 344329432, 'Sklep Marysia'),
(5, 'Magdalena', 'Roztocka', 387094053, 'Centrum ogrodnicze '),
(6, 'Mariusz', 'Wąski', 509828172, 'Marwas'),
(7, 'Ilona', 'Górska', 870392716, 'Materiały budowlane Ilona Górska');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pozycjezamowienia`
--

CREATE TABLE `pozycjezamowienia` (
  `IdPZ` int(10) NOT NULL,
  `Ilosc` int(10) NOT NULL,
  `Cena` float NOT NULL,
  `Rabat` int(11) NOT NULL,
  `CenaPoRabacie` float NOT NULL,
  `ZapytanieIdZap` int(10) DEFAULT NULL,
  `TowarIdT` int(10) NOT NULL,
  `ZamowienieIdZ` int(10) NOT NULL,
  `terminRealPoz` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `pozycjezamowienia`
--

INSERT INTO `pozycjezamowienia` (`IdPZ`, `Ilosc`, `Cena`, `Rabat`, `CenaPoRabacie`, `ZapytanieIdZap`, `TowarIdT`, `ZamowienieIdZ`, `terminRealPoz`) VALUES
(44, 13, 4, 0, 52, 22, 5, 47, '2018-01-19'),
(45, 200, 4, 2, 784, 23, 5, 50, '2018-01-19'),
(46, 5, 99.75, 0, 99.75, NULL, 4, 48, '2018-01-12'),
(47, 3, 38.4, 0, 38.4, NULL, 6, 49, '2018-01-12'),
(48, 20, 172, 1, 170.28, NULL, 11, 49, '2018-01-12'),
(50, 3, 17.97, 0, 17.97, NULL, 5, 51, '2018-01-12'),
(51, 10, 199.5, 0, 199.5, NULL, 4, 51, '2018-01-12'),
(53, 5, 99.75, 0, 99.75, NULL, 4, 52, '2018-01-13'),
(54, 5, 29.95, 0, 29.95, NULL, 5, 53, '2018-01-13'),
(56, 6, 35.94, 0, 35.94, NULL, 5, 55, '2018-01-15');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownicy`
--

CREATE TABLE `pracownicy` (
  `IdP` int(10) NOT NULL,
  `ImieP` varchar(255) NOT NULL,
  `NazwiskoP` varchar(255) NOT NULL,
  `MailP` varchar(255) NOT NULL,
  `LoginP` varchar(255) NOT NULL,
  `HasloP` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `pracownicy`
--

INSERT INTO `pracownicy` (`IdP`, `ImieP`, `NazwiskoP`, `MailP`, `LoginP`, `HasloP`) VALUES
(1, 'Adam', 'Nowak', 'adam.nowak@gmail.com', 'adam', 'nowak');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `towary`
--

CREATE TABLE `towary` (
  `IdT` int(10) NOT NULL,
  `NazwaT` varchar(255) NOT NULL,
  `Kod` int(10) NOT NULL,
  `KategoriaIdKat` int(10) DEFAULT NULL,
  `Cena` double NOT NULL,
  `MaxRabat` int(11) NOT NULL,
  `Ilosc` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `towary`
--

INSERT INTO `towary` (`IdT`, `NazwaT`, `Kod`, `KategoriaIdKat`, `Cena`, `MaxRabat`, `Ilosc`) VALUES
(4, 'Taśma malarska BLUE MASKING DEXTER', 42578796, 2, 19.95, 2, 50),
(5, 'Taśma PAKOWA dł. 66 AXTON', 98786764, 2, 5.99, 0, 39),
(6, 'Taśma UNIWERSALNA EQUATION', 9786543, 2, 12.8, 3, 294),
(7, 'Taśma dwustronna uniwersalna TRANSPARENT ', 9536783, 2, 14, 2, 143),
(8, 'Śruba 4X10MM STANDERS', 43547689, 1, 3.6, 0, 179),
(9, 'Śruba 8X20MM STANDERS', 97675543, 1, 6.9, 0, 11),
(10, 'Śruba motylkowa 6X25MM STANDERS', 86435467, 1, 11.2, 4, 164),
(11, 'Napinacz liny HAK-OKO 6MM wyt. 60 STANDERS', 6566536, NULL, 8.6, 2, 74),
(12, 'Śruba dwugwintowa 8X120MM FISCHER', 437688764, 1, 1.62, 0, 33),
(13, 'Farba do ścian i sufitów KUCHNIA I ŁAZIENKA 2,5 l Waniliowy dotyk LUXENS', 34797875, 4, 54.97, 4, 29),
(14, 'Farba fasadowa AKRYLOWA Popielaty ALPINA', 246886986, 4, 23.9, 2, 43),
(15, 'Tester farby DO ŚCIAN I SUFITÓW LUXENS', 187864, 4, 2.9, 0, 8),
(16, 'Uszczelka 65 MM EQUATION', 64539998, NULL, 6.5, 0, 75);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zamowienia`
--

CREATE TABLE `zamowienia` (
  `IdZ` int(10) NOT NULL,
  `Status` enum('otwarte','przekazane','wRealizacji','zrealizowane','anulowane') NOT NULL,
  `DataZlozZam` date NOT NULL,
  `TerminRealizacji` date NOT NULL,
  `KwotaZ` float NOT NULL,
  `CzyZatwierdzone` tinyint(1) DEFAULT NULL,
  `PracownikIdP` int(10) DEFAULT NULL,
  `KlientIdK` int(10) NOT NULL,
  `Dowód sprzedażyIdDS` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `zamowienia`
--

INSERT INTO `zamowienia` (`IdZ`, `Status`, `DataZlozZam`, `TerminRealizacji`, `KwotaZ`, `CzyZatwierdzone`, `PracownikIdP`, `KlientIdK`, `Dowód sprzedażyIdDS`) VALUES
(9, 'otwarte', '2018-01-03', '0000-00-00', 656.7, NULL, NULL, 1, NULL),
(10, 'przekazane', '2018-01-02', '0000-00-00', 55.3, 1, NULL, 7, NULL),
(47, 'otwarte', '2018-01-19', '2018-01-19', 77.87, 0, NULL, 1, NULL),
(48, 'przekazane', '2018-01-12', '2018-01-12', 99.75, 0, NULL, 1, NULL),
(49, 'przekazane', '2018-01-12', '2018-01-12', 210.4, 0, NULL, 3, NULL),
(50, 'przekazane', '2018-01-12', '0000-00-00', 4, 0, NULL, 2, NULL),
(51, 'przekazane', '2018-01-12', '0000-00-00', 217.47, 0, NULL, 3, NULL),
(52, 'przekazane', '2018-01-13', '2018-01-13', 99.75, 0, NULL, 1, NULL),
(53, 'przekazane', '2018-01-13', '2018-01-13', 29.95, 0, NULL, 2, NULL),
(54, 'przekazane', '2018-01-15', '0000-00-00', 0, 0, NULL, 2, NULL),
(55, 'przekazane', '2018-01-15', '2018-01-15', 35.94, 0, NULL, 2, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zapytania`
--

CREATE TABLE `zapytania` (
  `IdZap` int(10) NOT NULL,
  `Status` enum('wyslane','zopiniowane','zatwierdzone','odrzucone') NOT NULL,
  `TerminReal` date NOT NULL,
  `Notatka` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `zapytania`
--

INSERT INTO `zapytania` (`IdZap`, `Status`, `TerminReal`, `Notatka`) VALUES
(22, 'zatwierdzone', '2018-01-19', ''),
(23, 'zatwierdzone', '2018-01-19', '');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `dowodysprzedazy`
--
ALTER TABLE `dowodysprzedazy`
  ADD PRIMARY KEY (`IdDS`);

--
-- Indexes for table `kategorie`
--
ALTER TABLE `kategorie`
  ADD PRIMARY KEY (`IdKat`),
  ADD KEY `FKKategoria441039` (`IdNadKat`);

--
-- Indexes for table `klienci`
--
ALTER TABLE `klienci`
  ADD PRIMARY KEY (`IdK`);

--
-- Indexes for table `pozycjezamowienia`
--
ALTER TABLE `pozycjezamowienia`
  ADD PRIMARY KEY (`IdPZ`),
  ADD KEY `FKPozycjaZam181024` (`TowarIdT`),
  ADD KEY `FKPozycjaZam764906` (`ZapytanieIdZap`),
  ADD KEY `FKPozycjaZam33658` (`ZamowienieIdZ`);

--
-- Indexes for table `pracownicy`
--
ALTER TABLE `pracownicy`
  ADD PRIMARY KEY (`IdP`);

--
-- Indexes for table `towary`
--
ALTER TABLE `towary`
  ADD PRIMARY KEY (`IdT`),
  ADD KEY `FKTowar288111` (`KategoriaIdKat`);

--
-- Indexes for table `zamowienia`
--
ALTER TABLE `zamowienia`
  ADD PRIMARY KEY (`IdZ`),
  ADD KEY `FKZamowienie125372` (`PracownikIdP`),
  ADD KEY `FKZamowienie998343` (`KlientIdK`),
  ADD KEY `FKZamowienie301198` (`Dowód sprzedażyIdDS`);

--
-- Indexes for table `zapytania`
--
ALTER TABLE `zapytania`
  ADD PRIMARY KEY (`IdZap`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `dowodysprzedazy`
--
ALTER TABLE `dowodysprzedazy`
  MODIFY `IdDS` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `kategorie`
--
ALTER TABLE `kategorie`
  MODIFY `IdKat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `klienci`
--
ALTER TABLE `klienci`
  MODIFY `IdK` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `pozycjezamowienia`
--
ALTER TABLE `pozycjezamowienia`
  MODIFY `IdPZ` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT dla tabeli `pracownicy`
--
ALTER TABLE `pracownicy`
  MODIFY `IdP` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `towary`
--
ALTER TABLE `towary`
  MODIFY `IdT` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT dla tabeli `zamowienia`
--
ALTER TABLE `zamowienia`
  MODIFY `IdZ` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT dla tabeli `zapytania`
--
ALTER TABLE `zapytania`
  MODIFY `IdZap` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `kategorie`
--
ALTER TABLE `kategorie`
  ADD CONSTRAINT `FKKategoria441039` FOREIGN KEY (`IdNadKat`) REFERENCES `kategorie` (`IdKat`);

--
-- Ograniczenia dla tabeli `pozycjezamowienia`
--
ALTER TABLE `pozycjezamowienia`
  ADD CONSTRAINT `FKPozycjaZam764906` FOREIGN KEY (`ZapytanieIdZap`) REFERENCES `zapytania` (`IdZap`),
  ADD CONSTRAINT `pozycjezamowienia_ibfk_1` FOREIGN KEY (`TowarIdT`) REFERENCES `towary` (`IdT`),
  ADD CONSTRAINT `pozycjezamowienia_ibfk_2` FOREIGN KEY (`ZamowienieIdZ`) REFERENCES `zamowienia` (`IdZ`);

--
-- Ograniczenia dla tabeli `towary`
--
ALTER TABLE `towary`
  ADD CONSTRAINT `FKTowar288111` FOREIGN KEY (`KategoriaIdKat`) REFERENCES `kategorie` (`IdKat`);

--
-- Ograniczenia dla tabeli `zamowienia`
--
ALTER TABLE `zamowienia`
  ADD CONSTRAINT `FKZamowienie125372` FOREIGN KEY (`PracownikIdP`) REFERENCES `pracownicy` (`IdP`),
  ADD CONSTRAINT `FKZamowienie301198` FOREIGN KEY (`Dowód sprzedażyIdDS`) REFERENCES `dowodysprzedazy` (`IdDS`),
  ADD CONSTRAINT `FKZamowienie998343` FOREIGN KEY (`KlientIdK`) REFERENCES `klienci` (`IdK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
